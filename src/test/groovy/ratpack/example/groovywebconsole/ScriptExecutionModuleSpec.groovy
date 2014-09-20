package ratpack.example.groovywebconsole

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import ratpack.test.http.TestHttpClient
import ratpack.test.http.TestHttpClients
import ratpack.groovy.test.embed.ClosureBackedEmbeddedApplication
import spock.lang.AutoCleanup
import spock.lang.Specification

/**
 * An example of functionally testing a module in isolation, using {@link ratpack.test.embed.EmbeddedApplication}
 */
class ScriptExecutionModuleSpec extends Specification {

    @AutoCleanup
    ClosureBackedEmbeddedApplication app = new ClosureBackedEmbeddedApplication()

    @Delegate
    TestHttpClient client = TestHttpClients.testHttpClient(app)

    def "script module provides executor and renderer"() {
        when:
        app.with {
            bindings {
                add new ScriptExecutionModule()
            }
            handlers {
                get { ScriptExecutor executor ->
                    render executor.execute("println 'foo'")
                }
            }
        }

        then:
        jsonResponse().get("outputText").asText() == "foo\n"
    }

    JsonNode jsonResponse() {
        new ObjectMapper().reader().readTree(get().body.text)
    }

}
