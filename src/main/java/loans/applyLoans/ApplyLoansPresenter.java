package loans.applyLoans;

import App.PresenterInterface;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class ApplyLoansPresenter implements PresenterInterface<ApplyLoansController> {
    private final ApplyLoansView applyLoansView;

    /**
     * Constructs a new ApplyLoansPresenter with the specified controller.
     *
     * @param controller The controller for ApplyLoans.
     */
    public ApplyLoansPresenter(ApplyLoansController controller) {
        this.applyLoansView = new ApplyLoansView(controller);
    }

    /**
     * Displays the ApplyLoans view.
     */
    @Override
    public void showView() {
        applyLoansView.setVisible(true);
    }

    /**
     * Disposes of the ApplyLoans view.
     */
    @Override
    public void disposeView() {
        applyLoansView.setVisible(false);
        applyLoansView.dispose();
    }
}
