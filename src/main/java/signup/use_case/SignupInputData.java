package signup.use_case;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {
    private final String name;
    private final String surname;
    private final String password;

    public SignupInputData(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }
}