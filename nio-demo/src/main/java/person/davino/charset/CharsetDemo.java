package person.davino.charset;

import java.io.UnsupportedEncodingException;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 17/03/2018
 */
public class CharsetDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] encodeMsg = {
                0x66, 0x61, (byte) 0xc3, (byte) 0xa7, 0x61, 0x64, 0x65, 0x20, 0x74,
                0x6f, 0x75, 0x63, 0x68, (byte) 0xc3, (byte) 0xa9
        };

        String s = new String(encodeMsg, "UTF-8");
        System.out.println(s);
        System.out.println();

        byte[] bytes = s.getBytes();

        for (byte _byte: bytes) {
            // 如果不&255, 会打印 大于一个字节(255)前面会自动补"ffffff", 如ffffffc3,
            // 因为byte会自动转型为int, 前面补1, (补码表示法)
            System.out.println(Integer.toHexString(_byte & 255) + " ");
        }
        System.out.println();
    }
}
