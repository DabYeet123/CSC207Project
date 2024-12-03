package oldstuff.Views.ATM.ATMMap;

import oldstuff.Views.appold.PresenterInterface;
import oldstuff.Views.Functionality.FunctionalityView;

import javax.swing.*;

public class ATMMapPresenter implements PresenterInterface<ATMMapController> {

    private final JFrame atmMapView;

    public ATMMapPresenter(ATMMapController controller){
        ATMMapView view = new ATMMapView(controller);
        this.atmMapView = new FunctionalityView(view, controller);
    }

    @Override
    public void showView(){
        atmMapView.setVisible(true);
    }

    @Override
    public void disposeView(){
        atmMapView.setVisible(false);
        atmMapView.dispose();
    }
}
