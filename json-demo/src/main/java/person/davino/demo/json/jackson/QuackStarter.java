package person.davino.demo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import person.davino.demo.json.jackson.bean.Student;

import java.io.IOException;

public class QuackStarter {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
        try {
            // 反序列化 deserialize
            Student student = objectMapper.readValue(jsonString, Student.class);
            System.out.println(student);


            // 序列化 serialize
            jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(student);
            System.out.println(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
