package House.HouseMap;
import DataObjects.UserObject;
import House.DataObject.HouseController;
import House.DataObject.HouseObject;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HouseMapView extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private UserObject user;

    public HouseMapView(HouseMapController controller) {
        super("House Map");
        HouseController houseController = new HouseController(user);
        this.user = controller.loggedInUser;
        pack();
        Insets insets = getInsets();
        int frameWidth = WIDTH + insets.left + insets.right;
        int frameHeight = HEIGHT + insets.top + insets.bottom;
        setSize(frameWidth, frameHeight); // Set the size of the frame

        //JPanel panel = new PanelMaker(0, 0, width, height, null);

        Image map = Toolkit.getDefaultToolkit().getImage("src/main/java/House/HouseMap.png");
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

        this.add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close Application on clicking X
        setLocationRelativeTo(null); //Puts the Menu in the center

    }

}
