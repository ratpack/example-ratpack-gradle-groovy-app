import groovy.json.JsonOutput
import groovywebconsole.ReloadingThing
import groovywebconsole.ScriptExecutor
import groovywebconsole.ScriptResult
import org.ratpackframework.groovy.templating.TemplateRenderer

import static org.ratpackframework.groovy.RatpackScript.ratpack

ratpack {
    routing {
        get {
            get(TemplateRenderer).render "skin.html", title: "Groovy Web Console"
        }

        post("execute") {
            String script = request.form.get("script")[0]
            ScriptResult result = new ScriptExecutor().execute(script)
            response.send "application/json", JsonOutput.toJson(result)
        }

        get("reloadexample") {
            response.send new ReloadingThing().toString()
        }

        assets("public")
    }
}


