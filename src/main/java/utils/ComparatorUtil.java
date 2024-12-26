package utils;

import classes.Bus;
import classes.Student;
import classes.User;
import comparators.AbstractComparator;
import comparators.BusComparators;
import comparators.StudentComparators;
import comparators.UserComparators;

import java.util.*;

public class ComparatorUtil {

    public static HashMap<Class, AbstractComparator> comparatorHashMap = new HashMap<>();
    static {
        comparatorHashMap.put(Bus.class, new BusComparators());
        comparatorHashMap.put(User.class, new UserComparators());
        comparatorHashMap.put(Student.class, new StudentComparators());
    }

    public static AbstractComparator getComparator(Class clazz){
        return comparatorHashMap.get(clazz);
    }
    private ComparatorUtil(){}
}
