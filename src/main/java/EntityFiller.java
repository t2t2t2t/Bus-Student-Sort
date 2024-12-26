import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntityFiller {

    private static final Scanner scanner = new Scanner(System.in);

    public static void fillEntityFields(Object entity, String[] data) {
        Field[] fields = entity.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            try {
                String value = (data != null && i < data.length) ? data[i] : getInput(field);
                switch (field.getType().getName()) {
                    case "java.lang.String":
                        field.set(entity, value);
                        break;

                    case "int":
                        field.set(entity, Integer.parseInt(value));
                        break;

                    case "boolean":
                        field.set(entity, Boolean.parseBoolean(value));
                        break;

                    case "double":
                        field.set(entity, Double.parseDouble(value));
                        break;

                    default:
                        throw new IllegalArgumentException("Unsupported field type: " + field.getType().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private static String getInput(Field field) {
        System.out.print("Enter a value for the field'" + field.getName() + "' (" + field.getType().getSimpleName() + "): ");
        return scanner.nextLine();
    }

    public static String selectFileFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("There are no files to select.");
            return null;
        }

        System.out.println("Select a file from the list:");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }

        int choice;
        while (true) {
            System.out.print("Enter file number: ");
            choice = scanner.nextInt();
            if (choice > 0 && choice <= files.length) {
                break;
            }
            System.out.println("Invalid input. Try again.");
        }
        return files[choice - 1].getAbsolutePath();
    }

    public static Object[] readEntitiesFromFile(String filePath, Class<?> entityClass) {
        List<Object> entities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Object entity = entityClass.getDeclaredConstructor().newInstance();
                EntityFiller.fillEntityFields(entity, data);
                entities.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities.toArray(new Object[0]);
    }

    public static Object[] fillEntitiesManually(Class<?> entityClass, int numberOfEntities) {
        Object[] entities = new Object[numberOfEntities];

        for (int i = 0; i < numberOfEntities; i++) {
            try {
                Object entity = entityClass.getDeclaredConstructor().newInstance();
                System.out.println("Enter data for the object type:" + entityClass.getSimpleName() + " №" + (i + 1));
                EntityFiller.fillEntityFields(entity, null);
                entities[i] = entity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entities;
    }

}