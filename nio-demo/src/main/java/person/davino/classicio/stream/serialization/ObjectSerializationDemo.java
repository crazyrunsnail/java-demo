package person.davino.classicio.stream.serialization;

import java.io.*;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 07/01/2018
 */
public class ObjectSerializationDemo {
    private static String FILENAME = "file.out";

    public static void main(String[] args) {
        ObjectOutputStream oos = null ;
        ObjectInputStream ois = null;
        try {

            FileOutputStream fos = new FileOutputStream(FILENAME);

            oos = new ObjectOutputStream(fos);

            Employee employee = new Employee("feng", 25);

            oos.writeObject(employee);

            oos.close();

            FileInputStream fis = new FileInputStream(FILENAME);

            ois = new ObjectInputStream(fis);

            Employee emp = (Employee) ois.readObject();

            System.out.println(emp);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
