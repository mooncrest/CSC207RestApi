package CSC207.CSC207RestApi.dao;

import CSC207.CSC207RestApi.model.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    public static void writeJson(String filename, String type, Object jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            if (type.equals("user")) {
                mapper.writeValue(new File(filename), ((UserDataBase)jsonObject));
            } else if (type.equals("leader")) {
                mapper.writeValue(new File(filename), ((LeaderBoardDataBase)jsonObject));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object ReadJson(String filename, String type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (type.equals("user")) {
                return mapper.readValue(new File(filename), UserDataBase.class);
            } else if (type.equals("leader")) {
                return mapper.readValue(new File(filename), LeaderBoardDataBase.class);
            }
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
