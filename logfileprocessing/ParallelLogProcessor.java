package exercises.logfileprocessing;


import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ParallelLogProcessor {
    private ExecutorService executorService;

    public ParallelLogProcessor(int numberOfThreads) {
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    public int processLogs(List<String> logs, String wordToBeCount) throws InterruptedException {
        int count = 0;

        List<Future<Integer>> futures = executorService.invokeAll(
                logs.stream()
                       .map(log -> new LogProcessor(log, wordToBeCount))
                       .collect(Collectors.toList())
        );

        for (Future<Integer> future : futures) {
            try {
                count += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        return count;

    }
}

