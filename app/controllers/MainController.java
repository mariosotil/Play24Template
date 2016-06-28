package controllers;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import play.Configuration;
import play.mvc.Controller;
import play.mvc.Result;

@Api(value = "hello", description = "API root")
public class MainController extends Controller {
    @Inject
    Configuration configuration;

    public Result hello() {
        Result result;

        try {
            String version = configuration.getString("application.version");
            result = ok(String.format("Andium API. Engine's version:%s", version));
        } catch (Exception e) {
            result = internalServerError(e.getMessage());
        }

        return result;
    }
}