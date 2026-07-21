import java.util.Scanner;
import java.util.Stack;

public class JavaStack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String input = sc.next();
            Stack<Character> stack = new Stack<>();

            boolean balanced = true;

            for (char ch : input.toCharArray()) {

                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                } else {

                    if (stack.isEmpty()) {
                        balanced = false;
                        break;
                    }

                    char top = stack.pop();

                    if ((ch == ')' && top != '(') ||
                        (ch == '}' && top != '{') ||
                        (ch == ']' && top != '[')) {

                        balanced = false;
                        break;
                    }
                }
            }

            if (balanced && stack.isEmpty()) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
        }

        sc.close();
    }
}