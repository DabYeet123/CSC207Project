package dataaccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class DataAccessControllerTest {

    private DataAccessController controller;

    @TempDir
    Path tempDir; // JUnit creates a temporary folder

    @BeforeEach
    public void setUp() {
        controller = new DataAccessController() {
            protected String getBasePath() {
                return tempDir.toString() + File.separator; // Override base path to use temp folder
            }
        };
    }

    @Test
    public void testSaveData() throws IOException {
        // Given
        List<String> data = Arrays.asList("item1", "item2", "item3");
        String fileName = "testData.json";

        // When
        controller.saveData(fileName, data, String.class);

        // Then
        File file = new File(tempDir.toString(), fileName);
        assertTrue(file.exists(), "File should be created");

        // Additional check for debugging
        System.out.println("File exists: " + file.exists());

        // Verify that the file contains the expected data
        ObjectMapper mapper = new ObjectMapper();
        List<String> savedData = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, String.class));
        assertEquals(data, savedData, "Saved data should match the original data");
    }


    @Test
    public void testReadData_existingFile() throws IOException {
        // Given
        List<String> data = Arrays.asList("item1", "item2", "item3");
        String fileName = "testData.json";
        controller.saveData(fileName, data, String.class);

        // When
        List<String> readData = controller.readData(fileName, String.class);

        // Then
        assertNotNull(readData, "Data should not be null");
        assertEquals(data.size(), readData.size(), "Data sizes should match");
        assertEquals(data, readData, "Saved data should match the read data");
    }

    @Test
    public void testReadData_nonExistingFile() {
        // Given
        String fileName = "nonExistingFile.json";

        // When
        List<String> readData = controller.readData(fileName, String.class);

        // Then
        assertTrue(readData.isEmpty(), "Data should be empty for a non-existing file");
    }

    @Test
    public void testSaveData_emptyList() throws IOException {
        // Given
        List<String> data = Arrays.asList(); // Empty list
        String fileName = "emptyData.json";

        // When
        controller.saveData(fileName, data, String.class);

        // Then
        File file = new File(tempDir.toString(), fileName);
        assertTrue(file.exists(), "File should be created even for empty data");

        // Verify that the file is empty or contains an empty array
        ObjectMapper mapper = new ObjectMapper();
        List<String> savedData = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, String.class));
        assertTrue(savedData.isEmpty(), "Saved data should be empty");
    }

    @Test
    public void testSaveData_nullData() {
        // Given
        List<String> data = null;
        String fileName = "nullData.json";

        // When
        // Expecting the method to handle null data gracefully without exceptions
        controller.saveData(fileName, data, String.class);

        // Then
        File file = new File(tempDir.toString(), fileName);
        assertFalse(file.exists(), "File should not be created if data is null");
    }

    @Test
    public void testSaveData_invalidData() {
        // Given
        String invalidData = "Invalid data";  // A single string instead of a list
        String fileName = "invalidData.json";

        // When
        // Expecting the method to handle invalid data type gracefully
        try {
            controller.saveData(fileName, Arrays.asList(invalidData), String.class);
            File file = new File(tempDir.toString(), fileName);
            assertTrue(file.exists(), "File should be created for valid data types");
        } catch (Exception e) {
            fail("Exception should not occur for valid data types");
        }
    }
}
