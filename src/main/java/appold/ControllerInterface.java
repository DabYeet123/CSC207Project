package appold;

/**
 * Interface for controllers in the application. Any class that implements this interface
 * should provide the functionality to launch the controller, which typically involves
 * initializing and displaying the corresponding view or interface.
 */
public interface ControllerInterface {
    /**
     * Launches the controller. This method is responsible for initializing and displaying
     * the corresponding view or interface associated with this controller.
     */
    void launch();
}
