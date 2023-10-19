import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class People {
    int age;
    String firstName;
    String lastName;
    double weight;
    int high;

    public People(int age, String firstName, String lastName, double weight, int high) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.high = high;
    }

    public String getLastName() {
        return lastName;
    }
}

class House {
    String street;
    int number;
    double price;
    int buildingYear;

    public House(String street, int number, double price, int buildingYear) {
        this.street = street;
        this.number = number;
        this.price = price;
        this.buildingYear = buildingYear;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите задание для выполнения (1-6) или 'exit' для выхода:");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    task1();
                    break;
                case "2":
                    task2();
                    break;
                case "3":
                    task3();
                    break;
                case "4":
                    task4();
                    break;
                case "5":
                    task5();
                    break;
                case "6":
                    task6();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте еще раз.");
                    break;
            }
        }
    }

    public static void task1() {
        List<String> strings = Arrays.asList("apple", "banana", "cherry");
        Optional<String> longest = strings.stream()
                .max(Comparator.comparingInt(String::length));
        Optional<String> shortest = strings.stream()
                .min(Comparator.comparingInt(String::length));

        System.out.println("Longest string: " + longest.orElse("List is empty"));
        System.out.println("Shortest string: " + shortest.orElse("List is empty"));
    }

    public static void task2() {
        Random rand = new Random();
        List<Integer> numbers = IntStream.range(0, 100)
                .map(i -> rand.nextInt(51))
                .filter(n -> n >= 25)
                .map(n -> n + 10)
                .map(n -> n * n)
                .boxed()
                .collect(Collectors.toList());

        Optional<Integer> firstLessThan100 = numbers.stream()
                .filter(n -> n < 100)
                .findFirst();
        System.out.println("First number less than 100: " + firstLessThan100.orElse(-1));
    }

    public static void task3() {
        List<People> peoples = new ArrayList<>();
        peoples.add(new People(20, "Alice", "Johnson", 60.5, 170));
        peoples.add(new People(15, "Bob", "Smith", 50.5, 160));

        List<People> filteredPeople = peoples.stream()
                .filter(p -> p.age > 18 && p.high < 180 && p.firstName.startsWith("A"))
                .collect(Collectors.toList());

        List<String> filteredLastNames = peoples.stream()
                .filter(p -> p.age > 30 && p.lastName.startsWith("A") && p.firstName.length() > 5)
                .map(People::getLastName)
                .collect(Collectors.toList());

        System.out.println("Filtered people: " + filteredPeople);
        System.out.println("Filtered last names: " + filteredLastNames);
    }

    public static void task4() {
        Set<String> lastNames = new HashSet<>(Arrays.asList("Johnson", "Williams", "Jones", "Brown"));
        Set<String> filteredLastNames = lastNames.stream()
                .filter(s -> s.startsWith("J"))
                .collect(Collectors.toSet());

        System.out.println("Filtered last names starting with 'J': " + filteredLastNames);
    }

    public static void task5() {
        Random rand = new Random();
        Set<Integer> numbers = IntStream.range(0, 100)
                .map(i -> rand.nextInt(201) - 100)
                .filter(n -> Math.abs(n) < 50)
                .map(n -> n + 20)
                .sorted()
                .skip(4)
                .limit(6)
                .boxed()
                .collect(Collectors.toSet());
        System.out.println("Resulting set: " + numbers);
    }

    public static void task6() {
        List<House> houses = new ArrayList<>();
        houses.add(new House("Main Street", 1, 250000.0, 1990));
        houses.add(new House("Max Platz", 2, 100000.0, 2000));

        OptionalDouble maxPrice = houses.stream()
                .mapToDouble(House::getPrice)
                .max();

        Optional<House> minOddNumberedHouse = houses.stream()
                .filter(h -> h.getNumber() % 2 != 0)
                .min(Comparator.comparingDouble(House::getPrice));

        List<House> midPricedHouses = houses.stream()
                .filter(h -> h.getPrice() < 200000 && h.getPrice() > 100000)
                .limit(2)
                .collect(Collectors.toList());

        List<Double> pricesOnMaxPlatz = houses.stream()
                .filter(h -> "Max Platz".equals(h.street))
                .map(House::getPrice)
                .collect(Collectors.toList());

        long oddNumberedCount = houses.stream()
                .filter(h -> h.getNumber() % 2 != 0)
                .count();

        System.out.println("Max price: " + maxPrice.orElse(-1.0));
        System.out.println("Min odd-numbered house: " + (minOddNumberedHouse.isPresent() ? minOddNumberedHouse.get().price : "Not found"));
        System.out.println("Mid-priced houses: " + midPricedHouses);
        System.out.println("Prices on Max Platz: " + pricesOnMaxPlatz);
        System.out.println("Number of odd-numbered houses: " + oddNumberedCount);
    }
}

