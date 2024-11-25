package DataAccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class DataAccessController {

    private static final String BASE_PATH = "data" + FileSystems.getDefault().getSeparator();

    public <T> void saveData(String fileName, List<T> data, Class<T> classA) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new JavaTimeModule());
        try {
            File file = new File(BASE_PATH + fileName);
            file.getParentFile().mkdirs(); // Ensure the directory exists
            mapper.writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> readData(String fileName, Class<T> classA) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new JavaTimeModule());
        List<T> data = new ArrayList<>();
        try {
            File file = new File(BASE_PATH + fileName);
            if (file.exists()) {
                T[] array = mapper.readValue(file, mapper.getTypeFactory().constructArrayType(classA));
                data = new ArrayList<>(List.of(array));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
