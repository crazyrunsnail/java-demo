package stringutil;

import com.google.common.base.CaseFormat;

public class CaseFormDemo {
    public static void main(String[] args) {
        String lowerCamel = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "lowerCamel");
        System.out.println(lowerCamel);
    }
}
