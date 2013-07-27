import groovywebconsole.ReloadingThing
import groovywebconsole.ScriptExecutionModule
import groovywebconsole.ScriptExecutor
import org.ratpackframework.groovy.templating.TemplateRenderer

import static groovy.json.JsonOutput.toJson
import static org.ratpackframework.groovy.RatpackScript.ratpack

ratpack {

    modules {
        register new ScriptExecutionModule()
    }

    handlers {
        get { TemplateRenderer renderer ->
            renderer.render "skin.html", title: "Groovy Web Console"
        }

        post("execute") { ScriptExecutor scriptExecutor ->
            def script = request.form.script
            def result = scriptExecutor.execute(script)
            response.send "application/json", toJson(result)
        }

        get("reloadexample") {
            response.send new ReloadingThing().toString()
        }

        assets "public"
    }

}


