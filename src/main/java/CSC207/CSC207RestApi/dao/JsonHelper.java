package CSC207.CSC207RestApi.dao;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class JsonHelper <T>{
    private File file;
    private Class<T> type;
    private ObjectMapper mapper = new ObjectMapper();

    public JsonHelper(Class<T> type, String fileName) {
        this.type = type;
        this.file = new File(fileName);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void writeJson(T DB) {
        try {
            mapper.writeValue(file, DB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public T ReadJson() {
        try {
            return mapper.readValue(file, type);
        }
        catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
