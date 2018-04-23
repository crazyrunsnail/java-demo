package person.davino.classicio.file;

import java.io.File;

public class PartitionSpaceDemo {
    public static void main(String[] args) {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("Partition: " + root);
            System.out.println("Free space on this partition = " +
                    root.getFreeSpace());
            System.out.println("Usable space on this partition = " +
                    root.getUsableSpace());
            System.out.println("Total space on this partition = " +
                    root.getTotalSpace());
            System.out.println("***");
        }
    }
}
