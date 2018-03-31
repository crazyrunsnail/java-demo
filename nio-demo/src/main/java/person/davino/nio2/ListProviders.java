package person.davino.nio2;

import java.nio.file.spi.FileSystemProvider;
import java.util.List;

public class ListProviders {
    public static void main(String[] args) {

        // since 1.7
        // 提供FileSystemProvider
        List<FileSystemProvider> fileSystemProviders= FileSystemProvider.installedProviders();

        fileSystemProviders.forEach(System.out::println);

    }
}
