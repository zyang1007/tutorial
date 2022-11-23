package p05_oa_api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodOutletsJava11 {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        List<String> seattle = FoodOutletsJava11.getRelevantFoodOutlets11("Seattle", 140);
        System.out.println(seattle);
    }

    public static List<String> getRelevantFoodOutlets11(String city, int maxCost) throws IOException,
            URISyntaxException, InterruptedException {
        List<String> res = new ArrayList<>();

        String BASE_URL = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city;

        String URL_Addr = BASE_URL + "&page=1";

        // processes page 1
        String resBody = callURL(URL_Addr);
        List<String> strings = processData(resBody, maxCost);
        res.addAll(strings);

        // get total_pages
        JsonObject jsonBody = (JsonObject) JsonParser.parseString(resBody);
        int total_pages = Integer.parseInt(jsonBody.get("total_pages").getAsString());

        // processes rest page: 2 - last page
        for (int i = 2; i <= total_pages; i++) {
            URL_Addr = BASE_URL + "&page=" + i;
            resBody = callURL(URL_Addr);
            strings = processData(resBody, maxCost);
            res.addAll(strings);
        }

        return res;
    }

    private static String callURL(String URL_Addr) throws IOException, InterruptedException, URISyntaxException {
        HttpClient httpClient = HttpClient.newBuilder().build();  // define httpClient
        HttpRequest request = HttpRequest.newBuilder(new URI(URL_Addr))  // define a HttpRequest
                .header("User-Agent", "Java HttpClient").header("Accept", "*/*")  // sets header
                .timeout(Duration.ofSeconds(5))  // sets timeout
                .version(HttpClient.Version.HTTP_2).build(); // set version

        // call api
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // HTTP allows duplicated Header，so one Header can have multiple correspond Values
        Map<String, List<String>> headers = response.headers().map();
        for (String header : headers.keySet()) {
            System.out.println(header + ": " + headers.get(header).get(0));
        }

        // check status code
        if (response.statusCode() != 200) {
            throw new RuntimeException("bad response");
        }

        // only return response body
        return response.body();
    }

    private static List<String> processData(String resBody, int maxCost) {
        List<String> res = new ArrayList<>();

        JsonObject jsonBody = (JsonObject) JsonParser.parseString(resBody);

        JsonArray jsonArray = jsonBody.get("data").getAsJsonArray();
        jsonArray.forEach(d -> {
            int estimated_cost = d.getAsJsonObject().get("estimated_cost").getAsInt();
            if (estimated_cost <= maxCost) {
                res.add(d.getAsJsonObject().get("name").getAsString());
            }
        });

        return res;
    }
}
