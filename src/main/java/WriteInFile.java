import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


class WriteInFile {
    private static final String FILE_PATH = "file.txt";

    public void logSortedCollection(List<Sortable> sortedCollection) { // Измените на List<Sortable>
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            for (Sortable item : sortedCollection) { // Измените на Sortable
                if (item.isSortable()) {
                    writer.write(item.toString());
                    writer.newLine();
                }
            }
            writer.write("\n\n");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public void logFoundValue(Object foundValue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("Найденный объект " + foundValue.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }


}