package house_map.adapter;

import javax.swing.JOptionPane;

import App.PresenterInterface;
import house_map.use_case.house_transaction.HouseTransactionOutput;
import house_map.use_case.house_transaction.HouseTransactionOutputBoundary;
import house_map.view.HouseMapView;

/**
 * The Presenter for the House Map.
 */
public class HouseMapPresenter implements PresenterInterface<HouseMapController>, HouseTransactionOutputBoundary {

    private final HouseMapView houseMapView;

    public HouseMapPresenter(HouseMapController controller) {
        this.houseMapView = new HouseMapView(controller);
    }

    @Override
    public void showView() {
        houseMapView.setVisible(true);
    }

    @Override
    public void disposeView() {
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
        houseMapView.updateView(houseTransactionOutput.getHouseObject(),
                houseTransactionOutput.getHouseObject().getOwner());
    }

}
