package person.davino.classicio.stream.serialization;

import java.io.Serializable;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 07/01/2018
 */
public class Employee implements Serializable {

    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
