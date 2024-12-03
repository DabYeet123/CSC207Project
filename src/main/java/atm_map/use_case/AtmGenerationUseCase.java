package atm_map.use_case;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import atm_map.adapter.AtmMapPresenter;
import atm_map.api.NominatimAPI;
import atm_map.api.StaticMapAPI;
import atm_map.view.AtmMapView;
import atm_map.view_maker.PanelMaker;

/**
 * The use case for Atm Generation.
 */
public class AtmGenerationUseCase implements AtmGenerationInputBoundary {

    private final AtmMapPresenter atmMapPresenter;
    private final NominatimAPI nominatimApi;
    private final StaticMapAPI staticMapApi;

    public AtmGenerationUseCase(AtmMapPresenter atmMapPresenter) {
        this.atmMapPresenter = atmMapPresenter;
        this.nominatimApi = new NominatimAPI();
        this.staticMapApi = new StaticMapAPI();
    }

    @Override
    public void generateAtmMap(AtmGenerationInput atmGenerationInput) {
        final String address = atmGenerationInput.getAddress();
        final JPanel mapPanel = generateMap(address);
        atmMapPresenter.updateView(new AtmGenerationOutput(mapPanel));
    }

    @Override
    public JPanel generateMap(String address) {
        final double[] addressCoordinate = nominatimApi.getCoordinates(address);

        final double lon = addressCoordinate[0];
        final double lat = addressCoordinate[1];
        final Image map = staticMapApi.generateMap(lon, lat, AtmMapView.WIDTH, AtmMapView.HEIGHT);
        final JPanel panel = new PanelMaker(0, 0, AtmMapView.WIDTH, AtmMapView.HEIGHT, null, null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(map, 0, 0, this);
            }
        };
        generateAtm(address, panel);

        return panel;
    }

    @Override
    public void generateAtm(String address, JPanel panel) {

            final double[] addressCoordinate = nominatimApi.getCoordinates(address);

            final double lon = addressCoordinate[0];
            final double lat = addressCoordinate[1];
            // Get ATMs
            final String atmJson = nominatimApi.getAtmCoordinates(lon, lat, 1000);
            // Creates the ATM objects
            nominatimApi.createAtms(atmJson, lon, lat, panel);

    }

    public boolean checkValidity(String address) {
        return nominatimApi.getCoordinates(address) == null;
    }
}
