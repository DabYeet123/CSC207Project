package insurance.dataObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class InsuranceMethods {
    public static final String CHOOSE_INSURANCE_TYPE = "Choose Insurance Type";
    public static final String CHOOSE_INSURANCE_NAME_ID = "Choose Insurance Name (ID)";
    public static final String END_LINE = "\n";

    public static String getInsurancePolicyDetails(String type, InsuranceObject insurance) {
        return "Insurance Type: " + type + END_LINE
                + "Insurance Name: " + insurance.getInsuranceName() + END_LINE
                + "Insurance ID: " + insurance.getInsuranceID() + END_LINE
                + "Premium per year ($): " + insurance.getPremium() + END_LINE
                + "Insurance Policy: " + insurance.getPolicyDetails();
    }
}
