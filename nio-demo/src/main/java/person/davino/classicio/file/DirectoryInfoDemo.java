package person.davino.classicio.file;

import java.io.File;
import java.util.Date;

public class DirectoryInfoDemo {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java FileDirectoryInfoDemo pathname");
            return;
        }

        File file = new File(args[0]);
        System.out.println("About " + file + ":");
        System.out.println("File is exits: " + file.exists());
        System.out.println("Is directory: " + file.isDirectory());
        System.out.println("Is file: " + file.isFile());
        System.out.println("Is hidden: " + file.isHidden());
        System.out.println("Last modified = " + new Date(file.lastModified()));
        System.out.println("Length: " + file.length());
        /**
         -->

         About .:
         File is exits: true
         Is directory: true
         Is file: false
         Is hidden: true
         Last modified = Thu Mar 22 21:40:56 CST 2018
         Length: 480

         */

    }
}
