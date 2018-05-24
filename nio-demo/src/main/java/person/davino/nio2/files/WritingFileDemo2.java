package person.davino.nio2.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WritingFileDemo2 {

    private final static Path path = Paths.get("file_write_demo2.txt");

    public static void main(String[] args) throws IOException {
        URL url = null;
        try {
            url = new URL("https://one-piece.cn/anime/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStreamReader reader = new InputStreamReader(url.openStream());

        BufferedReader br = new BufferedReader(reader);
        BufferedWriter bw = Files.newBufferedWriter(path);

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine(); // 手动分行
        }
        bw.flush();
        bw.close(); // 需要关闭
    }
}
