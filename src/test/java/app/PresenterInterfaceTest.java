package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class PresenterInterfaceTest {

    // Concrete implementation of Presenter for testing purposes
    public static class Presenter<T> implements PresenterInterface<T> {
        @Override
        public void showView() {
            System.out.println("View shown");
        }

        @Override
        public void disposeView() {
            System.out.println("View disposed");
        }
    }

    // Mock the PresenterInterface
    @Mock
    private PresenterInterface<Object> presenterMock;

    // Setup method to initialize mocks before each test
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mock objects
    }

    // Test case to verify the showView method on the mock
    @Test
    public void testShowView() {
        // Call the method on the mock
        presenterMock.showView();

        // Verify that the showView method was called exactly once
        verify(presenterMock, times(1)).showView();
    }

    // Test case to verify the disposeView method on the mock
    @Test
    public void testDisposeView() {
        // Call the method on the mock
        presenterMock.disposeView();

        // Verify that the disposeView method was called exactly once
        verify(presenterMock, times(1)).disposeView();
    }

    // Test case to verify the behavior of the concrete Presenter class
    @Test
    public void testPresenterImplementation() {
        // Create an actual instance of the Presenter (concrete implementation of PresenterInterface)
        Presenter<Object> presenter = new Presenter<>();

        // Call the methods on the concrete presenter and ensure they run correctly
        presenter.showView();  // Should print "View shown"
        presenter.disposeView();  // Should print "View disposed"
    }
}
