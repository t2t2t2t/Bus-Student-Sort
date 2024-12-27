import validation_utils.Validate;
import validation_utils.ValidationUtils;

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
            Validate<?> validator = getValidator(field);

            String value = (data != null && i < data.length) ? data[i] : getInput(field);
            Object parsedValue = parseValue(field.getType(), value);

            // Валидация значения
            if (validator != null && !((Validate<Object>) validator).isValid(parsedValue)) {
                if (data != null) {
                    System.out.println("Валидация не прошла: " + field.getName() + ". Пропуск...");
                    continue; // Пропускаем значение при загрузке из файла
                } else {
                    // Запрашиваем значение заново, если это ручной ввод
                    while (!((Validate<Object>) validator).isValid(parsedValue)) {
                        System.out.println("Неправильное значение " + field.getName() + ". Попробуйте снова:");
                        value = getInput(field);
                        parsedValue = parseValue(field.getType(), value);
                    }
                }
            }

            // Устанавливаем значение в поле
            try {
                field.set(entity, parsedValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static Validate<?> getValidator(Field field) {
        return switch (field.getName().toLowerCase()) {
            case "email" -> new ValidationUtils.UserEmailValidator();
            case "model" -> new ValidationUtils.BusModelValidator();
            case "password" -> new ValidationUtils.UserPasswordValidator();
            case "averagegrade" -> new ValidationUtils.AverageGradeValidator();
            default -> null;
        };
    }

    private static Object parseValue(Class<?> type, String value) {
        switch (type.getName()) {
            case "java.lang.String":
                return value;
            case "int":
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid integer value: " + value + ". Assigning default value (0).");
                    return 0;
                }
            case "double":
                try {
                    return Double.parseDouble(value);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid double value: " + value + ". Assigning default value (0.0).");
                    return 0.0;
                }
            case "boolean":
                return Boolean.parseBoolean(value);
            default:
                throw new IllegalArgumentException("Unsupported field type: " + type.getName());
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