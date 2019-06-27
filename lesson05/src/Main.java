import java.util.*;
import java.util.Comparator;
import java.util.Objects;

/**
 * Person - класс описывающий владельца
 */
class Person {
    /**
     * Sex - перечисление (муж, жен)
     */
    public enum Sex{
        Муж,
        Жен;
    };
    private String name;
    private int age;
    private Sex sex;

    /**
     * Person - конструтор
     * @param name - поле Имя
     * @param age - поле возраст
     * @param sex - поле Пол
     */
    public Person(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * getName - возвращает имя владельца
     * @return - возвращается имя
     */
    public String getName() {
        return name;
    }

    /**
     * setName - устанавливает Имя
     * @param name - имя владельца
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getAge - возраст владельца
     * @return - возвращает возраст владельца
     */
    public int getAge() {
        return age;
    }

    /**
     * setAge - устанавливает возраст владельца
     * @param age - возраст владельца
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getSex - пол владельца
     * @return - возвращает пол владельца
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * setSex - устанавливает пол владельца
     * @param sex - пол владельца
     */
    public void setSex(Sex sex){
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(sex, person.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex);
    }
}
/**
 * Animal - класс описывающий животных
 */
class Animal {

    private int id;
    private String name;
    private int weight;
    private Person person;

    /**
     * Animal        - конструтор
     * @param id     - идентификатор животного
     * @param name   - кличка животного
     * @param weight - вес животного
     */
    public Animal(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Animal() {
    }

    /**
     * getWeight - вес животного
     * @return   - возвращает вес животного
     */
    public int getWeight() {
        return weight;
    }

    /**
     * setWeight     - устанавливает вес животного
     * @param weight - вес животного
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * getId   - идентификатор животного
     * @return - возвращает идентификатор животного
     */
    public int getId() {
        return id;
    }

    /**
     * setId     - устанавливает идентификатор животного
     * @param id - идентификатор животного
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * getName - кличка живоного
     * @return - возвращает кличку животного
     */
    public String getName() {

        return name;
    }

    /**
     * setName     - устанавливает кличку животного
     * @param name - кличка животного
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getPerson - владелец животного
     * @return   - возвращает данные владельца
     */
    public Person getPerson() {
        return person;
    }

    /**
     * setPerson     - устанавливает владельца животного
     * @param person - владелец животного
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * getAnimalByName - поиск животного по кличке
     * @param animals Список животных для поиска
     * @param name    Имя искомого животного
     * @return Найденное животное
     */
    public static Animal getAnimalByName(Set<Animal> animals, String name) {
        Animal animal = new Animal();
        for (Animal a : animals) {
            if (a.getName().equals(name)) {
                animal = a;
            }
        }
        return animal;
    }
    /**
     * getAnimalById  - поиск животного по id
     * @param animals - Список животных для поиска
     * @param id      - уникальный идентификатор животного.
     * @return        - возвращает найденное животное
     */
    static Animal getAnimalById(Set<Animal> animals, int id) {
        Animal animal = new Animal();
        for (Animal a : animals) {
            if (a.getId() == id && a.getId() != 0) {
                animal = a;
            }
        }
        return animal;
    }

    /**
     * updateAnimal  - метод для изменения данных животного по его id
     * @param animal - Сущность животного получается через метод getAnimalById
     * @param name   - Параметр для установки нового имени
     * @param person - Параметр для установки нового владельца
     * @return       - возвращает измененную запись
     */
    static Animal updateAnimal(Animal animal, String name, Person person) {
        if (animal.getId() != 0 && animal.getName() != null) {
            animal.setName(name);
            animal.setPerson(person);
        }
        return animal;
    }

    /**
     * addAnimal      - метод добавления животного в список
     * @param animals - список для добавления
     * @param animal  - животное для добавления
     */
    static void addAnimal(Set<Animal> animals, Animal animal) {
        if (animals.contains(animal)) {
            try {
                throw new Exception("Животное с такими параметрами уже существует - " + animal.toString());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        animals.add(animal);
    }

    /**
     * printSetAnimal - метод вывода данных животных
     * @param animals - список животных
     */
    static void printSetAnimal(List<Animal> animals) {
        for (Animal a : animals) {
            System.out.println(a.getPerson().getName() + " " + a.getName() + " " + a.getWeight());
        }
    }
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                weight == animal.weight &&
                Objects.equals(name, animal.name) &&
                Objects.equals(person, animal.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, person);
    }
}
/**
 * SortAnimal - класс сортировки по параметрам: имя владельца, кличка животного, вес животного
 */

class SortAnimal implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        if (o1!=null && o2!=null) {
            int namePersonCompare = o1.getPerson().getName().compareTo(o2.getPerson().getName());
            if (namePersonCompare != 0) {
                return namePersonCompare;
            }
            int nameAnimalCompare = o1.getName().compareTo(o2.getName());
            if (nameAnimalCompare != 0) {
                return nameAnimalCompare;
            }
        }
        return o1.getWeight() - o2.getWeight();
    }
}

public class Main {

    public static void main(String[] args) {

        Set<Animal> animals = new HashSet<>();
        Animal animal1 = new Animal(1, "Владик", 6);
        animal1.setPerson(new Person("Валерий", 44, Person.Sex.Муж));
        Animal animal2 = new Animal(1, "Владик", 6);
        animal2.setPerson(new Person("Петро", 44, Person.Sex.Муж));
        Animal animal3 = new Animal(2, "Мурзик", 3);
        animal3.setPerson(new Person("Анатолий", 32, Person.Sex.Муж));
        Animal animal4 = new Animal(3, "Анжелина", 12);
        animal4.setPerson(new Person("Маргарита", 32, Person.Sex.Жен));
        Animal animal5 = new Animal(4, "Тигра", 11);
        animal5.setPerson(new Person("Антон", 28, Person.Sex.Муж));
        Animal animal6 = new Animal(1, "Владик", 6);
        animal6.setPerson(new Person("Валерий", 44, Person.Sex.Муж));
        Animal animal = new Animal();
        animal.addAnimal(animals, animal1);
        animal.addAnimal(animals, animal2);
        animal.addAnimal(animals, animal3);
        animal.addAnimal(animals, animal4);
        animal.addAnimal(animals, animal5);
        animal.addAnimal(animals, animal6);

        System.out.println("Не сортированный список:");
        for (Animal a : animals) {
            System.out.println(a);
        }

        System.out.println("Поиск животного по кличке и идентификатору:");
        System.out.println("Найдено совпадение по имени " + animal.getAnimalByName(animals, "Тигра"));
        System.out.println("Найдено совпадение по id " + animal.getAnimalById(animals, 2));
        System.out.println("Изменение клички и владельца у животного:");
        System.out.println("У питомца с id 1 изменено имя и возраст владельца " + animal.updateAnimal(animal.getAnimalById(animals, 1), "Спиноза", new Person("Александр", 22, Person.Sex.Муж)));

        List<Animal> sortedAnimalSet = new ArrayList<>(animals);
        Collections.sort(sortedAnimalSet, new SortAnimal());
        System.out.println("Отсортированный список - владелец, кличка,вес питомца:");
        animal.printSetAnimal(sortedAnimalSet);
    }
}
