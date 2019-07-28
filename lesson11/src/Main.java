import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Класс расчета факториала
 * @author Игорь Евстифеев
 */
public class Main {
    /**
     * Метод проверяет массив на наличие отрицательных чисел
     * @param arraynum - число с массива
     */
    private static void checkArrayNum(int arraynum)
    {
        if (arraynum < 0)
        {
            throw new RuntimeException("Число отрицательное: " + arraynum);
        }
    }

    /**
     * Calculate - класс расчета факториала
     */
    public static class Calculate {
        static ConcurrentSkipListMap<Integer, BigInteger> map = new ConcurrentSkipListMap<>();

        /**
         * Метод определяет наличие в карте меньшего числа чем текущее
         * @param factnum - число по которому рассчитывается факториал
         */
        public static HashMap<Integer, BigInteger> getValueMap(int factnum)
        {
            HashMap<Integer, BigInteger> hashmap = new HashMap<Integer, BigInteger>();
            Set<Map.Entry<Integer, BigInteger>> keySet = map.entrySet();
            for (Map.Entry<Integer, BigInteger> m : keySet) {
                if (factnum > m.getKey()) {
                    hashmap.put(m.getKey(), m.getValue());
                }
            }
            return hashmap;
        }

        /**
         * calcFact - метод рассчитывает факториал с помощью StreamAPI
         * @param factnum - число для расчета факториала
         * @return - возвращает рассчитанное значение
         */

        private BigInteger calcFact(int factnum){

            BigInteger result = BigInteger.valueOf(1);
            int step = 0;
            HashMap<Integer, BigInteger> valuemap = getValueMap(factnum);
            if (valuemap != null)
            {
                for (HashMap.Entry<Integer, BigInteger> s : valuemap.entrySet()) {
                    step = s.getKey();
                    result = s.getValue();
                }
            }
            if (map.get(factnum) != null) {
                result = map.get(factnum);
            } else
                for (int i = step+1; i <= factnum; i++) {
                    result = IntStream.rangeClosed(2, factnum)
                            .parallel()
                            .mapToObj(BigInteger::valueOf)
                            .reduce(BigInteger::multiply).get();
                }
            map.put(factnum, result);
            return result;
        }

        /**
         * fillArray - создает массив значений и вызывает расчет значений факториала
         * @param countelem - количество элементов массива
         * @param rangefrom - диапазон рандома "от"
         * @param rangeto   - диапазон рандома "до"
         */
        public void fillArray(int countelem, int rangefrom, int rangeto)
        {
            int[] array = new int[countelem+1];

            BigInteger value;

            for (int i=1; i<array.length; i++)
            {
                array[i] = rangefrom + (int) (Math.random() * ((rangeto-rangefrom)+1));

                checkArrayNum(array[i]);

                int finalI = array[i];

                value = calcFact(finalI);

                System.out.println("Число: " + array[i] + " Факториал: " + value);
            }
            map.clear();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество элементов массива:");
        int countelem = sc.nextInt();
        System.out.println("Введите значение 'от' диапазона для рандома:");
        int rangefrom = sc.nextInt();
        System.out.println("Введите значение 'до' диапазона для рандома:");
        int rangeto = sc.nextInt();
        sc.close();
        Calculate fact = new Calculate();
        fact.fillArray(countelem, rangefrom, rangeto);
    }
}