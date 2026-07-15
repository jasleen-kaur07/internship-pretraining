public class MyList<T> {

    private Object[] data;
    private int size;

    public MyList() {
        data = new Object[100];
        size = 0;
    }

    public void add(T value) {

        if (size == data.length) {

            // Double the capacity when the array becomes full
            Object[] newData = new Object[data.length * 2];

            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }

        data[size] = value;
        size++;
    }

    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return (T) data[index];
    }

    public void deleteByIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        // Shift elements left after deletion
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;

        // Shrink the array when it becomes mostly empty
        if (size > 0 && size <= data.length / 4 && data.length > 100) {

            Object[] newData = new Object[data.length / 2];

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }
    }

    public void deleteByValue(T value) {

        for (int i = 0; i < size; i++) {

            if (data[i].equals(value)) {
                deleteByIndex(i);
                return;
            }

        }
    }

    public int size() {
        return size;
    }

    public void display() {

        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }
}