package person.davino.format;

public class FormatDemo {

    public static void main(String[] args) {
        System.out.println(String.format("[%10.2s]", "hello")); // [        he]
        System.out.println(String.format("[%-10.2s]", "hello")); // [he        ] flag = -说明是左对齐
        System.out.println(String.format("[%10.2s]", "helloHELLOAB")); //[he        ]
        System.out.println(String.format("[%10.12s]", "helloHELLOAB")); //[helloHELLOAB]
        System.out.println(String.format("[%-10.12s]", "helloHELLOAB")); //[helloHELLOAB]
    }
}
