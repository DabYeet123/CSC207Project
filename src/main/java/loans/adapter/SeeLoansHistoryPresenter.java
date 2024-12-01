package loans.adapter;

import App.PresenterInterface;
import loans.view.SeeLoansHistoryView;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class SeeLoansHistoryPresenter implements PresenterInterface<SeeLoansHistoryController> {
    private final SeeLoansHistoryView seeLoansHistoryView;

    /**
     * Constructs a new SeeLoansHistoryPresenter with the specified controller.
     *
     * @param controller The controller for SeeLoansHistory.
     */
    public SeeLoansHistoryPresenter(SeeLoansHistoryController controller) {
        this.seeLoansHistoryView = new SeeLoansHistoryView(controller);
    }

    /**
     * Displays the SeeLoansHistory view.
     */
    @Override
    public void showView() {
        seeLoansHistoryView.setVisible(true);
    }

    /**
     * Disposes of the SeeLoansHistory view.
     */
    @Override
    public void disposeView() {
        seeLoansHistoryView.setVisible(false);
        seeLoansHistoryView.dispose();
    }
}
