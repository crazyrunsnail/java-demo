package person.davino.classicio.file;

import java.io.File;

public class CreateFileDemo {

    private final static String demoRelativePath = "Documents/IdeaProjects/java-demo";

    private final static String homePath = System.getProperty("user.home");

    public static void main(String[] args) {

        // 相对路径
        File file  = new File("hello.txt");

        System.out.println(file.exists()); // true

        // 第二个参数是 childpath, 并不需要用 / 开头
        File file1 = new File(homePath, demoRelativePath);
        System.out.println(file1.exists());


    }
}
