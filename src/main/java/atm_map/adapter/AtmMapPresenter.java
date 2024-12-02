package atm_map.adapter;

import javax.swing.JPanel;

import App.PresenterInterface;
import atm_map.use_case.AtmGenerationOutput;
import atm_map.use_case.AtmGenerationOutputBoundary;
import atm_map.view.AtmMapView;

/**
 * The Presenter for the AtmMap.
 */
public class AtmMapPresenter implements PresenterInterface<AtmMapController>, AtmGenerationOutputBoundary {

    private final AtmMapView atmMapView;

    public AtmMapPresenter(AtmMapController controller) {
        this.atmMapView = new AtmMapView(controller);
    }

    @Override
    public void showView() {
        atmMapView.setVisible(true);
    }

    @Override
    public void disposeView() {
        atmMapView.setVisible(false);
        atmMapView.dispose();
    }

    @Override
    public void updateView(AtmGenerationOutput atmGenerationOutput) {
        final JPanel mapPanel = atmGenerationOutput.getMapPanel();
        atmMapView.updateView(mapPanel);
    }
}
