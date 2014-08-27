package ratpack.example.groovywebconsole

import groovy.transform.CompileStatic
import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.codehaus.groovy.runtime.StackTraceUtils

@CompileStatic
class GroovyScriptExecutor implements ScriptExecutor {

    ScriptResult execute(String scriptText) {

        def encoding = 'UTF-8'
        def stream = new ByteArrayOutputStream()
        def printStream = new PrintStream(stream, true, encoding)

        def stacktrace = new StringWriter()
        def errWriter = new PrintWriter(stacktrace)

        def aBinding = new Binding([out: printStream])

        List<MetaClassRegistryChangeEvent> emcEvents = []
        def listener = { MetaClassRegistryChangeEvent event ->
            emcEvents << event
        } as MetaClassRegistryChangeEventListener

        GroovySystem.metaClassRegistry.addMetaClassRegistryChangeEventListener listener

        def originalOut = System.out
        def originalErr = System.err

        System.setOut(printStream)
        System.setErr(printStream)

        def result = ""
        try {
            result = new GroovyShell(aBinding).evaluate(scriptText)
        } catch (MultipleCompilationErrorsException e) {
            stacktrace.append(e.message - 'startup failed, Script1.groovy: ')
        } catch (Throwable t) {
            StackTraceUtils.deepSanitize(t)
            t.printStackTrace(errWriter)
        } finally {
            System.setOut(originalOut)
            System.setErr(originalErr)

            GroovySystem.metaClassRegistry.removeMetaClassRegistryChangeEventListener listener
            emcEvents.each { MetaClassRegistryChangeEvent event ->
                GroovySystem.metaClassRegistry.removeMetaClass event.classToUpdate
            }
        }

        new ScriptResult(
                executionResult: result == null ? "" : result.toString(),
                outputText: stream.toString(encoding),
                stacktraceText: stacktrace.toString()
        )
    }

}
