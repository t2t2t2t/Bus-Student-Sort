package utils;

import sorting.BubbleSort;
import sorting.QuickSort;
import sorting.SelectionSort;
import sorting.SortingStrategy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SorterUtil {
    private static final Map<String, SortingStrategy> strategies = new HashMap<>();

    static {
        strategies.put("bubble", new BubbleSort());
        strategies.put("quick", new QuickSort());
        strategies.put("selection", new SelectionSort());
    }

    public static  <T> void sort(List<?> list, Comparator<?> comparator, String strategyName) {
        SortingStrategy strategy = strategies.get(strategyName.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown sorting strategy: " + strategyName);
        }
        strategy.sort(list, comparator);
    }
}
