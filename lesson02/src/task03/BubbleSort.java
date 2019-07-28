package task03;

public class BubbleSort implements Sorter {

    @Override
    public void sort(Main.Person[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int f = i; f < arr.length; f++) {
                if (arr[i].compareTo(arr[f]) > 0) {
                    Main.Person tmp = arr[i];
                    arr[i] = arr[f];
                    arr[f] = tmp;
                }
            }
        }
    }

}
