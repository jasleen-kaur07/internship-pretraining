import java.util.*;

public class JavaHashSet {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        HashSet<List<String>> set = new HashSet<>();

        while (T-- > 0) {

            String left = sc.next();
            String right = sc.next();

            set.add(Arrays.asList(left, right));

            System.out.println(set.size());
        }

        sc.close();
    }
}