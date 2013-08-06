package groovywebconsole

import com.google.inject.AbstractModule

class ScriptExecutionModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ScriptExecutor).to(GroovyScriptExecutor)
        bind(ScriptResultRenderer)
    }
}
