import ratpack.example.groovywebconsole.ReloadingThing
import ratpack.example.groovywebconsole.ScriptExecutionModule
import ratpack.example.groovywebconsole.ScriptExecutor
import ratpack.form.Form
import ratpack.groovy.templating.TemplatingModule

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {

    bindings {
        add new ScriptExecutionModule()
        config(TemplatingModule) { it.staticallyCompile = true }
    }

    handlers {
        get {
            render groovyTemplate("skin.html", title: "Groovy Web Console")
        }

        post("execute") { ScriptExecutor scriptExecutor ->
            Form form = parse(Form)
            String script = form.script
            render scriptExecutor.execute(script)
        }

        get("reloadexample") {
            render new ReloadingThing().toString()
        }

        assets "public"
    }

}


