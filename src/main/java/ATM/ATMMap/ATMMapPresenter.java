package ATM.ATMMap;


public class ATMMapPresenter {

    private final ATMMapView atmMapView;

    public ATMMapPresenter(ATMMapController controller) throws Exception {
        this.atmMapView = new ATMMapView(controller);
    }

    public void showView(){
        atmMapView.setVisible(true);
    }

    public void disposeView(){
        atmMapView.setVisible(false);
        atmMapView.dispose();
    }
}
