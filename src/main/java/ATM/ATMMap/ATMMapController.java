package ATM.ATMMap;

import ATM.DataObject.ATMObject;
import userdataobject.UserObject;
import Functionality.FunctionalityController;
import login.loggedin.LoggedInController;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;

import static ATM.ATMMap.NominatimAPI.getATMCoordinates;

public class ATMMapController extends FunctionalityController {

    UserObject loggedInUser;
    private final ATMMapPresenter atmMapPresenter;
    private final LoggedInController loggedInController;

    public ATMMapController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        this.loggedInController = loggedInController;
        this.atmMapPresenter = new ATMMapPresenter(this);
    }

    public void launch(){
        atmMapPresenter.showView();
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

    public JPanel generatePanel(String address) {
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


    @Override
    public void back() {
        atmMapPresenter.disposeView();
        loggedInController.launch();
    }
}
