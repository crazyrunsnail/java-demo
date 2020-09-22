package person.davino.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {
        /*Pattern compile = Pattern.compile(".+?/(\\w+)(?<ffdf>\\.pdf|\\.txt)$");
        Matcher matcher = compile.matcher("https://hsprescription.oss-cn-hangzhou.aliyuncs.com/51d27447986645b98915a26baf615308.txthttps://hsprescription.oss-cn-hangzhou.aliyuncs.com/51d27447986645b98915a26baf615308.txt");
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group("ffdf"));
        }*/

//        System.out.println("fdf;;fdafds;;;fdafdsf;".replaceAll(";{2,}", ";")
//                .replaceFirst(";$", "").replaceAll(";", "、"));
//        StringBuilder builder = new StringBuilder();
//        builder.toString().replaceFirst(";$", "");
        System.out.println("fdfd".replaceFirst("[,，]$", ""));
        System.out.println("O123213".replaceFirst("^[OI]", ""));
    }
}
