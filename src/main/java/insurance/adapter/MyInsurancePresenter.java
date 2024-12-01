package insurance.adapter;

import App.PresenterInterface;
import insurance.view.MyInsuranceView;
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class MyInsurancePresenter implements PresenterInterface<MyInsuranceController> {
    private final MyInsuranceView myInsuranceView;

    /**
     * Constructs a new MyInsurancePresenter with the specified controller.
     *
     * @param controller The controller for MyInsurance.
     */
    public MyInsurancePresenter(MyInsuranceController controller) {
        this.myInsuranceView = new MyInsuranceView(controller);
    }

    /**
     * Displays the MyInsurance view.
     */
    @Override
    public void showView() {
        myInsuranceView.setVisible(true);
    }

    /**
     * Disposes of the MyInsurance view.
     */
    @Override
    public void disposeView() {
        myInsuranceView.setVisible(false);
        myInsuranceView.dispose();
    }

}
