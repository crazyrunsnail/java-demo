package person.davino.nio2.filetree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DoNothingFileVisitorDemo {

    public static void main(String[] args) throws IOException {
        // NoSuchFileException
        Files.walkFileTree(Paths.get("hello.t"), new DoNotingFileVisitor());

        // 只运行visitFile
        Files.walkFileTree(Paths.get("hello.txt"), new DoNotingFileVisitor());
    }

    /**
     * 一个简单的FileVisitor实现
     * 如果传入的Path是一个regularFile, 那么不会回调preVisitDirectory和postVisitDirectory
     * 每一步都会返回一个FileVisitResult, FileVisitResult是一个enum
     *
     */
    private static class DoNotingFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.printf("preVisitDirectory: %s%n", dir);
            System.out.printf("   lastModifiedTime: %s%n",
                    attrs.lastModifiedTime());
            System.out.printf("   size: %d%n%n", attrs.size());
            return super.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.printf("visitFile: %s%n%n", file);
            System.out.printf("   lastModifiedTime: %s%n",
                    attrs.lastModifiedTime());
            System.out.printf("   size: %d%n%n", attrs.size());
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.printf("visitFileFailed: %s %s%n%n", file, exc);
            return super.visitFileFailed(file, exc);
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.printf("postVisitDirectory: %s %s%n%n", dir, exc);
            return super.postVisitDirectory(dir, exc);
        }
    }
}
