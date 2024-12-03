package oldstuff.Views.ATM.ATMMap;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class StaticMapAPI {

    public static Image generateMap(double lon, double lat, int width, int height, int zoom) {
        try {
            // Construct the URL with parameters for the Static Maps API
            String apiKey = getKey(); // Replace with your actual API key

            // Build the markers string for all ATM locations
            String baseURL = "https://maps.googleapis.com/maps/api/staticmap?";
            String centerString = "?center=" + lat +"," + lon;
            String markerString = "&markers=" + lat + "," + lon;
            String zoomString = "&zoom=" + zoom;
            String sizeString = "&size=" + width + "x" + height;
            String apiKeyString = "&key=" + apiKey;
            // Construct the final URL for the Static Map API
            String urlString = baseURL + centerString + markerString + zoomString + sizeString + apiKeyString;
            System.out.println(urlString);
            // Fetch the image from the URL
            URL url = new URL(urlString);
            // Read the image from the URL;

            return ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getKey() {
        String encriptString = "CK|cU{EyHhO";
        encriptString += "-HoE8Uh9j[bQMC";
        encriptString += "-Nw3LLG9i8kJ<H";
        return decript(encriptString);
    }

    public static String decript(String encriptString) {
        StringBuilder key = new StringBuilder();
        for(int i = 0; i < 11; i++) {
            key.append((char) (encriptString.charAt(i) - 2));
        }
        key.append("-");
        for( int i = 0; i < 13; i++) {
            key.append((char) (encriptString.charAt(i + 12) - 1));
        }
        key.append("-");
        for( int i = 0; i < 13; i++) {
            key.append((char) (encriptString.charAt(i + 12 + 14) - 3));
        }
        return key.toString();
    }
    
}
