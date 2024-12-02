package atm_map.adapter;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import atm_map.use_case.AtmGenerationInput;
import atm_map.use_case.AtmGenerationUseCase;

/**
 * The Controller for the AtmMap.
 */
public class AtmMapController implements ControllerInterface {

    private final UserObject loggedInUser;
    private final AtmGenerationUseCase atmGenerationUseCase;
    private final AtmMapPresenter atmMapPresenter;
    private final LoggedInController loggedInController;

    public AtmMapController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        this.loggedInController = loggedInController;
        this.atmMapPresenter = new AtmMapPresenter(this);
        this.atmGenerationUseCase = new AtmGenerationUseCase(atmMapPresenter);
    }

    @Override
    public void launch() {
        atmMapPresenter.showView();
    }

    /**
     * Generates an ATM map for the specified address.
     *
     * @param address The address for which the ATM map should be generated.
     */
    public void generatePanel(String address) {
        atmGenerationUseCase.generateAtmMap(new AtmGenerationInput(address));
    }


    /**
     * Checks if the address is valid
     * @param address the address
     */
    public boolean checkValidity(String address) {
        return atmGenerationUseCase.checkValidity(address);
    }

    /**
     * Navigates back to the main view and closes the current view.
     */
    public void back() {
        atmMapPresenter.disposeView();
        loggedInController.launch();
    }
}
