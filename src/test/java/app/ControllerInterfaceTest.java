package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the `ControllerInterface`.
 */
public class ControllerInterfaceTest {

    /**
     * A mock implementation of the `ControllerInterface` for testing.
     */
    static class MockController implements ControllerInterface {
        private boolean isLaunched = false;

        @Override
        public void launch() {
            isLaunched = true;
        }

        public boolean isLaunched() {
            return isLaunched;
        }
    }

    @Test
    public void launchTest() {
        // Arrange
        MockController mockController = new MockController();

        // Act
        mockController.launch();

        // Assert
        assertTrue(mockController.isLaunched(), "The controller should be launched after calling launch().");
    }
}
