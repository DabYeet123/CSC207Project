package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedinState;
import interface_adapter.loggedin.LoggedinViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final LoggedinViewModel loggedinViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           WelcomeViewModel welcomeViewModel,
                           LoggedinViewModel loggedinViewModel,
                           SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        this.loggedinViewModel = loggedinViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the logged in view.

        final LoggedinState loggedInState = loggedinViewModel.getState();
        loggedInState.setUser(response.getUser());
        this.loggedinViewModel.setState(loggedInState);
        this.loggedinViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedinViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setNameError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
