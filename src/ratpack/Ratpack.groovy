import ratpack.example.groovywebconsole.ReloadingThing
import ratpack.example.groovywebconsole.ScriptExecutionModule
import ratpack.example.groovywebconsole.ScriptExecutor
import ratpack.form.Form
import ratpack.groovy.template.TextTemplateModule

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {

    bindings {
        module ScriptExecutionModule
        module TextTemplateModule, { TextTemplateModule.Config config -> config.staticallyCompile = true }
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


