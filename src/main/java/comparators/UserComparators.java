package comparators;

import classes.User;

import java.util.Comparator;

public class UserComparators extends AbstractComparator {
    public Comparator<User> selectComparator(int index){
        if (index ==0)
            return UserComparators::nameComparator;
        if (index == 2)
            return UserComparators::emailComparator;
        if (index == 1)
            return UserComparators::passwordComparator;
        return null;
    }

    private static int nameComparator(User o1, User o2){
        return o1.getName().compareTo(o2.getName());
    }
    private static int emailComparator(User o1, User o2){
        return o1.getEmail().compareTo(o2.getEmail());
    }

    private static int passwordComparator(User o1, User o2){
        return o1.getPassword().compareTo(o2.getPassword());
    }
}
