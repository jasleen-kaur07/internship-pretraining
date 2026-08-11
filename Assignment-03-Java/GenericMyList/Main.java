import java.util.Date;

public class Main {

    public static void main(String[] args) {

        MyList<Integer> numbers = new MyList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("Integer List:");
        numbers.display();

        System.out.println("\nElement at index 1:");
        System.out.println(numbers.get(1));

        numbers.deleteByValue(20);

        System.out.println("\nAfter deleting 20:");
        numbers.display();

        MyList<String> names = new MyList<>();

        names.add("Jasleen");
        names.add("Rahul");
        names.add("Priya");

        System.out.println("\nString List:");
        names.display();

        MyList<Employee> employees = new MyList<>();

        employees.add(new Employee("E101", "Jasleen", 21, new Date()));
        employees.add(new Employee("E102", "Rahul", 23, new Date()));

        System.out.println("\nEmployee List:");
        employees.display();
    }
}