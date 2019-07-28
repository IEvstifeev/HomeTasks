package task03;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * @author Igor Evstifeev
 * @version 2.0
 * Класс сортирует двумя разными способами
 */
public class Main {

    public enum Sex
    {
        Man,
        Woman;

        public static String getRandomSex()
        {
            Sex[] sexval = Sex.values();
            Random generator = new Random();
            return sexval[generator.nextInt(sexval.length)].toString();
        }
    }

    /**
     * getRndName - метод генерирует имя
     * @return возвращает сгенерированное имя
     */
    public static String getRndName(){
        String name;
        int min = 3;
        int max = 10;
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Random rand = new Random();
        int length = min + rand.nextInt(max - min + 1);
        char[] word = new char[length];
        for (int i = 0; i < length; i++) {
            word[i] = chars[rand.nextInt(chars.length)];
        }
        word[0] = Character.toUpperCase(word[0]);
        name = new String(word);
        return name;
    }

    /**
     * Person - класс описывающий персоны
     */
    public static class Person implements Comparable<Person> {
        private String name;
        private Integer age;
        private String sex;

        public String getName()
        {
            return name;
        }

        public int getAge()
        {
            return age;
        }

        public String getSex()
        {
            return sex;
        }

        public Person(String name, int age, String sex){
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        @Override
        public int compareTo(Person another) {
            int cmpSex = (sex == another.sex) ? 0 : (sex == Sex.Man.toString() ? -1 : 1);
            if (cmpSex != 0) {
                return cmpSex;
            }
            int cmpAge = age - another.age;
            if (cmpAge != 0) {
                return cmpAge;
            }
            return name.compareTo(another.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(name, person.name) &&
                    sex == person.sex;
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            result = 31 * result + sex.hashCode();
            return result;
        }

        @Override
        public String toString(){
            return "Имя: " + this.name + ", Возраст: " + this.age + ",Пол: " + this.sex;
        }
    }

    public static void main(String[] args)
    {

        Person[] perArr = new Person[10001];

        Random rnd = new Random();

        for(int i=0; i<perArr.length; i++)
        {
            try {
                perArr[i] = new Person(getRndName(), rnd.nextInt(100), Sex.getRandomSex());
            }
            catch (ArrayIndexOutOfBoundsException exc){
                System.out.println("Обращение к несуществующему элементу массива");
            }
        }

        Person[] perArr2 = Arrays.copyOf(perArr, perArr.length);

        Sorter s = new BubbleSort();

        long start = System.currentTimeMillis();

        s.sort(perArr);

        System.out.println("Время работы BubbleSort: " + (System.currentTimeMillis() - start) + " ms");

        System.out.println("Результаты сортировки BubbleSort: ");

        for (int i=0; i<perArr.length; i++){
            System.out.println(perArr[i].toString());
        }

        Sorter s2 = new AnotherSort();

        start = System.currentTimeMillis();

        s2.sort(perArr2);

        System.out.println("Время работы AnotherSort: " + (System.currentTimeMillis() - start) + " ms");

        System.out.println("Результаты сортировки AnotherSort: ");

        for (int i=0; i<perArr2.length; i++){
            System.out.println(perArr2[i].toString());
        }
    }
}
