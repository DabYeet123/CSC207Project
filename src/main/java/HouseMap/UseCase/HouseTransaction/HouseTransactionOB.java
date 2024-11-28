package HouseMap.UseCase.HouseTransaction;


import App.PresenterInterface;
import HouseMap.Adapter.HouseMapController;
import HouseMap.DataObject.HouseObject;

public interface HouseTransactionOB extends PresenterInterface<HouseMapController> {


    void showMessage(String title, String text);

    void updateView(HouseTransactionOutput houseTransactionOutput);

}
