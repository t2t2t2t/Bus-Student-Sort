package utils;

import java.util.Comparator;
import java.util.List;

public class SearchUtility {

        public static <T> int BinarySearch(List<T> list, T key, Comparator<T> comparator) {

            list.sort(comparator);

        if (list.isEmpty() || key == null || comparator == null) {
            return -1;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midElement = list.get(mid);

            int comparison = comparator.compare(midElement, key);

            if (comparison == 0) {
                return mid; // Найден элемент
            } else if (comparison < 0) {
                left = mid + 1; // Искать в правой половине
            } else {
                right = mid - 1; // Искать в левой половине
            }
        }

        return -1; // Элемент не найден
    }
}
