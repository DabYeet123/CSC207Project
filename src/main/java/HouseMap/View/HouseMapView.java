package HouseMap.View;
import HouseMap.DataObject.HouseObject;
import HouseMap.Adapter.HouseMapController;
import Views.ButtonMaker;
import Views.PanelMaker;
import Views.ViewMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseMapView extends ViewMaker {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static final Image map = Toolkit.getDefaultToolkit().getImage("src/main/java/HouseMap/HouseMap.png");
    private final Map<HouseObject, HouseButton> houseButtonMap;

    public HouseMapView(HouseMapController controller) {
        super("House Map", WIDTH, HEIGHT + 50);
        setLayout(null);
        JPanel panel = new PanelMaker(0, 0, WIDTH, HEIGHT, null, Color.black) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(map, 0, 0, this);  // Draw the map image at the top-left corner
            }
        };
        houseButtonMap = new HashMap<>();
        createHouseButtons(panel, controller, houseButtonMap);
        add(panel);

        JPanel buttonPanel = new PanelMaker(0, HEIGHT, WIDTH, 50, null, null);
        JButton backButton = new ButtonMaker("Back",
                WIDTH - 120,  12, 85, 25) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.back();
            }
        };
        buttonPanel.add(backButton);
        add(buttonPanel);
    }

    public void createHouseButtons(JPanel panel, HouseMapController controller,Map<HouseObject, HouseButton> houseButtonMap) {
        List<HouseObject> houses = controller.getHouses();
        List<HouseButton> buttons = new ArrayList<>();
        for(HouseObject houseObject : houses) {
            HouseButton button = new HouseButton(houseObject, panel, controller);
            buttons.add(button);
            houseButtonMap.put(houseObject, button);
        }

    }

    public void updateView(HouseObject house, String text) {
        houseButtonMap.get(house).getHousePopUp().updateView(text);
    }

}
