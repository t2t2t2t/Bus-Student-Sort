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
                if (field.getType() == String.class) {
                    field.set(entity, value);
                } else if (field.getType() == int.class) {
                    field.set(entity, Integer.parseInt(value));
                } else if (field.getType() == boolean.class) {
                    field.set(entity, Boolean.parseBoolean(value));
                } else if (field.getType() == double.class) {
                    field.set(entity, Double.parseDouble(value));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static String getInput(Field field) {
        System.out.print("Введите значение для поля '" + field.getName() + "' (" + field.getType().getSimpleName() + "): ");
        return scanner.nextLine();
    }

    public static String selectFileFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("Нет файлов для выбора.");
            return null;
        }

        System.out.println("Выберите файл из списка:");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }

        int choice;
        while (true) {
            System.out.print("Введите номер файла: ");
            choice = scanner.nextInt();
            if (choice > 0 && choice <= files.length) {
                break;
            }
            System.out.println("Неверный ввод. Попробуйте снова.");
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
                    System.out.println("Введите данные для объекта типа: " + entityClass.getSimpleName() + " №" + (i + 1));
                    EntityFiller.fillEntityFields(entity, null);
                    entities[i] = entity;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return entities;
        }

}
