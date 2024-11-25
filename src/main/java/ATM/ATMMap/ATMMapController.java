package ATM.ATMMap;

import ATM.DataObject.ATMObject;
import DataObjects.UserObject;
import LogIn.Welcome.WelcomeController;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;

import static ATM.ATMMap.NominatimAPI.getATMCoordinates;

public class ATMMapController {

    UserObject loggedInUser;
    private WelcomeController welcomeController;
    private final ATMMapPresenter atmMapPresenter;

    public ATMMapController(UserObject user) throws Exception {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.atmMapPresenter = new ATMMapPresenter(this);
    }

    //Temporary
    public ATMMapController() throws Exception {
        this.atmMapPresenter = new ATMMapPresenter(this);
    }

    public void launch(){
        atmMapPresenter.showView();
    }

    public void logOutTriggered(){
        atmMapPresenter.disposeView();
        welcomeController.launch();
    }

    public void generateATM(String address, JPanel panel) throws Exception {
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

    public JPanel generatePanel(String address) throws Exception {
        double[] addressCoordinate = NominatimAPI.getCoordinates(address);

        double lon = addressCoordinate[0];
        double lat = addressCoordinate[1];
        Image map = StaticMapAPI.generateMap(lon, lat, 600, 600, ATMObject.ZOOM);
        JPanel panel = new PanelMaker(0, 0, ATMMapView.WIDTH, ATMMapView.HEIGHT, null) {
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


}
