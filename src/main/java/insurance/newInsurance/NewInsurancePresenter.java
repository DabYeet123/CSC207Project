package insurance.newInsurance;

import App.PresenterInterface;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class NewInsurancePresenter implements PresenterInterface<NewInsuranceController> {
    private final NewInsuranceView newInsuranceView;

    public NewInsurancePresenter(NewInsuranceController controller) {
        this.newInsuranceView = new NewInsuranceView(controller);
    }

    @Override
    public void showView() {
        newInsuranceView.setVisible(true);
    }

    @Override
    public void disposeView() {
        newInsuranceView.setVisible(false);
        newInsuranceView.dispose();
    }
}
