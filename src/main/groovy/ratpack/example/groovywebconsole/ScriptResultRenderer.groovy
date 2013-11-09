package ratpack.example.groovywebconsole

import ratpack.handling.Context
import ratpack.render.Renderer

import static groovy.json.JsonOutput.toJson

class ScriptResultRenderer implements Renderer<ScriptResult> {

    @Override
    Class<ScriptResult> getType() {
        ScriptResult
    }

    @Override
    void render(Context context, ScriptResult result) {
        context.with {
            respond byContent.
                    type("application/json") {
                        response.send toJson(result)
                    }
        }
    }
}
