package insurance.purchaseInsurance;

import App.PresenterInterface;

public class PurchaseInsurancePresenter implements PresenterInterface<PurchaseInsuranceController> {
    private final PurchaseInsuranceView purchaseInsuranceView;

    public PurchaseInsurancePresenter(PurchaseInsuranceController controller) {
        this.purchaseInsuranceView = new PurchaseInsuranceView(controller);
    }

    @Override
    public void showView() {
        purchaseInsuranceView.setVisible(true);
    }

    @Override
    public void disposeView() {
        purchaseInsuranceView.setVisible(false);
        purchaseInsuranceView.dispose();
    }
}
