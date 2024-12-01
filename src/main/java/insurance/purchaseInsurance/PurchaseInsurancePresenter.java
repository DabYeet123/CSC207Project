package insurance.purchaseInsurance;

import App.PresenterInterface;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class PurchaseInsurancePresenter implements PresenterInterface<PurchaseInsuranceController> {
    private final PurchaseInsuranceView purchaseInsuranceView;

    /**
     * Constructs a new PurchaseInsurancePresenter with the specified controller.
     *
     * @param controller The controller for PurchaseInsurance.
     */
    public PurchaseInsurancePresenter(PurchaseInsuranceController controller) {
        this.purchaseInsuranceView = new PurchaseInsuranceView(controller);
    }

    /**
     * Displays the PurchaseInsurance view.
     */
    @Override
    public void showView() {
        purchaseInsuranceView.setVisible(true);
    }

    /**
     * Disposes of the PurchaseInsurance view.
     */
    @Override
    public void disposeView() {
        purchaseInsuranceView.setVisible(false);
        purchaseInsuranceView.dispose();
    }
}
