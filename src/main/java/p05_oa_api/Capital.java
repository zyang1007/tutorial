package p05_oa_api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Capital {

    public static void main(String[] args) {
        String capital = Capital.getCapital("Canada");
        System.out.println("Capital of Canada:  " + capital);
    }

    public static String getCapital(String country) {
        try {
            Gson gson = new Gson();
            String co = country.replace(" ", "%20");
            URL url = new URL("https://jsonmock.hackerrank.com/api/countries?name=" + co);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = br.readLine();
            System.out.println("Line: " + line);

            Country c= gson.fromJson(line, Country.class);
            Data data = c.data.get(0);
            String capital = data.capital;

            return capital;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "-1";
    }

    static class Country {
        int page, per_page, total, total_pages;
        List<Data> data;
    }

    static class Data {
        String name, capital;
        List<String> callingCodes;
    }
}
