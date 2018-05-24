package person.davino.nio2.filetree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DeletingFileVisitorDemo {

    public static void main(String[] args) {
        DeletingFileVisitorDemo demo = new DeletingFileVisitorDemo();
        Path delete = Paths.get("x", "y2");
        DeletingFileVisitorDemo.DeletingFileVisitor visitor = demo.new DeletingFileVisitor(delete);
        try {
            Files.walkFileTree(delete, visitor);
        } catch (IOException e) {
            System.err.printf("Walking file tree error! [%s]", e.getMessage());
        }
    }

    public class DeletingFileVisitor extends SimpleFileVisitor<Path> {

        private Path src;

        public DeletingFileVisitor(Path src) {
            this.src = src;
            init();
        }

        public void init() {
            if (Files.notExists(this.src)) {
                System.err.printf("The path want to delete [%s] is not exists!", src);
                System.exit(-1);
            }
        }


        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.printf("Predeleting Directory [%s] ....%n", dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.printf("deleting file: %s%n", file);
            Files.delete(file);
            System.out.printf("deleted file %s successfully!%n", file);
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.err.printf("Deleting file [%s] fail! continue....%n", file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            if (exc == null) {
                System.out.printf("Deleting directory [%s] ....%n", dir);
                Files.delete(dir);
                System.out.printf("Deleted directory [%s] successfully!", dir);

            } else
                throw exc;

            return FileVisitResult.CONTINUE;
        }
    }
}
