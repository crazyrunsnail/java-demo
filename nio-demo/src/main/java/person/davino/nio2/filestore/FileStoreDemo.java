package person.davino.nio2.filestore;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileStoreDemo {
    public static void main(String[] args) throws IOException {
        Iterable<Path> rootDirectories = FileSystems.getDefault().getRootDirectories();
        for (Path root: rootDirectories) {
            FileStore fs = Files.getFileStore(root);
            System.out.printf("TotalSpace: %s%n", fs.getTotalSpace());
            System.out.printf("UseableSpace: %s%n", fs.getUsableSpace());
            System.out.printf("UnallocatedSpace: %s%n", fs.getUnallocatedSpace());
            System.out.printf("Read only %b%n", fs.isReadOnly());
            System.out.printf("Name: %s%n", fs.name());
            System.out.printf("Type: %s%n", fs.type());
        }
    }
}
