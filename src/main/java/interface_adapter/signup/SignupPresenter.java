package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final WelcomeViewModel welcomeViewModel;
    // private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           WelcomeViewModel welcomeViewModel,
                           SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the logged in view.

        /**
         final LoggedInState loggedInState = loggedInViewModel.getState();
         loggedInState.setUserId(response.getUserId());
         this.loggedInViewModel.setState(loggedInState);
         this.loggedInViewModel.firePropertyChanged();

         this.viewManagerModel.setState(loggedInViewModel.getViewName());
         this.viewManagerModel.firePropertyChanged();
         */
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
