import validation_utils.Validate;
import validation_utils.ValidationUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.*;

public class EntityFiller {

    private static final Scanner scanner = new Scanner(System.in);

    public static void fillEntityFields(Object entity, String[] data, boolean isFromFile) {
        Field[] fields = entity.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            try {
                String value = (data != null && i < data.length) ? data[i] : getInput(field);


                // Сначала пытаемся применить валидатор на основе имени поля
                Validate<?> validator = switch (field.getName().toLowerCase()) {
                    case "email" -> new ValidationUtils.UserEmailValidator();
                    case "model" -> new ValidationUtils.BusModelValidator();
                    case "password" -> new ValidationUtils.UserPasswordValidator();
                    case "averagegrade" -> new ValidationUtils.AverageGradeValidator();
                    default -> null;
                };

                // Если валидатор по имени поля не был выбран, применяем валидатор на основе типа данных
                if (validator == null) {
                    System.out.println(field.getType().getName());
                    System.out.println(field.getType());
                    String fieldType = field.getType().getName();
                    switch (fieldType) {
                        case "java.lang.String":
                            validator = new ValidationUtils.StringValidator();
                            break;
                        case "int":
                        case "java.lang.Integer":
                            validator = new ValidationUtils.NumberValidator();
                            break;
                        case "boolean":
                        case "java.lang.Boolean":
                            validator = new ValidationUtils.BooleanValidator();
                            break;
                    }
                }

                Object parsedValue = parseValue(field.getType(), value);

                while (validator != null && !((Validate<Object>) validator).isValid(parsedValue)) {
                    if (isFromFile) {
                        System.out.println("Валидация не прошла: " + field.getName() + ". Пропуск...");
                        parsedValue = null;
                        break;
                    } else {
                        System.out.println("Неправильное значение " + field.getName() + ". Попробуйте снова:");
                        value = getInput(field);
                        parsedValue = parseValue(field.getType(), value);
                    }
                }

                if (parsedValue != null) {
                    field.set(entity, parsedValue);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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
                EntityFiller.fillEntityFields(entity, data,true);
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
                    EntityFiller.fillEntityFields(entity, null,false);
                    entities[i] = entity;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return entities;
        }

}
