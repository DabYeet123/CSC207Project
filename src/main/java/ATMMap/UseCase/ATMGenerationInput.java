package ATMMap.UseCase;

public class ATMGenerationInput {

    private final String address;

    public ATMGenerationInput(String address) {
        this.address = address;
    }


    public String getAddress() {
        return address;
    }
}
