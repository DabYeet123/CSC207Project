package atm_map.use_case;

import javax.swing.JPanel;

/**
 * Input Boundary for actions which are related to Atm Generation.
 */
public interface AtmGenerationInputBoundary {

    /**
     * Generates the atm map.
     * @param atmGenerationInput the input data
     */
    void generateAtmMap(AtmGenerationInput atmGenerationInput);

    /**
     * Generates the map for the given address.
     * @param address the address to generate the map around
     */
    JPanel generateMap(String address);


    /**
     * Generates the atm map.
     * @param address the address where the atm needs to be generated around
     * @param panel the JPanel where the atm will be displayed on
     */
    void generateAtm(String address, JPanel panel);
}
