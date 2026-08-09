class Add {

    void add(int... numbers) {

        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {

            sum += numbers[i];

            System.out.print(numbers[i]);

            if (i != numbers.length - 1) {
                System.out.print("+");
            }
        }

        System.out.println("=" + sum);
    }
}

public class JavaVarargs {

    public static void main(String[] args) {

        Add obj = new Add();

        obj.add(1, 2);
        obj.add(1, 2, 3);
        obj.add(1, 2, 3, 4, 5);
        obj.add(1, 2, 3, 4, 5, 6);
    }
}