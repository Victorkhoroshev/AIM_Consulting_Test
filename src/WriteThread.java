import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class WriteThread extends Thread {
    private final String fileName;
    private final Set<String> value;

    public WriteThread(String fileName, Set<String> value) {
        this.fileName = fileName;
        this.value = value;
    }

    @Override
    public void run() {
        try (FileWriter fileWriter = new FileWriter(new File(fileName + ".txt"))) {
            for (String s : value) {
                fileWriter.write(s + TestConsoleApp.SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

