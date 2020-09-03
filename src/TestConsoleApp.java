import java.util.*;
import java.util.concurrent.*;

public class TestConsoleApp {
    static final String SEPARATOR = ";";
    static Map<String, Set<String>> result = new HashMap<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Thread> threadsList = new ArrayList<>();
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Future<Map<String, Set<String>>>> futuresRead = new ArrayList<>();

        for (String arg : args) {
            Callable<Map<String, Set<String>>> callable = new ReadCallable(arg);
            futuresRead.add(executor.submit(callable));
        }

        for (Future<Map<String, Set<String>>> future : futuresRead) {
            result.putAll(future.get());
        }

        for (Map.Entry<String, Set<String>> pair: result.entrySet()) {
            threadsList.add(new WriteThread(pair.getKey(), pair.getValue()));
        }
        for (Thread t : threadsList){
            t.start();
        }
        for (Thread t : threadsList) {
            t.join();
        }

    }

}
