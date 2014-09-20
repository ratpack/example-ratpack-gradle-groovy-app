package ratpack.example.groovywebconsole

import com.jayway.restassured.path.json.JsonPath
import com.jayway.restassured.response.Response
import ratpack.groovy.test.LocalScriptApplicationUnderTest
import ratpack.groovy.test.TestHttpClient
import ratpack.groovy.test.TestHttpClients
import spock.lang.Specification

class ScriptExecutionSpec extends Specification {

    def aut = new LocalScriptApplicationUnderTest()
    @Delegate TestHttpClient client = TestHttpClients.testHttpClient(aut)

    def setup() {
        resetRequest()
    }

    def "captures output"() {
        when:
        request.param "script", "println 'hello world'"
        post "execute"

        then:
        with(new JsonResponse(response)) {
            outputText == "hello world\n".denormalize()
            executionResult == ""
            stacktraceText == ""
        }
    }

    def "captures result"() {
        when:
        request.param "script", "1%0A3"
        post "execute"

        then:
        with(new JsonResponse(response)) {
            outputText == ""
            executionResult == "3"
            stacktraceText == ""
        }
    }

    def "captures errors"() {
        when:
        request.param "script", "throw new Exception('bang')"
        post "execute"

        then:
        with(new JsonResponse(response)) {
            outputText == ""
            executionResult == ""
            stacktraceText.contains "java.lang.Exception"
            stacktraceText.contains "bang"
        }
    }

    class JsonResponse {
        private JsonPath path

        JsonResponse(Response response) {
            path = response.jsonPath()
        }

        String getOutputText() {
            path.outputText
        }

        String getExecutionResult() {
            path.executionResult
        }

        String getStacktraceText() {
            path.stacktraceText
        }
    }
}
