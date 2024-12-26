import classes.Bus;
import classes.Student;
import classes.User;
import utils.SorterUtil;
import utils.ClassUtil;
import utils.ComparatorUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Main {
    public static Comparator<?> comparator;
    public static void main(String[] args) {
        List<Bus> list = Arrays.asList(
                new Bus.BusBuilder().setNumber("2213").setModel("aasda").setMileage(21312).build(),
                new Bus.BusBuilder().setNumber("123").setModel("zadwa").setMileage(1234).build(),
                new Bus.BusBuilder().setNumber("213").setModel("b23ss").setMileage(321).build()
                );
        List<?> userList = Arrays.asList(
                new User.UserBuilder().setName("aaa").setEmail("b@a.com").setPassword("123321").build(),
        new User.UserBuilder().setName("ccc").setEmail("c@a.com").setPassword("53414").build(),
        new User.UserBuilder().setName("bba").setEmail("a@a.com").setPassword("32122").build()
        );

        List<?> studentList = Arrays.asList(
                new Student.StudentBuilder().setAverageScore(1.2).setGroupNumber("12312").setRecordBookNumber("s1321").build(),
                new Student.StudentBuilder().setAverageScore(8.2).setGroupNumber("822w").setRecordBookNumber("b12c1").build(),
                new Student.StudentBuilder().setAverageScore(3.2).setGroupNumber("5f1").setRecordBookNumber("dd21wd").build()
        );
        ;
        //Возможно стоит добавить статику для текущего класса списка
        comparator = ComparatorUtil.getComparator(ClassUtil.getClassFromList(list))
                .selectComparator(1);

        SorterUtil.sort(list, comparator, "bubble");
        System.out.println(list);

        comparator = ComparatorUtil.getComparator(ClassUtil.getClassFromList(userList))
                .selectComparator(1);
        SorterUtil.sort(userList,comparator, "quick");
        System.out.println(userList);

        comparator = ComparatorUtil.getComparator(ClassUtil.getClassFromList(studentList))
                .selectComparator(1);
        SorterUtil.sort(studentList,comparator, "selection");
        System.out.println(studentList);
    }
}
