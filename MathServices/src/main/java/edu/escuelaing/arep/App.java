package edu.escuelaing.arep;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        port(getPort());
        Gson gson = new Gson();
        get("/tan", (request, response) -> {
            double value = Double.parseDouble(request.queryParams("value"));
            MathResponse mathResponse = new MathResponse("tan", value, Math.tan(value));
            return gson.toJson(mathResponse);
        });
        get("/acos", (request, response) -> {
            double value = Double.parseDouble(request.queryParams("value"));
            MathResponse mathResponse = new MathResponse("acos", value, Math.acos(value));
            return gson.toJson(mathResponse);
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
