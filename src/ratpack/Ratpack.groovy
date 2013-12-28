import ratpack.example.groovywebconsole.ReloadingThing
import ratpack.example.groovywebconsole.ScriptExecutionModule
import ratpack.example.groovywebconsole.ScriptExecutor
import ratpack.groovy.templating.TemplatingModule

import static ratpack.form.Forms.form
import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {

    modules {
        register new ScriptExecutionModule()
        get(TemplatingModule).staticallyCompile = true
    }

    handlers {
        get {
            render groovyTemplate("skin.html", title: "Groovy Web Console")
        }

        post("execute") { ScriptExecutor scriptExecutor ->
            def form = parse form()
            def script = form.script
            render scriptExecutor.execute(script)
        }

        get("reloadexample") {
            render new ReloadingThing().toString()
        }

        assets "public"
    }

}


