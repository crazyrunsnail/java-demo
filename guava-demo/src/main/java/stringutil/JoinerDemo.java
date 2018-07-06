package stringutil;

import com.google.common.base.Joiner;

public class JoinerDemo {

    public static void main(String[] args) {
        String join = Joiner.on("; ").skipNulls().join("harry", "", null, "Ron");
        // userForNull 不能和 skipNulls 同时共用
        String join2 = Joiner.on("; ").useForNull("null").join("harry", "", null, "Ron");
        System.out.println(join);
        System.out.println(join2);
    }
}
