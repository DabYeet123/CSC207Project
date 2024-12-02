package login.signup;

import app.PresenterInterface;

/**
 * Presenter class for managing the Sign-Up view.
 */
public class SignUpPresenter implements PresenterInterface<SignUpController> {
    private final SignUpView signUpView;

    public SignUpPresenter(SignUpController controller) {
        this.signUpView = new SignUpView(controller);
    }

    @Override
    public void showView() {
        signUpView.setVisible(true);
    }

    @Override
    public void disposeView() {
        signUpView.setVisible(false);
        signUpView.dispose();
    }
}
