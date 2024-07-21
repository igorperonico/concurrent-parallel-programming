package exercises.logfileprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class LogProcessor implements Callable<Integer> {
    private String filePath;
    private String wordToBeCount;

    public LogProcessor(String filePath, String wordToBeCount) {
        this.filePath = filePath;
        this.wordToBeCount = wordToBeCount;
    }


    @Override
    public Integer call() throws Exception {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(wordToBeCount)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
