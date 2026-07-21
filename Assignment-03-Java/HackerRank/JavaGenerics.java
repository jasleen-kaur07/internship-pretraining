public class JavaGenerics {

    static class Printer {

        public <T> void printArray(T[] array) {
            for (T element : array) {
                System.out.println(element);
            }
        }
    }

    public static void main(String[] args) {

        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};

        Printer printer = new Printer();

        printer.printArray(intArray);
        printer.printArray(stringArray);
    }
}