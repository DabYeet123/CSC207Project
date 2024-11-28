package ATMMap.Adapter;

import ATMMap.UseCase.ATMGenerationUseCase;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;

import javax.swing.*;

public class ATMMapController{

    UserObject loggedInUser;
    private final ATMGenerationUseCase atmGenerationUseCase;
    private final ATMMapPresenter atmMapPresenter;
    private final LoggedInController loggedInController;

    public ATMMapController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        this.loggedInController = loggedInController;
        this.atmMapPresenter = new ATMMapPresenter(this);
        this.atmGenerationUseCase = new ATMGenerationUseCase(atmMapPresenter);
    }

    public void launch(){
        atmMapPresenter.showView();
    }

    public JPanel generatePanel(String address) {
        return atmGenerationUseCase.generateMap(address);
    }

    public void back() {
        atmMapPresenter.disposeView();
        loggedInController.launch();
    }
}
