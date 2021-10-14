package edu.escuelaing.arep;

import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        port(getPort());
        HashMap<String, Integer> connection = new HashMap<>();
        String firstInstance = "http://ec2-35-175-219-216.compute-1.amazonaws.com:4567/";
        String secondInstance = "http://ec2-3-91-71-243.compute-1.amazonaws.com:4567/";
        connection.put(firstInstance, 0);
        connection.put(secondInstance, 0);
        get("/tan", (request, response) -> {
            String value = request.queryParams("value");
            if (connection.get(firstInstance) <= connection.get(secondInstance)) {
                URL obj = new URL(firstInstance + "tan?value=" + value);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                String responseStr = getResponse(con);
                connection.put(firstInstance, connection.get(firstInstance) + 1);
                return new JSONObject(responseStr);
            }else {
                URL obj = new URL(secondInstance + "tan?value=" + value);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                String responseStr = getResponse(con);
                connection.put(secondInstance, connection.get(secondInstance) + 1);
                return new JSONObject(responseStr);
            }
        });
        get("/acos", (request, response) -> {
            String value = request.queryParams("value");
            if (connection.get(firstInstance) <= connection.get(secondInstance)) {
                URL obj = new URL(firstInstance + "acos?value=" + value);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                String responseStr = getResponse(con);
                connection.put(firstInstance, connection.get(firstInstance) + 1);
                return new JSONObject(responseStr);
            }else {
                URL obj = new URL(secondInstance + "acos?value=" + value);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                String responseStr = getResponse(con);
                connection.put(secondInstance, connection.get(secondInstance) + 1);
                return new JSONObject(responseStr);
            }
        });

    }

    static String getResponse(HttpURLConnection httpURLConnection) throws IOException {
        String responseStr;
        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine())!=null){
            response.append(inputLine);
        }
        in.close();
        responseStr = response.toString();
        return responseStr;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
