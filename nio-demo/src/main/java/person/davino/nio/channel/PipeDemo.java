package person.davino.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 28/02/2018
 */
public class PipeDemo {

    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();

        Runnable r = () -> {
            WritableByteChannel src = pipe.sink();
            ByteBuffer buffer = ByteBuffer.allocateDirect(10);
            for (int i = 0 ; i < 3 ; i++) {
                buffer.clear();

                System.out.println("buffer.capacity: " + buffer.capacity());

                for (int j = 0 ; j < buffer.capacity(); j++)
                    buffer.put((byte) (Math.random() * 256));

                buffer.flip();

                try {
                    while (src.write(buffer) > 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                src.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        };

        Runnable rr = () -> {
            ReadableByteChannel dst = pipe.source();

            ByteBuffer buffer = ByteBuffer.allocateDirect(10);

            try {
                while (dst.read(buffer) >= 0 ){
                    buffer.flip();
                    while (buffer.hasRemaining())
                        System.out.println(buffer.get() & 255);
                    buffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread sender = new Thread(r);

        Thread receiver = new Thread(rr);

        sender.start();

        receiver.start();

    }



}
