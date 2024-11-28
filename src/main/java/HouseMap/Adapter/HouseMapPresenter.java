package HouseMap.Adapter;

import HouseMap.DataObject.HouseObject;
import HouseMap.UseCase.HouseTransaction.HouseTransactionOB;
import HouseMap.UseCase.HouseTransaction.HouseTransactionOutput;
import HouseMap.View.HouseMapView;

import javax.swing.*;

public class HouseMapPresenter implements HouseTransactionOB {

    private final HouseMapView houseMapView;

    public HouseMapPresenter(HouseMapController controller) {
        this.houseMapView = new HouseMapView(controller);
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



    @Override
    public void showMessage(String title, String text) {
        JOptionPane.showMessageDialog(
                houseMapView,
                text,
                title,
                JOptionPane.WARNING_MESSAGE
        );
    }

    @Override
    public void updateView(HouseTransactionOutput houseTransactionOutput) {
        houseMapView.updateView(houseTransactionOutput.getHouseObject(), houseTransactionOutput.getHouseObject().getOwner());
    }

}
