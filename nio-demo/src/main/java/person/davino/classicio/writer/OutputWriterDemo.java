package person.davino.classicio.writer;

import sun.tools.java.ClassPath;

import java.io.*;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 09/01/2018
 */
public class OutputWriterDemo {

    public static void main(String[] args) throws IOException {

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("hello.txt"), "UTF-8");

        char[] outs = {'\u0034', '\u0033', '\u0045'};

        osw.write(outs);

        //先要close, 再使用InputStreamReader去连接才有数据
        osw.close();


        InputStreamReader osr = new InputStreamReader(new FileInputStream("hello.txt"), "UTF-8");

        int b = 0 ;
        while ((b = osr.read()) != -1) {
            System.out.println((char)b);
        }

    }


}
