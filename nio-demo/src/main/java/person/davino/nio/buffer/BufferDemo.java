package person.davino.nio.buffer;

import java.nio.ByteBuffer;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/01/2018
 */
public class BufferDemo {

    public static void main(String[] args) {
        //bufferPutGet();
        create();
        arrayOffSet();

    }

    private static void bufferPutGet() {
        // put() 和 get() 一样会改变position
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for ( int i = 0 ; i < 10 ; i++ ) {
            byteBuffer.put((byte)(Math.random() * 256));
        }
        printBuffer(byteBuffer);

        for ( int i = 0 ;i < byteBuffer.position();i++) {
            System.out.println(byteBuffer.get(i));
        }
    }

    private static void getIndex() {
        // position = offset
        // limit = offset + length
        // capacity = array.lenght
        byte[] bytes1 = {12,23,23,42};
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(bytes1, 1, 2);
        printBuffer(byteBuffer2);
        int i =  byteBuffer2.position();
        //get(index) 方法不会移动position
        for ( ; i< byteBuffer2.limit(); i++) {
            System.out.println(byteBuffer2.get(i));
        }
    }

    public static  void arrayOffSet() {
        byte[] bytes = {12,23,24};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes,1,2);
        byteBuffer.get(1);
        System.out.println(byteBuffer.hasArray());
        System.out.println(byteBuffer.arrayOffset());
    }


    public static  void create () {
        //create
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        printBuffer(byteBuffer);


        byte[] bytes = {12, 23, 34};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(bytes);

        printBuffer(byteBuffer1);

        // position = offset
        // limit = offset + length
        // capacity = array.lenght
        byte[] bytes1 = {12,23,23,42};
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(bytes1, 1, 2);
        printBuffer(byteBuffer2);

        ByteBuffer byteBuffer3 = byteBuffer.duplicate();
        byteBuffer3.get();
        printBuffer(byteBuffer3);
    }


    public static void printBuffer(ByteBuffer byteBuffer) {
        System.out.println("======");
        System.out.println("buffer capacity:" + byteBuffer.capacity());
        System.out.println("buffer limit:" + byteBuffer.limit());
        System.out.println("buffer remaining" + byteBuffer.remaining());
        System.out.println("buffer position:" + byteBuffer.position());
        System.out.println(byteBuffer);
        if (byteBuffer.hasArray()) {
            System.out.println("buffer array: "  + byteBuffer.array());
            System.out.println("buffer array: " + byteBuffer.arrayOffset());
        }
        System.out.println("====");
    }
}


