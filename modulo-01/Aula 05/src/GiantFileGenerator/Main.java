package GiantFileGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    static void main(String[] args) {
        String fileName = "benchmark.txt";
        String baseLine = "This is a example line for a file reader benchmark";
        Long size = 200L * 1024 * 1024;
        try {
            Long actualSize = 0L;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            while (actualSize < size) {
                bufferedWriter.write(baseLine);
                actualSize += baseLine.getBytes().length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
