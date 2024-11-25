package ATM.ATMMap;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import ATM.DataObject.ATMObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;

public class NominatimAPI {
    public static double[] getCoordinates(String address) throws Exception {
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" +
                java.net.URLEncoder.encode(address, "UTF-8");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "JavaApp")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body()).get(0); // Take the first result
        double lat = root.path("lat").asDouble();
        double lon = root.path("lon").asDouble();

        return new double[] {lon, lat};
    }

    public static String getATMCoordinates(double lon, double lat, double radius) throws Exception {
        String query = "[out:json];(node[\"amenity\"=\"atm\"](around:%f,%f,%f););out body;".formatted(radius, lat, lon);

        String url = "https://overpass-api.de/api/interpreter";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("data=" + query))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); // Parse JSON to get ATM details
    }

    public static void parseResponse(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        for (JsonNode node : rootNode.path("elements")) {
            double lat = node.path("lat").asDouble();
            double lon = node.path("lon").asDouble();
            String name = node.path("tags").path("name").asText("Unknown ATM");

            System.out.println("ATM: " + name + " at (" + lat + ", " + lon + ")");
        }
    }

    public static List<ATMObject> createATMS(String json, double centerLongitude, double centerLatitude, JPanel panel)
            throws Exception {
        List<ATMObject> ATMObjects = new ArrayList<ATMObject>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        for (JsonNode node : rootNode.path("elements")) {
            double lat = node.path("lat").asDouble();
            double lon = node.path("lon").asDouble();
            String name = node.path("tags").path("name").asText("Unknown ATM");

            int[] atmCoordinate = PixelConverter.calculateRelativePosition(centerLongitude, centerLatitude,
                    lon, lat, ATMObject.ZOOM);

            ATMObject atmObject = new ATMObject(name, ATMObject.CASHRESERVE, ATMObject.FEE,
                    ATMObject.centerX + atmCoordinate[0], ATMObject.centerY - atmCoordinate[1], panel);
            ATMObjects.add(atmObject);
        }

        return ATMObjects;
    }

    public static void main(String[] args) {
        try {
            String address = "40 St George St, Toronto, ON";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

