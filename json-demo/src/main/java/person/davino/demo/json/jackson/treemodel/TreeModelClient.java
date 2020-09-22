package person.davino.demo.json.jackson.treemodel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import person.davino.demo.json.jackson.ObjectMapperFactory;

import java.io.IOException;
import java.util.Objects;

public class TreeModelClient {

    public static void main(String[] args) throws IOException {
//        ObjectMapper mapper = ObjectMapperFactory.getInstance();
//        String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
//        //create tree from JSON
//        JsonNode rootNode = mapper.readTree(jsonString);
//        System.out.println(rootNode.get("age").asInt());
//        rootNode.get("marks").elements().forEachRemaining(elem -> {
//            System.out.println(elem);
//        });
        System.out.println(Objects.equals(150L, 150L));
    }
}
