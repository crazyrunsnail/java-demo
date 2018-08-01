package person.davino.demo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {

    private static ObjectMapper instance = new ObjectMapper();

    private ObjectMapperFactory() {}

    public static ObjectMapper getInstance() {
        return instance;
    }

}
