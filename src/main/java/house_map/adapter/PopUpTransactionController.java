package house_map.adapter;

import app.ControllerInterface;
import cardandexchange.dataObject.Card;
import userdataobject.UserObject;
import house_map.use_case.pop_up_transaction.PopUpTransactionInput;
import house_map.use_case.pop_up_transaction.PopUpTransactionUseCase;

/**
 * Controller for the PopupTransaction Menu.
 */
public class PopUpTransactionController implements ControllerInterface {

    private final UserObject loggedInUser;
    private final PopUpTransactionPresenter popUpTransactionPresenter;
    private final PopUpTransactionUseCase popUpTransactionUseCase;
    private final HouseMapController houseMapController;

    /**
     * Constructs a PopUpTransactionController with the specified user and house map controller.
     *
     * @param user The logged-in user.
     * @param houseMapController The controller for the HouseMap module.
     */
    public PopUpTransactionController(UserObject user, HouseMapController houseMapController) {
        this.loggedInUser = user;
        this.houseMapController = houseMapController;
        this.popUpTransactionPresenter = new PopUpTransactionPresenter(user, this);
        this.popUpTransactionUseCase = new PopUpTransactionUseCase(user, popUpTransactionPresenter);
    }

    /**
     * Launches the pop-up transaction view.
     */
    @Override
    public void launch() {
        popUpTransactionPresenter.showView();
    }

    /**
     * Closes the pop-up transaction view.
     */
    public void closeView() {
        popUpTransactionPresenter.disposeView();
    }

    /**
     * Updates the balance based on the specified price.
     *
     * @param price The price to adjust the balance.
     */
    public void updateBalance(double price) {
        popUpTransactionUseCase.updateBalance(price);
    }

    /**
     * Confirms a transaction by interacting with the HouseMapController.
     *
     * @param price The price to confirm the transaction.
     */
    public void confirmTransaction(double price) {
        houseMapController.execute(null, price, false);
    }

    /**
     * Sets the current card to be used for the transaction.
     *
     * @param card The card to be set as the current card.
     */
    public void setCurrentCard(Card card) {
        popUpTransactionUseCase.setCurrentCard(card);
    }

    public PopUpTransactionPresenter getPopUpTransactionPresenter() {
        return popUpTransactionPresenter;
    }
}
