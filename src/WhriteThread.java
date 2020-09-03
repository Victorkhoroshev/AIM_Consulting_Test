import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class WhriteThread extends Thread {
    private final String fileName;
    private final Set<String> value;

    public WhriteThread(String fileName, Set<String> value) {
        this.fileName = fileName;
        this.value = value;
    }

    @Override
    public void run() {
        try (FileWriter fileWriter = new FileWriter(new File(fileName + ".txt"))) {
            String[] valueArray = value.toArray(new String[value.size()]);
            for (int i = 0; i < valueArray.length; i++){
                fileWriter.write(valueArray[i] + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

