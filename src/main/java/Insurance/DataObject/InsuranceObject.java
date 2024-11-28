package Insurance.DataObject;

import java.time.LocalDate;

public class InsuranceObject {
    private static final double HEALTH_INSURANCE_PRICE = 400.00;
    private static final double VEHICLE_INSURANCE_PRICE = 600.00;
    private static final double HOME_INSURANCE_PRICE = 550.00;
    private static final double TRAVEL_INSURANCE_PRICE = 300.00;
    private static final double LIFE_INSURANCE_PRICE = 800.00;
    private static final double PET_INSURANCE_PRICE = 200.00;
    private static final double BUSINESS_INSURANCE_PRICE = 1000.00;
    private static final double DENTAL_INSURANCE_PRICE = 250.00;

    int userID;
    String type;
    double premium;
    int term;
    LocalDate startDate;
    LocalDate endDate;
    boolean autoRenew;
    int insuranceID;
    String policyDetails;
    String insuranceName;

    public InsuranceObject(int userID, String type, double premium, int term, boolean autoRenew) {
        this.userID = userID;
        this.type = type;
        switch (type) {
            case "Health":
                this.premium = HEALTH_INSURANCE_PRICE;
                break;
            case "Vehicle":
                this.premium = VEHICLE_INSURANCE_PRICE;
                break;
            case "Home":
                this.premium = HOME_INSURANCE_PRICE;
                break;
            case "Travel":
                this.premium = TRAVEL_INSURANCE_PRICE;
                break;
            case "Life":
                this.premium = LIFE_INSURANCE_PRICE;
                break;
            case "Pet":
                this.premium = PET_INSURANCE_PRICE;
                break;
            case "Business":
                this.premium = BUSINESS_INSURANCE_PRICE;
                break;
            case "Dental":
                this.premium = DENTAL_INSURANCE_PRICE;
                break;
            default:
                this.premium = 500.00;
        }
        this.term = term;
        this.autoRenew = autoRenew;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusYears(term);
        this.insuranceID = 100000000 + (int) (Math.random() * 100000); // Generate a unique ID for each policy
        this.policyDetails = generatePolicyDetails(type);
        this.insuranceName = "Sample " + type + " Insurance";
    }

    private String generatePolicyDetails(String type) {
        switch (type) {
            case "Health":
                return "Covers hospitalization, surgery, and prescription costs.";
            case "Vehicle":
                return "Covers damage, theft, and liability for accidents.";
            case "Home":
                return "Covers fire, theft, and natural disasters.";
            case "Travel":
                return "Covers trip cancellation, medical emergencies, and lost luggage.";
            case "Life":
                return "Covers life insurance providing death benefits to beneficiaries.";
            case "Pet":
                return "Covers veterinary care, surgeries, and medications for pets.";
            case "Business":
                return "Covers liability, property damage, and business interruption.";
            case "Dental":
                return "Covers routine dental checkups, fillings, and dental surgeries.";
            default:
                return "Standard policy details.";
        }
    }

    public int getUserID() {
        return userID;
    }
    public String getType() {
        return type;
    }
    public double getPremium() {
        return premium;
    }
    public int getTerm() {
        return term;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public boolean isAutoRenew() {
        return autoRenew;
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