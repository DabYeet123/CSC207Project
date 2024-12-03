package oldstuff.Views.Loans.ApplyLoans;

import oldstuff.Views.appold.PresenterInterface;

public class ApplyLoansPresenter implements PresenterInterface<ApplyLoansController> {
    private final ApplyLoansView applyLoansView;

    public ApplyLoansPresenter(ApplyLoansController controller){
        this.applyLoansView = new ApplyLoansView(controller);
    }

    @Override
    public void showView(){
        applyLoansView.setVisible(true);
    }

    @Override
    public void disposeView(){
        applyLoansView.setVisible(false);
        applyLoansView.dispose();
    }
}
