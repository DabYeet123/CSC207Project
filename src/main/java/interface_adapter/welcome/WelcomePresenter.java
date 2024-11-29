package interface_adapter.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.welcome.WelcomeOutputBoundary;

/**
 * The Presenter for the Welcome Use Case.
 */
public class WelcomePresenter implements WelcomeOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    // private final SignUpViewModel signUpViewModel;

    public WelcomePresenter(ViewManagerModel viewManagerModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        // this.signupViewModel = signupViewModel;
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignupView() {
        // viewManagerModel.setState(loginViewModel.getViewName());
        // viewManagerModel.firePropertyChanged();
    }
}
