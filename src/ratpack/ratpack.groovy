import groovy.json.JsonOutput
import groovywebconsole.ReloadingThing
import groovywebconsole.ScriptExecutor
import groovywebconsole.ScriptResult
import org.ratpackframework.groovy.templating.TemplateRenderer

import static org.ratpackframework.groovy.RatpackScript.ratpack
import static org.ratpackframework.routing.Handlers.assets

ratpack {
    routing {
        get("") {
            context.get(TemplateRenderer).render "skin.html", title: "Groovy Web Console"
        }

        post("execute") {
            String script = request.form.get("script")[0]
            ScriptResult result = new ScriptExecutor().execute(script)
            response.send "application/json", JsonOutput.toJson(result)
        }

        get("reloadexample") {
            response.send new ReloadingThing().toString()
        }

        route assets("public")
    }
}


