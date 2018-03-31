package person.davino.nio2.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NormalizePathDemo {
    public static void main(String[] args) {
        Path path = Paths.get("report", ".", "2015", "jan");
        System.out.println(path);
        System.out.println(path.normalize()); // 普通化去掉 . 或者是 ..

        path = Paths.get("report", "2015", "..", "jan"); // report/jan
        System.out.println(path.normalize());

        path = Paths.get("report", "2015", "jan");
        System.out.println(path.relativize(Paths.get("report", "2016", "mar"))); // ../../2016/mar 相对路径

        path = Paths.get("report", "2015");
        System.out.println(path.resolve("jan")); // report/2015/jan 相当于拼接字符串
        System.out.println(path.resolve(Paths.get("report", "jan")));

        System.out.println(path.resolveSibling("mar")); // report/mar 这里看到于 report/2015/mar的差别

    }
}
