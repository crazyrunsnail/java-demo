package person.davino.demo.json.jackson.steam;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import person.davino.demo.json.jackson.ObjectMapperFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class StreamApiClient {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("x/student.json");
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(Files.newOutputStream(path));

        // begin
        generator.writeStartObject();

        generator.writeStringField("hello", "json");

        generator.writeFieldName("marks");
        generator.writeStartArray();
        generator.writeNumber(10);
        generator.writeNumber(11);
        generator.writeNumber(12);
        generator.writeEndArray();

        generator.writeEndObject();

        generator.close();

        ObjectMapper objectMapper = ObjectMapperFactory.getInstance();
        Map map = objectMapper.readValue(path.toFile(), Map.class);
        System.out.println(map.get("hello"));
        System.out.println(map.get("marks"));

        JsonParser parser = factory.createParser(path.toFile());
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = parser.getCurrentName();
            if ("name".equals(currentName)) {
                //move to next token
                parser.nextToken();
                System.out.println(parser.getText());
            }
            if("age".equals(currentName)){
                //move to next token
                parser.nextToken();
                System.out.println(parser.getNumberValue());
            }
            if("verified".equals(currentName)){
                //move to next token
                parser.nextToken();
                System.out.println(parser.getBooleanValue());
            }
            if("marks".equals(currentName)){
                //move to [
                parser.nextToken();
                // loop till token equal to "]"
                while (parser.nextToken() != JsonToken.END_ARRAY) {
                    System.out.println(parser.getNumberValue());
                }
            }
        }
    }
}
