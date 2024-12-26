package utils;

import java.util.List;

public class ClassUtil {
    public static <T> Class<?> getClassFromList(List<T> list) {
        if (!list.isEmpty()) {
            return list.get(0).getClass();
        } else {
            System.out.println("List is empty, cannot determine class.");
            return Object.class;
        }
    }
    private ClassUtil(){}
}
