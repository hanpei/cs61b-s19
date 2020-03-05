public class LinkedListDeque<T> {

    private class Node<T> {
        private T value;
        private Node prev;
        private Node next;

        private Node() {
            this.value = null;
            this.prev = null;
            this.next = null;
        }

        private Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
        private Node(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int size = 0;
    private Node head;
    private Node tail;

    public LinkedListDeque() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addFirst(T item) {
        Node node = new Node<>(item);
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        size += 1;
    }

    public void addLast(T item) {
        Node node = new Node<>(item);
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    public void printDeque() {
        while (head.next != null && head.next != tail) {
            System.out.println(head.next.value);
            head = head.next;
        }
    }

    public T removeFirst() {
        Node first = head.next;
        head.next = head.next.next;
        first.next.prev = first.prev;
        first.prev = null;
        first.next = null;
        size -= 1;
        return (T) first.value;
    }
    public T removeLast() {
        Node last = tail.prev;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        last.next = null;
        last.prev = null;
        size -= 1;
        return (T) last.value;
    }
    public T get(int index) {
        if (index > size) {
            return null;
        }
        Node p = head;
        while (index >= 0) {
            p = p.next;
            index -= 1;
        }
        return (T) p.value;
    }
}
