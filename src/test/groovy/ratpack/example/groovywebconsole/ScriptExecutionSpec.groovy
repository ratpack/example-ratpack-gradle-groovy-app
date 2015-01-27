package ratpack.example.groovywebconsole

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import ratpack.groovy.test.LocalScriptApplicationUnderTest
import ratpack.http.client.ReceivedResponse
import ratpack.test.http.TestHttpClient
import spock.lang.Specification

import static ratpack.http.MediaType.APPLICATION_FORM

class ScriptExecutionSpec extends Specification {

    def aut = new LocalScriptApplicationUnderTest()
    @Delegate TestHttpClient client = TestHttpClient.testHttpClient(aut)

    def setup() {
        resetRequest()
        requestSpec {
            it.headers.add("Content-Type", APPLICATION_FORM)
        }
    }

    private String encode(String toBeEncoded) {
        URLEncoder.encode(toBeEncoded, "UTF-8")
    }

    private void postScript(String script) {
        requestSpec {
            it.headers.add("Content-Type", APPLICATION_FORM)
            it.body.stream { it << "script=${encode(script)}" }
        }
        post "execute"
    }

    def "captures output"() {
        when:
        postScript('println "hello world"')

        then:
        with(new JsonResponse(response)) {
            outputText == "hello world\n".denormalize()
            executionResult == ""
            stacktraceText == ""
        }
    }

    def "captures result"() {
        when:
        postScript('1\n3')

        then:
        with(new JsonResponse(response)) {
            outputText == ""
            executionResult == "3"
            stacktraceText == ""
        }
    }

    def "captures errors"() {
        when:
        postScript('throw new Exception("bang")')

        then:
        with(new JsonResponse(response)) {
            outputText == ""
            executionResult == ""
            stacktraceText.contains "java.lang.Exception"
            stacktraceText.contains "bang"
        }
    }

    class JsonResponse {
        private JsonNode json

        JsonResponse(ReceivedResponse response) {
            json = new ObjectMapper().reader().readTree(response.body.text)
        }

        String getOutputText() {
            json.get('outputText').asText()
        }

        String getExecutionResult() {
            json.get('executionResult').asText()
        }

        String getStacktraceText() {
            json.get('stacktraceText').asText()
        }
    }
}
