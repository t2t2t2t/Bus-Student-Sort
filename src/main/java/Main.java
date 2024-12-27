import classes.Bus;
import classes.Student;
import classes.User;
import utils.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
                    System.out.println("Manual input is selected.");
                    entities = EntityFiller.fillEntitiesManually(entityClass, getChoice(0, 2000));
                    actionMenu(entities);
                    break;

                case 2:
                    System.out.println("Input from file is selected");
                    String filePath = EntityFiller.selectFileFromDirectory("src/data");
                    if (filePath != null) {
                        entities = EntityFiller.readEntitiesFromFile(filePath, entityClass);
                        actionMenu(entities);
                    } else {
                        System.out.println("No file selected.");
                    }
                    break;

                case 3:
                    System.out.println("Entering a file randomly");
                    entities=RandomEntityGenerator.fillEntitiesRandomly(entityClass, getChoice(0, 2000));
                    actionMenu(entities);
                    break;

                case 0:
                    return;
            }
        }
    }


    public static void actionMenu(Object[] entities) {
        List<Object> sortList = null;
        Object findObject = null;
        WriteInFile writer = new WriteInFile();
        List<Object> list = Arrays.asList(entities);

        while (true) {
            System.out.println(MenuConstants.ACTION_MENU);
            int choice = getChoice(0, 5);
            switch (choice) {
                case 1:
                    sortList = sortEntities(list);
                    System.out.println(sortList);
                    break;
                case 2:
                    findObject = performBinarySearch(list);
                    break;
                case 3:
                    System.out.println(sortList);
                    writeSortedCollectionToFile(writer, sortList);
                    break;
                case 4:

                    writeFoundValueToFile(writer, findObject);
                    break;
                case 5:
                    displayEntities(entities);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static List<Object> sortEntities(List<Object> list) {
        Comparator<?> comparator = ComparatorUtil.getComparator(ClassUtil.getClassFromList(list)).selectComparator(1);

        System.out.println("Sort");
        var fields = Arrays.stream(list.get(0).getClass().getDeclaredFields()).toList();

        for (int i = 0; i < fields.size(); i++) {
            System.out.println(i + ": " + fields.get(i).getName());
        }

        int fieldIndex = getChoice(0, fields.size() - 1);
        var selectedField = fields.get(fieldIndex);
        System.out.println("Selected field: " + selectedField.getName());

        comparator = ComparatorUtil.getComparator(ClassUtil.getClassFromList(list)).selectComparator(0);
        SorterUtil.sort(list, comparator, "quick");

        // Проверка изменения sortList
        System.out.println("Sorting completed. Sorted list: " + list);
        return list; // Возвращаем отсортированный список
    }

    private static Object performBinarySearch(List<Object> list) {
        System.out.println("Binary Search");
        Class<?> clazz = list.get(0).getClass();
        var fields = Arrays.stream(clazz.getDeclaredFields()).toList();

        for (int i = 0; i < fields.size(); i++) {
            System.out.println(i + ": " + fields.get(i).getName());
        }

        int fieldIndex = getChoice(0, fields.size() - 1);
        var selectedField = fields.get(fieldIndex);
        System.out.println("Selected field: " + selectedField.getName());

        Comparator<Object> comparator = (Comparator<Object>) SearchingCompareUtil.getComparatorForClass(clazz, fieldIndex);
        System.out.print("Enter search value: ");
        String str = scanner.nextLine();
        Object target = SearchingCompareUtil.CreateTargetObject(clazz, str, fieldIndex);

        int resultIndex = SearchUtility.BinarySearch(list, target, comparator);
        if (resultIndex >= 0) {
            Object foundObject = list.get(resultIndex);
            System.out.println("Found object: " + foundObject);
            return foundObject;
        } else {
            System.out.println("Object not found.");
            return null;
        }
    }

    private static void writeSortedCollectionToFile(WriteInFile writer, List<Object> sortList) {
        if (sortList != null && !sortList.isEmpty()) {
            writer.logSortedCollection(sortList);
            System.out.println("The sorted data is recorded.");
        } else {
            System.out.println("No sorted data to write.");
        }
    }

    private static void writeFoundValueToFile(WriteInFile writer, Object findObject) {
        if (findObject != null) {
            writer.logFoundValue(findObject);
            System.out.println("The found object is recorded.");
        } else {
            System.out.println("No object found to write.");
        }
    }

    private static void displayEntities(Object[] entities) {
        System.out.println("Data output:");
        for (Object e : entities) {
            System.out.println(e.toString());
        }
    }

    public static int getChoice(int min, int max) {
        while (true) {
            System.out.println("Enter value");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Enter number from" + min + " to  " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter number");
            }
        }
    }

    public static class MenuConstants {
        public static final String MAIN_MENU =
                "Select which class to work with:\n" + "1. Bus\n" + "2. User\n" + "3. Student\n" + "0. Exit";

        public static final String INPUT_MENU =
                "Select a method for filling the array:\n" + "1. Manual input\n" + "2. Input from file\n" + "3. Random generation\n" + "0. Exit this menu";

        public static final String ACTION_MENU =
                "Select an action:\n" + "1. Sorting\n" + "2. Search\n" + "3. Writing sorted data to a file\n"+ "4. Writing the found object to a file\n"+ "5 . Data output\n"+ "0. Exit this menu";
        public static final String EXIT_MESSAGE = "\nThank you for using the program! Goodbye!";

    }
}
