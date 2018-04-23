package person.davino.classicio.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * File.list 列出文件名字串
 * 配合 FilenameFilter, 可以过滤出一些信息.
 */
public class DirDemo {
    public static void main(String[] args) {
        File file = new File(".");
        // 使用FileNameFilter
        String[] list = file.list((f, name) -> {
            return name.matches("[a-zA-Z0-9-_]*demo$");
        });
        Arrays.stream(list).forEach(System.out::println);
    }
}
