package person.davino.demo.json.jackson.tester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import person.davino.demo.json.jackson.ObjectMapperFactory;
import person.davino.demo.json.jackson.bean.Student;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JsonTesterClient {
    public static void main(String[] args) throws IOException, ParseException {
        ObjectMapper objectMapper = ObjectMapperFactory.getInstance();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy(){
            @Override
            public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
                if (field.getName().equals("name")){
                    return  "NAME";
                }
                return super.nameForField(config, field, defaultName);
            }

            @Override
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                if (method.getAnnotated().getDeclaringClass().equals(Student.class) && defaultName.equals("name"))
                    return "Name";
                return super.nameForGetterMethod(config, method, defaultName);
            }
        });
        Map<String, Object> studentDataMap = new HashMap<>();
        int[] marks = {1,2,3};
        Student student = new Student();
        student.setName("Jin");
        student.setAge(10);


        studentDataMap.put("student", student);
        studentDataMap.put("name", "feng");
        studentDataMap.put("verified", Boolean.TRUE);
        studentDataMap.put("marks", marks);
        studentDataMap.put("current", new Date());

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentDataMap);
        System.out.println(json);

        Map jsonMap = objectMapper.readValue(json, Map.class);

        System.out.println(jsonMap.get("student"));
        System.out.println(jsonMap.get("marks"));
        System.out.println(jsonMap.get("verified"));
        System.out.println(jsonMap.get("hello"));

    }
}
