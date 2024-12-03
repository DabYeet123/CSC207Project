package oldstuff.Views.House.HouseMap;
import oldstuff.Views.userdataobject.UserObject;
import oldstuff.Views.House.DataObject.HouseController;
import oldstuff.Views.House.DataObject.HouseObject;
import oldstuff.Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HouseMapView extends PanelMaker {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static final Image map = Toolkit.getDefaultToolkit().getImage("src/main/java/House/HouseMap.png");
    private UserObject user;

    public HouseMapView(UserObject user, HouseMapController  controller) {
        super(0, 0, WIDTH, HEIGHT, null);
        HouseController houseController = new HouseController(user, controller);
        this.user = controller.loggedInUser;
        JPanel panel = new PanelMaker(0, 0, WIDTH, HEIGHT, null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(map, 0, 0, this);  // Draw the map image at the top-left corner
            }
        };

        List<HouseObject> houses = houseController.getHouses();
        for(HouseObject houseObject : houses) {
            houseObject.createButton(panel, houseController);
        }
        add(panel);
    }



}
