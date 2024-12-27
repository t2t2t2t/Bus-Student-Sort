package utils;

import classes.Bus;
import classes.Student;
import classes.User;
import comparators.BusComparators;
import comparators.StudentComparators;
import comparators.UserComparators;

import java.lang.reflect.Field;
import java.util.Comparator;

public class SearchingCompareUtil {

    public static Object CreateTargetObject(Class<?> tempClass, String key, int n) {
        Field[] fields = tempClass.getDeclaredFields();

        if (n < 0 || n >= fields.length) {
            throw new IllegalArgumentException("Неверный индекс поля: " + n);
        }

        Field field = fields[n];
        Class<?> fieldType = field.getType();
        Object value = ConvertToFieldType(key, fieldType);  // Преобразуем key в нужный тип

        if (tempClass == Student.class) {
            Student.StudentBuilder builder = new Student.StudentBuilder();
            switch (n) {
                case 0 -> builder.setGroupNumber(key);
                case 1 -> builder.setAverageScore(value instanceof Double ? (double) value : 0.0);
                case 2 -> builder.setRecordBookNumber(key);
            }
            return builder.build();

        } else if (tempClass == User.class) {
            User.UserBuilder builder = new User.UserBuilder();
            switch (n) {
                case 0 -> builder.setName(key);
                case 1 -> builder.setPassword(key);
                case 2 -> builder.setEmail(key);
            }
            return builder.build();

        } else if (tempClass == Bus.class) {
            Bus.BusBuilder builder = new Bus.BusBuilder();
            switch (n) {
                case 0 -> builder.setNumber(key);
                case 1 -> builder.setModel(key);
                case 2 -> builder.setMileage(value instanceof Integer ? (int) value : 0);
            }
            return builder.build();
        }
        return null;
    }


    private static Object ConvertToFieldType(String key, Class<?> fieldType) {
        try {
            return switch (fieldType.getSimpleName()) {
                case "int", "Integer" -> Integer.valueOf(key);
                case "double", "Double" -> Double.valueOf(key);
                case "long", "Long" -> Long.valueOf(key);
                case "float", "Float" -> Float.valueOf(key);
                case "boolean", "Boolean" -> Boolean.valueOf(key);
                case "String" -> key;
                default -> throw new IllegalArgumentException("Неподдерживаемый тип: " + fieldType.getSimpleName());
            };
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка преобразования строки '" + key +
                    "' в тип " + fieldType.getSimpleName(), e);
        }
    }

    public static Comparator<?> getComparatorForClass(Class<?> tempClass, int index) {
        if (tempClass == Student.class) {
            return new StudentComparators().selectComparator(index);
        } else if (tempClass == User.class) {
            return new UserComparators().selectComparator(index);
        } else if (tempClass == Bus.class) {
            return new BusComparators().selectComparator(index);
        }
        return null;
    }

}
