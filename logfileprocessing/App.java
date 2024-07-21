package exercises.logfileprocessing;

import java.util.List;

public class App {

    public void run() throws InterruptedException {
        List<String> logPaths = List.of("src/exercises/logfileprocessing/files/log1.txt",
                "src/exercises/logfileprocessing/files/log2.txt",
                "src/exercises/logfileprocessing/files/log3.txt");
        String wordToBeCount = "ERROR";

        ParallelLogProcessor processor = new ParallelLogProcessor(4);
        int count = processor.processLogs(logPaths, wordToBeCount);

        System.out.println("Total occurrences of '" + wordToBeCount + "': " + count);

    }
}
