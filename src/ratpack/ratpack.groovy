import ratpack.example.groovywebconsole.ReloadingThing
import ratpack.example.groovywebconsole.ScriptExecutionModule
import ratpack.example.groovywebconsole.ScriptExecutor
import ratpack.groovy.RatpackScript
import ratpack.groovy.Template
import ratpack.groovy.templating.TemplatingModule

RatpackScript.ratpack {

    modules {
        register new ScriptExecutionModule()
        get(TemplatingModule).staticallyCompile = true
    }

    handlers {
        get {
            render Template.groovyTemplate("skin.html", title: "Groovy Web Console")
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


