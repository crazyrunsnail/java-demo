package person.davino.classicio.file;

import java.io.File;
import java.io.IOException;

public class PathInfoDemo {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("usage: java PathInfoDemo path");
            System.exit(1);
        }

        File file = new File(args[0]);
        System.out.println("Absolute Path: " + file.getAbsolutePath()); // Same as file.getPath();
        System.out.println("Canoncial Path: " + file.getCanonicalPath());
        System.out.println("Name: " + file.getName());
        System.out.println("Parent: " + file.getParent());
        System.out.println("Path: " + file.getPath());
        System.out.println("Is absolute: " + file.isAbsolute());

        /**
         *
         * -->
         java PathInfoDemo .
         Absolute Path: /Users/Rounds/Documents/IdeaProjects/java-demo/.
         Canoncial Path: /Users/Rounds/Documents/IdeaProjects/java-demo
         Name: .
         Parentnull
         Path: .
         Is absolute: false

         -->
         java PathInfoDemo /Users/Rounds/Documents/IdeaProjects/java-demo/../java-demo
         Absolute Path: /Users/Rounds/Documents/IdeaProjects/java-demo/../java-demo
         Canoncial Path: /Users/Rounds/Documents/IdeaProjects/java-demo
         Name: java-demo
         Parent: /Users/Rounds/Documents/IdeaProjects/java-demo/..
         Path: /Users/Rounds/Documents/IdeaProjects/java-demo/../java-demo
         Is absolute: true

         */
    }
}
