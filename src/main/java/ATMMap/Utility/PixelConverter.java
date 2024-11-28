package ATMMap.Utility;

import java.util.Arrays;

public class PixelConverter {

    private static final double EARTH_RADIUS = 6378137;
    private static final double EARTH_CIRCUMFERENCE = 2 * Math.PI * EARTH_RADIUS;

    public static void main(String[] args) {
        // Example coordinates
        double cenLat = 43.6541316; // Latitude in degrees (New York City)
        double cenLon = -79.4005452; // Longitude in degrees (New York City)
        double lat = 43.6645752;
        double lon = -79.3997156;

        double scale = (EARTH_CIRCUMFERENCE / ( 256 * Math.pow(2, 15))) * Math.cos(cenLat);


        // Convert latitude and longitude to Mercator projection (x, y)
        double cx = calculateX(cenLon);
        double cy = calculateY(cenLat);
        double x = calculateX(lon);
        double y = calculateY(lat);
        System.out.printf("CX: %.2f meters, CY: %.2f meters%n", cx, cy);
        System.out.printf("X: %.2f meters, Y: %.2f meters%n", x, y);
        int finalX = (int) ((x - cx) / scale);
        int finalY = (int)((y - cy) / scale);

        System.out.printf("X: %.2f meters, Y: %.2f meters%n", x-cx, y-cy);
        System.out.printf("X: %d pixels, Y: %d pixels%n", finalX, finalY);

        System.out.println(Arrays.toString(calculateRelativePosition(cenLon, cenLat, lon, lat, 15)));
    }

    /**
     * Calculate the x-coordinate (longitude) in the Mercator projection.
     *
     * @param longitude Longitude in degrees
     * @return x-coordinate in meters
     */
    public static double calculateX(double longitude) {
        // Convert longitude from degrees to radians
        double lambda = Math.toRadians(longitude);

        // Calculate x using the Mercator formula
        return EARTH_RADIUS * lambda;
    }

    /**
     * Calculate the y-coordinate (latitude) in the Mercator projection.
     *
     * @param latitude Latitude in degrees
     * @return y-coordinate in meters
     */
    public static double calculateY(double latitude) {
        // Convert latitude from degrees to radians
        double phi = Math.toRadians(latitude);

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
    public static int[] calculateRelativePosition(double centerLongitude, double centerLatitude,
                                                  double longitude, double latitude, int zoom) {
        int[] relativePosition = new int[2];

        //The scale factor that converts between distance in meters to pixels
        double SCALE = (EARTH_CIRCUMFERENCE / ( 256 * Math.pow(2, zoom))) * Math.cos(centerLatitude);

        double dx = calculateX(longitude) - calculateX(centerLongitude); //The horizontal distance between the two locations
        double dy = calculateY(latitude) - calculateY(centerLatitude); //The vertical distance between the two locations

        relativePosition[0] = (int) (dx / SCALE);
        relativePosition[1] = (int) (dy / SCALE);

        return relativePosition;
    }
}
