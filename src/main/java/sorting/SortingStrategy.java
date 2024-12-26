package sorting;

import java.util.Comparator;
import java.util.List;

public interface SortingStrategy<T> {
     <T> void sort(List<T> list, Comparator<T> comparator);
}
