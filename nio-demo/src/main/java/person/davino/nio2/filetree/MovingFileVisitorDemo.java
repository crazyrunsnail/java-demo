package person.davino.nio2.filetree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class MovingFileVisitorDemo {

    public static void main(String[] args) throws IOException {
        Path src = Paths.get("x", "y1");
        Path dst = Paths.get("x", "y2");
        Files.walkFileTree(src, new MovingFileVisitor(src, dst));
    }


    public static class MovingFileVisitor extends SimpleFileVisitor<Path> {

        private Path src, dst;

        public MovingFileVisitor(Path src, Path dst) {
            this.src = src;
            this.dst = dst;
            init();
        }

        public void init() {
            if (Files.notExists(this.src)) {
                System.err.printf("Source Path %1$s is not exits!", src.getFileName());
                System.exit(-1);
            }
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("Moving File [" + dir + "]");
            Path targetPath = dst.resolve(src.relativize(dir));
            Files.copy(dir, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            return super.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Path targetPath = dst.resolve(src.relativize(file));
            System.out.printf("Moving file [%s] from [%s] to [%s]%n", file, src, targetPath);
            Files.move(file, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return super.visitFileFailed(file, exc);
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            if (exc == null)
                Files.delete(dir);
            else
                throw exc;
            return FileVisitResult.CONTINUE;
        }
    }
}
