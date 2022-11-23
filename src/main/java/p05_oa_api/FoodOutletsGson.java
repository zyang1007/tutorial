package p05_oa_api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodOutletsGson {
    private static Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        List<String> seattle = FoodOutletsGson.getRelevantFoodOutlets11("Seattle", 140);
        System.out.println(seattle);
    }

    private static List<String> getRelevantFoodOutlets11(String city, int maxCost) throws IOException {
        List<String> res = new ArrayList<>();
        String BASE_URL = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city;
        String URL_Addr = BASE_URL + "&page=1";

        String resBody = callURL(URL_Addr);
        List<String> strings = processData(resBody, maxCost);
        res.addAll(strings);

        FoodOutlet foodOutlet = gson.fromJson(resBody, FoodOutlet.class);
        int total_pages = foodOutlet.getTotal_pages();

        for (int i = 2; i <= total_pages; i++) {
            URL_Addr = BASE_URL + "&page=" + i;
            resBody = callURL(URL_Addr);
            strings = processData(resBody, maxCost);
            res.addAll(strings);
        }

        return res;
    }

    private static List<String> processData(String resBody, int maxCost) {
        FoodOutlet foodOutlet = gson.fromJson(resBody, FoodOutlet.class);
        List<Data> dataList = foodOutlet.getData();
        List<String> collect = dataList.stream()
                .filter(e -> e.getEstimated_cost() <= maxCost)
                .map(e -> e.getName())
                .collect(Collectors.toList());

        return collect;
    }

    private static String callURL(String url_addr) throws IOException {
        URL url = new URL(url_addr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = br.readLine();

        return line;
    }

    static class FoodOutlet {
        int page, per_page, total, total_pages;
        List<Data> data;

        public int getTotal_pages() {
            return total_pages;
        }

        public List<Data> getData() {
            return data;
        }
    }

    static class Data {
        String city, name;
        int id, estimated_cost;
        UserRating userRating;

        public String getName() {
            return name;
        }

        public int getEstimated_cost() {
            return estimated_cost;
        }
    }

    static class UserRating {
        int votes;
        float average_rating;
    }
}
