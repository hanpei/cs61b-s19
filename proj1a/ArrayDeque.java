import java.util.Deque;

public class ArrayDeque<T> {
    private int capacity = 8;
    private T[] items;
    private int size = 0;
    private int nextFirstIdx;
    private int nextLastIdx;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        capacity = 8;
        size = 0;
        nextFirstIdx = 0;
        nextLastIdx = 1;
    }

    public ArrayDeque(int value) {
        items = (T[]) new Object[value];
        capacity = value;
        size = 0;
        nextFirstIdx = 0;
        nextLastIdx = 1;
    }

    public ArrayDeque(ArrayDeque<T> other) {
        capacity = other.getCapacity();
        items = (T[]) new Object[capacity];
        for (int i = 0; i < other.getCapacity(); i++) {
            items[size] = other.get(i);
            size += 1;
        }
        nextFirstIdx = other.nextFirstIdx;
        nextLastIdx = other.nextLastIdx;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        if (isFull()) {
            increaseCapacity();
        }
        items[nextFirstIdx] = item;
        size ++;
        nextFirstIdx = minusOne(nextFirstIdx);
    }

    public void addLast(T item) {
        if (isFull()) {
            increaseCapacity();
        }
        items[nextLastIdx] = item;
        size++;
        nextLastIdx = plusOne(nextLastIdx);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (isSparse()) {
            decreaseCapacity();
        }
        int idx = plusOne(nextFirstIdx);
        nextFirstIdx = idx;
        return removeByIdx(idx);
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (isSparse()) {
            decreaseCapacity();
        }
        int idx = minusOne(nextLastIdx);
        nextLastIdx = idx;
        return removeByIdx(idx);
    }

    private T removeByIdx(int idx) {
        T item = items[idx];
        items[idx] = null;
        size--;
        return item;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        return items[index];
    }

    public int getCapacity() {
        return capacity;
    }

    public void increaseCapacity() {
        copyDeque(capacity * 2);
    }

    public void decreaseCapacity() {
        copyDeque(capacity / 2);
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int idx = plusOne(nextFirstIdx + i);
            System.out.println(get(idx));
        }
    }

    private void copyDeque(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            int idx = plusOne(nextFirstIdx + i);
            newItems[i] = items[idx];
        }
        items = newItems;
        capacity = newCapacity;
        nextFirstIdx = newCapacity - 1;
        nextLastIdx = size;
    }

    private int minusOne(int index) {
        int idx;
        if (index == 0) {
            idx = capacity - 1;
        } else {
            idx = index - 1;
        }
        return idx;
    }

    private int plusOne(int index) {
        int idx = (index + 1) % capacity;
        return idx;
    }

    private boolean isFull() {
        return size == capacity;
    }

    private boolean isSparse() {
        double LOADING_FACTOR = 0.25;
        return size <= capacity * LOADING_FACTOR;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        for (int i = 0; i < size; i++) {
            int idx = (nextFirstIdx + i + 1) % capacity;
            if (items[idx] != null) {
                stringBuilder.append(items[idx].toString());
                stringBuilder.append(",");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
