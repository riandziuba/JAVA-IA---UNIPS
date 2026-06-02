package benchmarkIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    static void main(String[] args) throws Exception{
        Path path = Paths.get("benchmark.txt");

        // Java IO - classic
        long start, end;
        start = System.currentTimeMillis();
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
        while (reader.readLine() != null);
        reader.close();
        end = System.currentTimeMillis();
        System.out.println("Java IO - Duration: " + (end-start) + "ms");

        // Java NIO - File Channel
        start = System.currentTimeMillis();
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            buffer.clear();
        }
        channel.close();
        end = System.currentTimeMillis();
        System.out.println("Java NIO Channel Duration: " + (end - start) + "ms");

        // JAVA NIO2 - ReadAllLines
        start = System.currentTimeMillis();
        List<String> lines = Files.readAllLines(path);
        end = System.currentTimeMillis();

        System.out.println("Java NIO2 ReadAllLines Duration: " + (end - start) + "ms");
    }
}
