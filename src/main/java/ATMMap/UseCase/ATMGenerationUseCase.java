package ATMMap.UseCase;

import ATMMap.Adapter.ATMMapPresenter;
import ATMMap.View.ATMMapView;
import ATMMap.API.NominatimAPI;
import ATMMap.API.StaticMapAPI;
import ATMMap.DataObject.ATMObject;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;

import static ATMMap.API.NominatimAPI.getATMCoordinates;

public class ATMGenerationUseCase implements  ATMGenerationIB {

    private final ATMMapPresenter atmMapPresenter;

    public ATMGenerationUseCase(ATMMapPresenter atmMapPresenter) {
        this.atmMapPresenter = atmMapPresenter;
    }

    @Override
    public void generateATMMap(ATMGenerationInput atmGenerationInput) {
        String address = atmGenerationInput.getAddress();
        JPanel mapPanel = generateMap(address);
        atmMapPresenter.updateView(new ATMGenerationOutput(mapPanel));
    }

    public JPanel generateMap(String address) {
        double[] addressCoordinate = NominatimAPI.getCoordinates(address);

        double lon = addressCoordinate[0];
        double lat = addressCoordinate[1];
        Image map = StaticMapAPI.generateMap(lon, lat, 600, 600, ATMObject.ZOOM);
        JPanel panel = new PanelMaker(0, 0, ATMMapView.WIDTH, ATMMapView.HEIGHT, null, null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(map, 0, 0, this);  // Draw the map image at the top-left corner
            }
        };
        generateATM(address, panel);
        //add(panel);

        return panel;
    }

    public void generateATM(String address, JPanel panel) {
        try {
            double[] addressCoordinate = NominatimAPI.getCoordinates(address);

            double lon = addressCoordinate[0];
            double lat = addressCoordinate[1];
            // Get ATMs
            String atmJson = getATMCoordinates(lon, lat,  1000); // Search within 1 km radius

            NominatimAPI.createATMS(atmJson, lon, lat, panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
