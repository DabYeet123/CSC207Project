package Loans.SeeLoansHistory;

import App.PresenterInterface;

public class SeeLoansHistoryPresenter implements PresenterInterface<SeeLoansHistoryController> {
    private final SeeLoansHistoryView seeLoansHistoryView;

    public SeeLoansHistoryPresenter(SeeLoansHistoryController controller){
        this.seeLoansHistoryView = new SeeLoansHistoryView(controller);
    }

    @Override
    public void showView(){
        seeLoansHistoryView.setVisible(true);
    }

    @Override
    public void disposeView(){
        seeLoansHistoryView.setVisible(false);
        seeLoansHistoryView.dispose();
    }
}
