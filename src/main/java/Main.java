import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println(MenuConstants.MAIN_MENU);
            switch (getChoice(0, 3)) {
                case 1:
                    inputMenu(Bus.class);
                    break;
                case 2:
                    inputMenu(User.class);
                    break;
                case 3:
                    inputMenu(Student.class);
                    break;
                case 0:
                    System.out.println(MenuConstants.EXIT_MESSAGE);
                    return;
            }
        }
    }

    public static void inputMenu(Class<?> entityClass) {
        Object[] entities;
        while (true) {
            System.out.println(MenuConstants.INPUT_MENU);
            switch (getChoice(0, 3)) {
                case 1:
                    System.out.println("Ввод вручную выбран.");
                    entities = EntityFiller.fillEntitiesManually(entityClass, getChoice(0, 2000));
                    actionMenu(entities);
                    break;

                case 2:
                    System.out.println("Ввод из файла выбран.");
                    String filePath = EntityFiller.selectFileFromDirectory("src/data");
                    if (filePath != null) {
                        entities = EntityFiller.readEntitiesFromFile(filePath, entityClass);
                        actionMenu(entities);
                    } else {
                        System.out.println("Файл не выбран.");
                    }
                    break;

                case 3:
                    System.out.println("Ввод файла рандомно");
                    entities=RandomEntityGenerator.fillEntitiesRandomly(entityClass, getChoice(0, 2000));
                    actionMenu(entities);
                    break;

                case 0:
                    return;
            }
        }
    }


    public static void actionMenu(Object[] entities) {
        Object[] sortClass;
        Object findObject;
        WriteInFile writer = new WriteInFile();
        //пример записи
        List<Sortable> sortableList = Arrays.stream(entities)
                .filter(e -> e instanceof Sortable)
                .map(e -> (Sortable) e)
                .toList();

        writer.logSortedCollection(sortableList);

        while (true) {
            System.out.println(MenuConstants.ACTION_MENU);
            switch (getChoice(0, 5)) {
                case 1:
                    System.out.println("Сортировка");
                    break;
                case 2:
                    System.out.println("Бинарный поиск");
                    break;
                case 3:
                    System.out.println("Запись в файл с сортировка");
                    writer.logSortedCollection(sortableList);
                    System.out.println("Отсортированные данные записаны.");
                    break;
                case 4:
                    System.out.println("Запись в файл с бинарным поиском");
                    writer.logFoundValue(entities[0]);
                    System.out.println("Отсортированные данные записаны.");
                    break;
                case 5:
                    System.out.println("Вывод данных");
                    for (Object e: entities) {
                        System.out.println(e.toString());
                    }
                    break;
                case 0:
                    return;
            }
        }
    }

    public static int getChoice(int min, int max) {
        while (true) {
            System.out.println("Введите значение");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Введите число от " + min + " до " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }

    public static class MenuConstants {
        public static final String MAIN_MENU =
                "Выберите с каким классом работать:\n" +
                        "1. Автобус (Bus)\n" +
                        "2. Пользователь (User)\n" +
                        "3. Студент (Student)\n" +
                        "0. Выход";

        public static final String INPUT_MENU =
                "Выберите способ заполнения массива:\n" +
                        "1. Ввод вручную\n" +
                        "2. Ввод из файла\n" +
                        "3. Рандомная генерация\n"+
                        "0. Выход из данного меню";

        public static final String ACTION_MENU =
                "Выберите действие:\n" +
                        "1. Сортировка\n" +
                        "2. Поиск\n" +
                        "3. Запись в файл отсортированные данные\n"+
                        "4. Запись в файл найденного объекта\n"+
                        "5. Вывод данных\n"+
                        "0. Выход из данного меню";
        public static final String EXIT_MESSAGE = "\nСпасибо за использование программы! До свидания!";

    }
}
