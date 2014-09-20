package ratpack.example.groovywebconsole

import ratpack.groovy.test.TestHttpClient
import ratpack.groovy.test.TestHttpClients
import ratpack.groovy.test.embed.ClosureBackedEmbeddedApplication
import spock.lang.AutoCleanup
import spock.lang.Specification

/**
 * An example of functionally testing a module in isolation, using {@link ratpack.test.embed.EmbeddedApplication}
 */
class ScriptExecutionModuleSpec extends Specification {

    @AutoCleanup
    ClosureBackedEmbeddedApplication app = new ClosureBackedEmbeddedApplication()

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
        client.get().body.jsonPath().getString("outputText") == "foo\n"
    }

}
