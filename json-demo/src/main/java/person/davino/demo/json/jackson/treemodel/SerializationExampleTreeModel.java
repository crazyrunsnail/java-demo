package person.davino.demo.json.jackson.treemodel;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.io.IOException;

public class SerializationExampleTreeModel {


    public static void main(String[] args) throws IOException {
        // Create the node factory that gives us nodes.
        JsonNodeFactory factory = new JsonNodeFactory(false);

        // create a json factory to write the treenode as json. for the example
        // we just write to console
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator generator = jsonFactory.createGenerator(System.out);
        ObjectMapper mapper = new ObjectMapper();

        // the root node - album
        JsonNode album = factory.objectNode();
        mapper.writeTree(generator, album);

    }


}
