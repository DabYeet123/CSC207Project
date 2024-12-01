package signup.use_case;

import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary signupOutputBoundary;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.signupOutputBoundary = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        final String name = signupInputData.getName();
        final String surname = signupInputData.getSurname();
        final String password = signupInputData.getPassword();

        final User user = userFactory.create(name, surname, password);
        userDataAccessObject.save(user);

        final SignupOutputData signupOutputData = new SignupOutputData(user);
        signupOutputBoundary.prepareSuccessView(signupOutputData);
    }

    @Override
    public void switchToWelcomeView() {
        signupOutputBoundary.switchToWelcomeView();
    }
}
