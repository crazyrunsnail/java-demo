package person.davino.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPeek {

    private static class I {
        private List<A> list;

        public List<A> getList() {
            return list;
        }

        public void setList(List<A> list) {
            this.list = list;
        }
    }

    private static class A {
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static void main(String[] args) {
        A a = new A();
        a.setCode("1");
        List<A> list = new ArrayList<>();
        list.add(a);
        I i = new I();
        i.setList(list);
        i.setList(i.getList().stream().peek(aa -> {
            aa.setCode("2");
        }).collect(Collectors.toList()));
        System.out.println(i.getList().get(0).getCode());

    }

}
