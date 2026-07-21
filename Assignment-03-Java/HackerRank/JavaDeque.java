import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

public class JavaDeque {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter n and m: ");
        int n = in.nextInt();
        int m = in.nextInt();

        Deque<Integer> deque = new ArrayDeque<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        int max = 0;

        System.out.println("Enter " + n + " integers:");

        for (int i = 0; i < n; i++) {

            int num = in.nextInt();

            deque.addLast(num);

            map.put(num, map.getOrDefault(num, 0) + 1);

            if (deque.size() > m) {

                int removed = deque.removeFirst();

                map.put(removed, map.get(removed) - 1);

                if (map.get(removed) == 0) {
                    map.remove(removed);
                }
            }

            if (deque.size() == m) {
                max = Math.max(max, map.size());
            }
        }

        System.out.println("Maximum unique integers = " + max);

        in.close();
    }
}