package House.HouseMap;

import appold.PresenterInterface;
import userdataobject.UserObject;
import Functionality.FunctionalityView;

import javax.swing.*;

public class HouseMapPresenter implements PresenterInterface<HouseMapController> {

    private final JFrame houseMapView;

    public HouseMapPresenter(UserObject user, HouseMapController controller) {
        HouseMapView houseView = new HouseMapView(user,controller);
        this.houseMapView = new FunctionalityView(houseView, controller);
    }

    @Override
    public void showView(){
        houseMapView.setVisible(true);
    }

    @Override
    public void disposeView(){
        houseMapView.setVisible(false);
        houseMapView.dispose();
    }
}
