package person.davino.charset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 17/03/2018
 */
public class CharsetDemo2 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String msg = "façade touché";

        String csName[] = {
                "US-ASCII",
                "ISO-8859-1",
                "UTF-8",
                "UTF-16BE",
                "UTF-16LE",
                "UTF-16"
        };

        encode(msg, Charset.defaultCharset());

        for (String cs: csName)
            encode(msg, Charset.forName(cs));


    }

    public static void encode(String msg, Charset charset) {
        System.out.println("Charset: " + charset.displayName());
        System.out.println("Message: " + msg );

        ByteBuffer byteBuffer = charset.encode(msg);
        System.out.println("Encoding: ");

        for (int i = 0; byteBuffer.hasRemaining(); i++) {
            int _byte = byteBuffer.get() & 255;
            char ch = (char) _byte;
            if (Character.isWhitespace(ch) || Character.isISOControl(ch))
                ch = '\u0000';
            System.out.printf("%2d: %02x (%c)%n", i, _byte, ch);
        }
        System.out.println();
    }
}
