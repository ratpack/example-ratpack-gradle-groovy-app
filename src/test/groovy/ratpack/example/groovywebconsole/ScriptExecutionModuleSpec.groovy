package ratpack.example.groovywebconsole

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import ratpack.groovy.test.embed.GroovyEmbeddedApp
import ratpack.test.embed.EmbeddedApp
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Specification

/**
 * An example of functionally testing a module in isolation, using {@link ratpack.test.embed.EmbeddedApp}
 */
class ScriptExecutionModuleSpec extends Specification {

    def lineSeparator = System.getProperty("line.separator")

    @AutoCleanup
    EmbeddedApp app

    @Delegate
    TestHttpClient client

    def "script module provides executor and renderer"() {
        when:
        app = GroovyEmbeddedApp.build {
            bindings {
                module ScriptExecutionModule
            }
            handlers {
                get { ScriptExecutor executor ->
                    render executor.execute("println 'foo'")
                }
            }
        }

        and:
        client = TestHttpClient.testHttpClient(app)

        then:
        jsonResponse().get("outputText").asText() == "foo$lineSeparator"
    }

    JsonNode jsonResponse() {
        new ObjectMapper().reader().readTree(get().body.text)
    }

}
