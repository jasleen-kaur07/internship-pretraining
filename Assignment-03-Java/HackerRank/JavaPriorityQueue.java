import java.util.*;

class Student {
    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return cgpa;
    }
}

public class JavaPriorityQueue {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of events: ");
        int n = Integer.parseInt(sc.nextLine());

        PriorityQueue<Student> pq = new PriorityQueue<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {

                if (Double.compare(s2.getCGPA(), s1.getCGPA()) != 0)
                    return Double.compare(s2.getCGPA(), s1.getCGPA());

                if (!s1.getName().equals(s2.getName()))
                    return s1.getName().compareTo(s2.getName());

                return Integer.compare(s1.getID(), s2.getID());
            }
        });

        for (int i = 0; i < n; i++) {

            String event = sc.nextLine();
            String[] parts = event.split(" ");

            if (parts[0].equals("ENTER")) {

                String name = parts[1];
                double cgpa = Double.parseDouble(parts[2]);
                int id = Integer.parseInt(parts[3]);

                pq.add(new Student(id, name, cgpa));

            } else if (parts[0].equals("SERVED")) {

                if (!pq.isEmpty()) {
                    pq.poll();
                }
            }
        }

        if (pq.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            while (!pq.isEmpty()) {
                System.out.println(pq.poll().getName());
            }
        }

        sc.close();
    }
}