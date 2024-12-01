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

    public InsuranceObject() {

    }

    public InsuranceObject(String type, double premium, int insuranceID, String policyDetails, String insuranceName) {
        this.type = type;
        this.premium = premium;
        this.insuranceID = insuranceID;
        this.policyDetails = policyDetails;
        this.insuranceName = insuranceName;
    }
}
