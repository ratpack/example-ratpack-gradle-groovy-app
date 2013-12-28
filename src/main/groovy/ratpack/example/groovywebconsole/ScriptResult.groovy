package ratpack.example.groovywebconsole

import groovy.transform.CompileStatic

@CompileStatic
class ScriptResult {
    String executionResult
    String outputText
    String stacktraceText
}
