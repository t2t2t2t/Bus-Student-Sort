import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


class WriteInFile {
    private static final String FILE_PATH = "file.txt";

    public void logSortedCollection(List sortedCollection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            for (Object item : sortedCollection) {
                    writer.write(item.toString());
                    writer.newLine();
            }
            writer.write("\n\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void logFoundValue(Object foundValue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("Found object" + foundValue.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


}