package aaaa.data_access;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * A controller for data access, responsible for saving and reading data
 * using JSON format. This class uses Jackson's ObjectMapper for serialization
 * and deserialization of data.
 */
public class DBDataAccessObject {
    private static final String BASE_PATH = "data" + FileSystems.getDefault().getSeparator();

    /**
     * Saves a list of data objects to a JSON file.
     *
     * @param <T> The type of the data objects being saved.
     * @param fileName The name of the file to save the data.
     * @param data The list of data objects to save.
     * @param classA The class type of the data objects, used for serialization.
     */
    public <T> void saveData(String fileName, List<T> data, Class<T> classA) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new JavaTimeModule());
        try {
            final File file = new File(BASE_PATH + fileName);
            file.getParentFile().mkdirs();
            mapper.writeValue(file, data);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reads data from a JSON file and returns it as a list of objects.
     *
     * @param <T> The type of the data objects being read.
     * @param fileName The name of the file to read the data from.
     * @param classA The class type of the data objects, used for deserialization.
     * @return A list of data objects read from the file.
     */
    public <T> List<T> readData(String fileName, Class<T> classA) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new JavaTimeModule());
        List<T> data = new ArrayList<>();
        try {
            final File file = new File(BASE_PATH + fileName);
            if (file.exists()) {
                final T[] array = mapper.readValue(file, mapper.getTypeFactory().constructArrayType(classA));
                data = new ArrayList<>(List.of(array));
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return data;
    }
}

