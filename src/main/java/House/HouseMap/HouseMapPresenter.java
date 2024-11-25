package House.HouseMap;

import ATM.ATMMap.ATMMapController;
import ATM.ATMMap.ATMMapView;

public class HouseMapPresenter {

    private final HouseMapView atmMapView;

    public HouseMapPresenter(HouseMapController controller) throws Exception {
        this.atmMapView = new HouseMapView(controller);
    }

    public void showView(){
        atmMapView.setVisible(true);
    }

    public void disposeView(){
        atmMapView.setVisible(false);
        atmMapView.dispose();
    }
}
