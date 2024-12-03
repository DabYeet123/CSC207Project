package atm_map.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JPanel;

import atm_map.data_object.AtmFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Nominatim API which is responsible for getting all the atm information.
 */
public class NominatimAPI {

    public static final int ZOOM = 15;
    public static final double EARTH_RADIUS = 6378137;
    public static final double EARTH_CIRCUMFERENCE = 2 * Math.PI * EARTH_RADIUS;

    /**
     * Returns the coordinate in (x,y) from the address, this is based off of real world coordinates.
     * @param address the address
     * @return the coordinate in (x,y) from the address
     */
    public double[] getCoordinates(String address) {
        String url = null;
        try {
            url = "https://nominatim.openstreetmap.org/search?format=json&q="
                    + java.net.URLEncoder.encode(address, "UTF-8");
        }
        catch (UnsupportedEncodingException error) {
            throw new RuntimeException(error);
        }

        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "JavaApp")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException error) {
            throw new RuntimeException(error);
        }
        catch (InterruptedException error) {
            throw new RuntimeException(error);
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.body()).get(0);
        }
        catch (JsonProcessingException error) {
            throw new RuntimeException(error);
        }
        if (root == null) {
            return null;
        }
        final double lat = root.path("lat").asDouble();
        final double lon = root.path("lon").asDouble();

        return new double[] {lon, lat};
    }

    /**
     * Returns all the atms near a location.
     * @param lon the longitude
     * @param lat the latitude
     * @param radius the radius of Earth
     * @return a string consisting of all the information about the atms
     */
    public String getAtmCoordinates(double lon, double lat, double radius) {
        final String query = "[out:json];(node[\"amenity\"=\"atm\"](around:" + radius + "," + lat + "," + lon + "););out body;";


        final String url = "https://overpass-api.de/api/interpreter";

        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("data=" + query))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException error) {
            throw new RuntimeException(error);
        }
        catch (InterruptedException error) {
            throw new RuntimeException(error);
        }
        return response.body();
    }

    /**
     * Creates all the atm object that will show up on screen.
     * @param json the locations of all the atms
     * @param centerLongitude the longitude of where we want the map to be centered
     * @param centerLatitude the latitude of where we want the map to be centered
     * @param panel the panel where we want the atms to be displayed
     */
    public void createAtms(String json, double centerLongitude, double centerLatitude, JPanel panel) {
        final ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(json);
        }
        catch (JsonProcessingException error) {
            throw new RuntimeException(error);
        }
        final AtmFactory atmFactory = new AtmFactory();

        for (JsonNode node : rootNode.path("elements")) {
            final double lat = node.path("lat").asDouble();
            final double lon = node.path("lon").asDouble();
            final String name = node.path("tags").path("name").asText("Unknown ATM");

            final int[] atmCoordinate = calculateRelativePosition(centerLongitude, centerLatitude,
                    lon, lat, ZOOM);

            atmFactory.create(name, Math.round(Math.random() * 3000), AtmFactory.FEE,
                    AtmFactory.CENTERX + atmCoordinate[0], AtmFactory.CENTERY - atmCoordinate[1], panel);
        }

    }

    /**
     * Calculate the x-coordinate (longitude) in the Mercator projection.
     *
     * @param longitude Longitude in degrees
     * @return x-coordinate in meters
     */
    public double calculateX(double longitude) {
        // Convert longitude from degrees to radians
        final double lambda = Math.toRadians(longitude);

        // Calculate x using the Mercator formula
        return EARTH_RADIUS * lambda;
    }

    /**
     * Calculate the y-coordinate (latitude) in the Mercator projection.
     *
     * @param latitude Latitude in degrees
     * @return y-coordinate in meters
     */
    public double calculateY(double latitude) {
        // Convert latitude from degrees to radians
        final double phi = Math.toRadians(latitude);

        // Calculate y using the Mercator formula
        return EARTH_RADIUS * Math.log(Math.tan(Math.PI / 4 + phi / 2));
    }

    /**
     * Calculate the relative position of a location with respect to the center point in pixels.
     *
     * @param centerLongitude Longitude of the center in degrees
     * @param centerLatitude Latitude of the center in degrees
     * @param longitude Longitude in degrees
     * @param latitude Latitude in degrees
     * @return the relative location of the location with respect to the center location in pixels
     */
    public int[] calculateRelativePosition(double centerLongitude, double centerLatitude,
                                                  double longitude, double latitude, int zoom) {
        final int[] relativePosition = new int[2];

        // The scale factor that converts between distance in meters to pixels
        final double SCALE = (EARTH_CIRCUMFERENCE / (256 * Math.pow(2, zoom))) * Math.cos(centerLatitude);

        final double dx = calculateX(longitude) - calculateX(centerLongitude);
        final double dy = calculateY(latitude) - calculateY(centerLatitude);

        relativePosition[0] = (int) (dx / SCALE);
        relativePosition[1] = (int) (dy / SCALE);

        return relativePosition;
    }

}

