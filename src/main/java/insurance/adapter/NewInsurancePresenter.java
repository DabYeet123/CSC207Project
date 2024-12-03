package insurance.adapter;

import app.PresenterInterface;
import insurance.view.NewInsuranceView;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class NewInsurancePresenter implements PresenterInterface<NewInsuranceController> {
    private final NewInsuranceView newInsuranceView;

    /**
     * Constructs a new NewInsurancePresenter with the specified controller.
     *
     * @param controller The controller for NewInsurance.
     */
    public NewInsurancePresenter(NewInsuranceController controller) {
        this.newInsuranceView = new NewInsuranceView(controller);
    }

    /**
     * Displays the NewInsurance view.
     */
    @Override
    public void showView() {
        newInsuranceView.setVisible(true);
    }

    /**
     * Disposes of the NewInsurance view.
     */
    @Override
    public void disposeView() {
        newInsuranceView.setVisible(false);
        newInsuranceView.dispose();
    }
}
