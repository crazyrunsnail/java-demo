package person.davino.basic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortStreamTest {

    public static void main(String[] args) {
//        List<Date> collect = Stream.of(new Date(), new Date(120, 01, 23), new Date(120, 12, 12))
//                .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
//        for (Date date : collect) {
//            System.out.println(date.getTime());
//        }
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Integer> collect = list.stream().filter(x -> x == 2).collect(Collectors.toList());
        List<Integer> collect1 = list.stream().filter(x -> x == 3).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect1);
        Order order = new Order();
        System.out.println(Objects.equals(1, order.getStatus()));

    }

    public static class Order {
        private Integer status;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }
}
