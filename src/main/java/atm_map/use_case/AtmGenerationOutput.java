package atm_map.use_case;

import javax.swing.JPanel;

/**
 * The Output Data for the Atm Generation use case.
 */
public class AtmGenerationOutput {

    private final JPanel mapPanel;

    public AtmGenerationOutput(JPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public JPanel getMapPanel() {
        return mapPanel;
    }
}
