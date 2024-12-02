package house_map.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import house_map.adapter.HouseMapController;
import house_map.data_object.HouseObject;
import atm_map.view_maker.ButtonMaker;
import atm_map.view_maker.PanelMaker;
import atm_map.view_maker.ViewMaker;

/**
 * The View to show the house map.
 */
public class HouseMapView extends ViewMaker {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int SEARCHROOM = 50;
    public static final Rectangle BACKBUTTON = new Rectangle(WIDTH - 120, HEIGHT - 25, 85, 25);
    private static final Image HOUSEMAP = Toolkit.getDefaultToolkit().getImage("src/main/java/HouseMap/HouseMap.png");
    private final Map<HouseObject, HouseButton> houseButtonMap;

    public HouseMapView(HouseMapController controller) {
        super("House Map", WIDTH, HEIGHT + SEARCHROOM);
        setLayout(null);
        final PanelMaker panel = new PanelMaker(0, 0, WIDTH, HEIGHT, null, Color.black) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(HOUSEMAP, 0, 0, this);
            }
        };
        houseButtonMap = new HashMap<>();
        createHouseButtons(panel, controller, houseButtonMap);
        add(panel);

        final PanelMaker buttonPanel = new PanelMaker(0, HEIGHT, WIDTH, 50, null, null);
        final ButtonMaker backButton = new ButtonMaker("Back",
                BACKBUTTON.x, BACKBUTTON.y, BACKBUTTON.width, BACKBUTTON.height) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.back();
            }
        };
        buttonPanel.add(backButton);
        add(buttonPanel);
    }

    /**
     * Creates the hoverable button for each house object.
     * @param panel the panel where the button will be displayed
     * @param controller the controller for the houseMapController
     * @param houseButtonMap the mapping that associated the house object to the house button
     */
    public void createHouseButtons(
            PanelMaker panel, HouseMapController controller, Map<HouseObject, HouseButton> houseButtonMap) {
        final List<HouseObject> houses = controller.getHouses();
        final List<HouseButton> buttons = new ArrayList<>();
        for (HouseObject houseObject : houses) {
            final HouseButton button = new HouseButton(houseObject, panel, controller);
            buttons.add(button);
            houseButtonMap.put(houseObject, button);
        }

    }

    /**
     * Updates the house text based on the the text given.
     * @param house the house object to be updated
     * @param text the text to be updated
     */
    public void updateView(HouseObject house, String text) {
        houseButtonMap.get(house).getHousePopUp().updateView(text);
    }

}
