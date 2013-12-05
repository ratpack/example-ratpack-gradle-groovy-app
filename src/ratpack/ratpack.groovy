import ratpack.example.groovywebconsole.ReloadingThing
import ratpack.example.groovywebconsole.ScriptExecutionModule
import ratpack.example.groovywebconsole.ScriptExecutor
import ratpack.groovy.templating.Template
import ratpack.groovy.templating.TemplatingModule
import static ratpack.groovy.Groovy.*
import static ratpack.form.Forms.form

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
            response.send new ReloadingThing().toString()
        }

        assets "public"
    }

}


