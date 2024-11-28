package ATMMap.UseCase;

import javax.swing.*;

public class ATMGenerationOutput {

    private final JPanel mapPanel;

    public ATMGenerationOutput(JPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public JPanel getMapPanel() {
        return mapPanel;
    }
}