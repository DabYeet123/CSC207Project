package ATMMap.Adapter;


import ATMMap.UseCase.ATMGenerationOB;
import ATMMap.UseCase.ATMGenerationOutput;
import ATMMap.View.ATMMapView;
import App.PresenterInterface;

import javax.swing.*;

public class ATMMapPresenter implements PresenterInterface<ATMMapController>, ATMGenerationOB {

    private final ATMMapView atmMapView;

    public ATMMapPresenter(ATMMapController controller){
        this.atmMapView = new ATMMapView(controller);
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

    @Override
    public void updateView(ATMGenerationOutput atmGenerationOutput) {
        JPanel mapPanel = atmGenerationOutput.getMapPanel();
        atmMapView.updateView(mapPanel);
    }
}
