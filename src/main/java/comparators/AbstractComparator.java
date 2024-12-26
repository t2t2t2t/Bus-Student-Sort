package comparators;

import java.util.Comparator;

public abstract class AbstractComparator {
    public abstract Comparator<?> selectComparator(int index);

}
