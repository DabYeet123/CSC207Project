package atm_map.api;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class StaticMapAPI {

    public static final int ZOOM = 15;

    /**
     * Generates the image of the map given the coordinates.
     * @param lon the longitude of the map
     * @param lat the latitude of the map
     * @param width the width of the map
     * @param height the height of the map
     * @return an image of the map with the given size at the given coordinate
     */
    public Image generateMap(double lon, double lat, int width, int height) {
        try {
            // Construct the URL with parameters for the Static Maps API
            final String apiKey = getKey(); // Replace with your actual API key

            // Build the markers string for all ATM locations
            final String baseURL = "https://maps.googleapis.com/maps/api/staticmap?";
            final String centerString = "?center=" + lat + "," + lon;
            final String markerString = "&markers=" + lat + "," + lon;
            final String zoomString = "&zoom=" + ZOOM;
            final String sizeString = "&size=" + width + "x" + height;
            final String apiKeyString = "&key=" + apiKey;
            // Construct the final URL for the Static Map API
            final String urlString = baseURL + centerString + markerString + zoomString + sizeString + apiKeyString;
            System.out.println(urlString);
            // Fetch the image from the URL
            final URL url = new URL(urlString);
            // Read the image from the URL;

            return ImageIO.read(url);

        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return null;
    }

    public String getKey() {
        String encriptString = "CK|cU{EyHhO";
        encriptString += "-HoE8Uh9j[bQMC";
        encriptString += "-Nw3LLG9i8kJ<H";
        return decript(encriptString);
    }

    public String decript(String encriptString) {
        final StringBuilder key = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            key.append((char) (encriptString.charAt(i) - 2));
        }
        key.append("-");
        for (int i = 0; i < 13; i++) {
            key.append((char) (encriptString.charAt(i + 12) - 1));
        }
        key.append("-");
        for (int i = 0; i < 13; i++) {
            key.append((char) (encriptString.charAt(i + 12 + 14) - 3));
        }
        return key.toString();
    }
    
}
