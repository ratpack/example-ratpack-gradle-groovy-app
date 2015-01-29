package ratpack.example.groovywebconsole

import com.google.inject.AbstractModule
import groovy.transform.CompileStatic

//@CompileStatic //TODO temporary remove due to GROOVY-7278
class ScriptExecutionModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ScriptExecutor).to(GroovyScriptExecutor)
        bind(ScriptResultRenderer)
    }
}
