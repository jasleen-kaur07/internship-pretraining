public class MyList {

    private int[] data;
    private int size;

    public MyList() {
        data = new int[100];
        size = 0;
    }

    public void add(int value) {

        if (size == data.length) {

            // Double the capacity when the array becomes full
            int[] newData = new int[data.length * 2];

            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }

        data[size] = value;
        size++;
    }

    public int get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    
        return data[index];
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
    
            int[] newData = new int[data.length / 2];
    
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
    
            data = newData;
        }
    }

    public void deleteByValue(int value) {

        for (int i = 0; i < size; i++) {
    
            if (data[i] == value) {
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
            System.out.print(data[i] + " ");
        }
    
        System.out.println();
    }

}