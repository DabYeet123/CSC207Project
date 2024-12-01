package insurance.myInsurance;

import App.PresenterInterface;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class MyInsurancePresenter implements PresenterInterface<MyInsuranceController> {
    private final MyInsuranceView myInsuranceView;

    public MyInsurancePresenter(MyInsuranceController controller) {
        this.myInsuranceView = new MyInsuranceView(controller);
    }

    @Override
    public void showView() {
        myInsuranceView.setVisible(true);
    }

    @Override
    public void disposeView() {
        myInsuranceView.setVisible(false);
        myInsuranceView.dispose();
    }
}
