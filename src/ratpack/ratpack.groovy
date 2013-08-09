import groovywebconsole.ReloadingThing
import groovywebconsole.ScriptExecutionModule
import groovywebconsole.ScriptExecutor

import static org.ratpackframework.groovy.RatpackScript.ratpack
import static org.ratpackframework.groovy.Template.groovyTemplate

ratpack {

    modules {
        register new ScriptExecutionModule()
    }

    handlers {
        get {
            render groovyTemplate("skin.html", title: "Groovy Web Console")
        }

        post("execute") { ScriptExecutor scriptExecutor ->
            def script = request.form.script
            render scriptExecutor.execute(script)
        }

        get("reloadexample") {
            response.send new ReloadingThing().toString()
        }

        assets "public"
    }

}


