package app;

import login.welcome.WelcomeController;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.runner.RunWith;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MainLauncher.class) // Prepare the MainLauncher class for mocking
public class MainLauncherTest {

    @Test
    public void testMainLauncher() throws Exception {
        // Arrange: Mock the WelcomeController
        WelcomeController mockWelcomeController = Mockito.mock(WelcomeController.class);

        // Mock the creation of WelcomeController inside MainLauncher
        PowerMockito.whenNew(WelcomeController.class).withNoArguments().thenReturn(mockWelcomeController);

        // Act: Call the MainLauncher.main method
        MainLauncher.main(null);

        // Assert: Verify the `launch` method was called on the mocked WelcomeController
        Mockito.verify(mockWelcomeController, Mockito.times(1)).launch();
    }
}
