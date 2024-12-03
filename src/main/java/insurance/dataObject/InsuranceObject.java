package insurance.dataObject;

import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class InsuranceObject {

    private String type;
    private double premium;
    private int insuranceID;
    private String policyDetails;
    private String insuranceName;

    /**
     * Default constructor for InsuranceObject.
     */
    public InsuranceObject() {

    }

    /**
     * Constructs an InsuranceObject with specified parameters.
     *
     * @param type          The type of insurance.
     * @param premium       The premium amount.
     * @param insuranceID   The ID of the insurance.
     * @param policyDetails The details of the policy.
     * @param insuranceName The name of the insurance.
     */
    public InsuranceObject(String type, double premium, int insuranceID, String policyDetails, String insuranceName) {
        this.type = type;
        this.premium = premium;
        this.insuranceID = insuranceID;
        this.policyDetails = policyDetails;
        this.insuranceName = insuranceName;
    }
}
