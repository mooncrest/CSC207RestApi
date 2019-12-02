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

    /**
     * Creates a json object that will read and write json database objects
     * @param type the class type used for generation by jackson
     * @param fileName the file name to be parsed
     */
    public JsonHelper(Class<T> type, String fileName) {
        this.type = type;
        this.file = new File(fileName);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * given the json database object it will write it to the file
     * @param DB a java object representation of a json database
     */
    public void writeJson(T DB) {
        try {
            mapper.writeValue(file, DB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads the file name from this class and returns the specific json data representation
     * @return a java object representing the json data
     */
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
