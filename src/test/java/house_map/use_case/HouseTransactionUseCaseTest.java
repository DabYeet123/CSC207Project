package house_map.use_case;

import DataObjects.UserObject;
import house_map.data_object.HouseObject;
import house_map.use_case.house_transaction.*;
import house_map.use_case.pop_up_transaction.PopUpTransactionOutput;
import house_map.use_case.pop_up_transaction.PopUpTransactionOutputBoundary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HouseTransactionUseCaseTest {

    private UserObject user;

    @BeforeEach
    public void init() {
        this.user = new UserObject(
                10000, "Bob", "Jo", "123456", 0, "");
    }


    @Test
    public void testBuyHouse() {
        HouseObject house = new HouseObject("Good house", "193 Joe Street", 0, 0, 1000, "None");
        HouseTransactionInput input = new HouseTransactionInput(user, house, 0, true);

        HouseTransactionOutputBoundary housePresenter = new HouseTransactionOutputBoundary() {
            @Override
            public void showMessage(String title, String text) {

            }

            @Override
            public void updateView(HouseTransactionOutput houseTransactionOutput) {
                Assertions.assertEquals(1000, houseTransactionOutput.getHouseObject().getPrice());
                Assertions.assertEquals("Bob", houseTransactionOutput.getUser().getFirstName());
            }
        };

        PopUpTransactionOutputBoundary popupPresenter = new PopUpTransactionOutputBoundary() {
            @Override
            public void updateView(PopUpTransactionOutput popUpTransactionOutput) {

            }

            @Override
            public void showView() {

            }
        };

        HouseTransactionInputBoundary useCase = new HouseTransactionUseCase(popupPresenter, housePresenter);
        useCase.execute(input);
    }

    @Test
    public void testSellHouse() {
        HouseObject house = new HouseObject("Good house", "193 Joe Street", 0, 0, 1000, "None");
        HouseTransactionInput input = new HouseTransactionInput(user, house, 0, false);

        HouseTransactionOutputBoundary housePresenter = new HouseTransactionOutputBoundary() {
            @Override
            public void showMessage(String title, String text) {

            }

            @Override
            public void updateView(HouseTransactionOutput houseTransactionOutput) {
                Assertions.assertEquals(1000, houseTransactionOutput.getHouseObject().getPrice());
                Assertions.assertEquals("Bob", houseTransactionOutput.getUser().getFirstName());
            }
        };

        PopUpTransactionOutputBoundary popupPresenter = new PopUpTransactionOutputBoundary() {
            @Override
            public void updateView(PopUpTransactionOutput popUpTransactionOutput) {

            }

            @Override
            public void showView() {

            }
        };

        HouseTransactionInputBoundary useCase = new HouseTransactionUseCase(popupPresenter, housePresenter);
        useCase.execute(input);
    }

    @Test
    public void testConfirm() {
        HouseObject house = new HouseObject("Good house", "193 Joe Street", 0, 0, 1000, "None");
        HouseTransactionInput input1 = new HouseTransactionInput(user, house, 0, true);
        HouseTransactionInput input2 = new HouseTransactionInput(user, house, house.getPrice(), true);

        HouseTransactionOutputBoundary housePresenter = new HouseTransactionOutputBoundary() {
            @Override
            public void showMessage(String title, String text) {

            }

            @Override
            public void updateView(HouseTransactionOutput houseTransactionOutput) {
                Assertions.assertEquals(1000, houseTransactionOutput.getHouseObject().getPrice());
                Assertions.assertEquals("Bob", houseTransactionOutput.getUser().getFirstName());
            }
        };

        PopUpTransactionOutputBoundary popupPresenter = new PopUpTransactionOutputBoundary() {
            @Override
            public void updateView(PopUpTransactionOutput popUpTransactionOutput) {

            }

            @Override
            public void showView() {

            }
        };

        HouseTransactionInputBoundary useCase = new HouseTransactionUseCase(popupPresenter, housePresenter);
        useCase.execute(input1);
        useCase.execute(input2);
    }


}
