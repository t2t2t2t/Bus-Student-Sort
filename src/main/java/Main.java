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
        Object[] sortClass;
        Object findObject;
        WriteInFile writer = new WriteInFile();
        // Пример записи
        List<Sortable> sortableList = Arrays.stream(entities)
                .filter(e -> e instanceof Sortable)
                .map(e -> (Sortable) e)
                .toList();

        List<Object> list = Arrays.asList(entities);
       Comparator<?> comparator = ComparatorUtil.getComparator(ClassUtil.getClassFromList(list))
                .selectComparator(1);
        writer.logSortedCollection(sortableList);

        while (true) {
            System.out.println(MenuConstants.ACTION_MENU);
            switch (getChoice(0, 5)) {
                case 1:
                    System.out.println("Sort");

                    var fields = Arrays.stream(entities[0].getClass().getDeclaredFields()).toList();


                    for (int i = 0; i < fields.size(); i++) {
                        System.out.println(i + ": " + fields.get(i).getName());
                    }

                    int fieldIndex = getChoice(0, fields.size() - 1);

                    var selectedField = fields.get(fieldIndex);

                    System.out.println("Selected field: " + selectedField.getName());

                    comparator = ComparatorUtil.getComparator(ClassUtil.getClassFromList(list))
                            .selectComparator(0);
                    SorterUtil.sort(list, comparator, "quick");
                    sortClass=list.toArray();
                    break;
                case 2:
                    System.out.println("Binary Search");
                    Class<?> clazz = list.get(0).getClass();
                    fields = Arrays.stream(entities[0].getClass().getDeclaredFields()).toList();


                    for (int i = 0; i < fields.size(); i++) {
                        System.out.println(i + ": " + fields.get(i).getName());
                    }

                    fieldIndex = getChoice(0, fields.size() - 1);

                    selectedField = fields.get(fieldIndex);

                    System.out.println("Selected field: " + selectedField.getName());

                    Comparator<Object> comparator1 = (Comparator<Object>) SearchingCompareUtil.getComparatorForClass(clazz, fieldIndex);

                    String str=scanner.nextLine();

                    Object target = SearchingCompareUtil.CreateTargetObject(clazz, str,fieldIndex);  // Создаем правильный объект

                    int studentResult = SearchUtility.BinarySearch(list, target, comparator1);

                    findObject=list.get(studentResult);
                    System.out.println(findObject);
                    break;

                case 3:
                    System.out.println("Writing to a file with sorting");
                    System.out.println("The sorted data is recorded.");
                    break;
                case 4:
                    System.out.println("Writing to a file with binary search");
                    writer.logFoundValue(entities[0]);
                    System.out.println("The sorted data is recorded.");
                    break;
                case 5:
                    System.out.println("Data output");
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
