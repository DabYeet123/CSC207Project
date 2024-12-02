package atm_map.use_case;

/**
 * The output boundary for the Atm Generation.
 */
public interface AtmGenerationOutputBoundary {

    /**
     * Updates the current view with the new information.
     * @param atmGenerationOutput the input data
     */
    void updateView(AtmGenerationOutput atmGenerationOutput);

}
