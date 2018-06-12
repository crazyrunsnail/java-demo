package person.davino.dp.iterator.v2;

public class Client {
    public static void main(String[] args) {

        Iterator iterator = new DinerMenu().createIterator();

        if (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
