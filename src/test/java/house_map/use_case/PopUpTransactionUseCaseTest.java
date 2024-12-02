package house_map.use_case;

import DataObjects.UserObject;
import house_map.data_object.HouseObject;
import house_map.use_case.house_transaction.*;
import house_map.use_case.pop_up_transaction.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PopUpTransactionUseCaseTest {

    private UserObject user;

    @BeforeEach
    public void init() {
        this.user = new UserObject(
                10000, "Bob", "Jo", "123456", 0, "");
    }

    @Test
    public void testExecution() {
        PopUpTransactionInput input = new PopUpTransactionInput(1000);

        PopUpTransactionOutputBoundary popupPresenter = new PopUpTransactionOutputBoundary() {
            @Override
            public void updateView(PopUpTransactionOutput popUpTransactionOutput) {
                Assertions.assertEquals(1000, popUpTransactionOutput.getPrice());
            }

            @Override
            public void showView() {
                Assertions.assertTrue(true);
            }
        };

        PopUpTransactionInputBoundary useCase = new PopUpTransactionUseCase(user, popupPresenter);
        useCase.execute(input);
    }
}
