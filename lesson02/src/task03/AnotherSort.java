package task03;

import java.util.Arrays;
import java.util.List;

public class AnotherSort implements Sorter {
    @Override
    public void sort(Main.Person[] arr) {
        Arrays.sort(arr);
    }

    public static <T> Object test(List<? super T> list) {
        return list.get(0);
    }
}
