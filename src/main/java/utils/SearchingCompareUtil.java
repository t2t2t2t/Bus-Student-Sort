package utils;

import classes.Bus;
import classes.Student;
import classes.User;
import comparators.BusComparators;
import comparators.StudentComparators;
import comparators.UserComparators;

import java.util.Comparator;

public class SearchingCompareUtil {

    public static Object CreateTargetObject(Class<?> tempClass, String key) {
        if (tempClass == Student.class) {
            return new User.UserBuilder()  // Create an instance of the builder
                    .setName(key)      // Set the name
                    .setPassword("")   // Set the password
                    .setEmail("")      // Set the email
                    .build();          // Build and return the User
        } else if (tempClass == User.class) {
            return new User.UserBuilder()  // Create an instance of the builder
                    .setName(key)      // Set the name
                    .setPassword("")   // Set the password
                    .setEmail("")      // Set the email
                    .build();          // Build and return the User
        } else if (tempClass == Bus.class) {
            return new User.UserBuilder()  // Create an instance of the builder
                    .setName(key)      // Set the name
                    .setPassword("")   // Set the password
                    .setEmail("")      // Set the email
                    .build();          // Build and return the User
        }
        return null;
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
