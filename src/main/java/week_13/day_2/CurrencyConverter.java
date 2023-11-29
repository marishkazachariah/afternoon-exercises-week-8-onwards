package week_13.day_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CurrencyConverter {
    private static final String API_KEY = "09d0fb0353b2bd057244835f";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter the amount: ");
            double amount = Double.parseDouble(reader.readLine());

            System.out.print("Enter the source currency (e.g., EUR): ");
            String sourceCurrency = reader.readLine().toUpperCase();

            System.out.print("Enter the target currency (e.g., USD): ");
            String targetCurrency = reader.readLine().toUpperCase();

            String apiUrl = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + sourceCurrency;

            double exchangeRate = getExchangeRate(apiUrl, targetCurrency);
            double convertedAmount = amount * exchangeRate;

            System.out.println("Exchange Rate: " + exchangeRate);
            System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    private static double getExchangeRate(String apiUrl, String targetCurrency) throws IOException, ParseException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        // Use json-simple library to parse the response
        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());
        JSONObject conversionRates = (JSONObject) jsonResponse.get("conversion_rates");

        if (conversionRates.containsKey(targetCurrency)) {
            return (Double) conversionRates.get(targetCurrency);
        } else {
            throw new IllegalArgumentException("Exchange rate not found for target currency: " + targetCurrency);
        }
    }
}
