import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class ReadCallable implements Callable<Map<String, Set<String>>> {
    private static final String SEPARATOR = ";";
    private final String filename;

    public ReadCallable(String filename) {
        this.filename = filename;
    }

    @Override
    public Map<String, Set<String>> call() throws Exception {
        Map<String, Set<String>> result = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            int lineNumber = 1;
            String[] titles = {};
            List<Set<String>> sets = new ArrayList<>();

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals("")) {
                    if (lineNumber++ == 1) {
                        titles = currentLine.split(SEPARATOR);
                        for (String ignored : titles) {
                            sets.add(new HashSet<>());
                        }
                    } else {
                        String[] elements = currentLine.split(SEPARATOR);
                        for (int i = 0; i < elements.length; i++) {
                            sets.get(i).add(elements[i]);
                        }
                    }
                    for (int i = 0; i < titles.length; i++) {
                        result.put(titles[i], sets.get(i));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
