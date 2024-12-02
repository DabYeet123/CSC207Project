package atm_map.use_case;

/**
 * The Input Data for the Atm Generation use case.
 */
public class AtmGenerationInput {

    private final String address;

    public AtmGenerationInput(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
