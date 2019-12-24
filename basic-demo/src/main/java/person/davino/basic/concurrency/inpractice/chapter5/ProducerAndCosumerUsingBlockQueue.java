package person.davino.basic.concurrency.inpractice.chapter5;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerAndCosumerUsingBlockQueue {

    public static class FileCrawler implements Runnable{
        private final BlockingQueue<File> fileBlockingQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileBlockingQueue, FileFilter fileFilter, File root) {
            this.fileBlockingQueue = fileBlockingQueue;
            this.fileFilter = fileFilter;
            this.root = root;
        }


        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        crawl(entry);
                    }else {
                        fileBlockingQueue.put(entry);
                    }
                }
            }
        }
    }

    public static final class Indexer implements Runnable {

        private final BlockingQueue<File> queue;

        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
        while (true) {
            try {
                // take and doing someting
                queue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();;
            }
        }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>();
        File root = Paths.get("./").toFile();
        new Thread(new FileCrawler(queue, null, root)).start();
        new Thread(new Indexer(queue)).start();
    }
}
