package person.davino.basic.jvm;

public class ValueByteTest {

    public static void main(String[] args) {
        String s = Integer.toBinaryString(Integer.MAX_VALUE & 0x00000000);
        System.out.println(s);
        System.out.println(s.length());

        Integer.toBinaryString(Integer.MAX_VALUE);
        System.out.println((byte)513);
    }
}
