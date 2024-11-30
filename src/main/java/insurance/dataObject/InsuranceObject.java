package insurance.dataObject;

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

    public String getType() {
        return type;
    }

    public double getPremium() {
        return premium;
    }

    public int getInsuranceID() {
        return insuranceID;
    }

    public String getPolicyDetails() {
        return policyDetails;
    }

    public String getInsuranceName() {
        return insuranceName;
    }
}
