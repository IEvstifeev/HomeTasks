package task02;

import java.util.Scanner;

/**
 * @author Igor Evstifeev
 * @version 2.0
 */

public class Main {
    public static void main(String[] args) {
        double sqr;
        long b;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество элементов массива:");
        int a = sc.nextInt();

        System.out.println("Введите значение 'от' диапазона для рандома:");
        int c = sc.nextInt();

        System.out.println("Введите значение 'до' диапазона для рандома:");
        int d = sc.nextInt();

        sc.close();
        int[] array = new int[a];
        for (int i = 0; i < array.length; i++) {
            array[i] = c + (int) (Math.random() * d);
            if (array[i]<0)
            {
                throw new RuntimeException("Число отрицательное" + array[i]);
            }
            sqr = Math.sqrt(array[i]);
            b = (long) sqr;
            if (Math.pow(b, 2) == array[i])
            {
                System.out.println("Наше число: " + array[i]);
            }

        }
    }
}
