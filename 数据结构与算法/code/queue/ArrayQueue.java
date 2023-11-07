package queue;

public class ArrayQueue<T> implements Queue<T> {
    private T[] array;
    private int front; // 指向队列头部
    private int rear; // 指向队列尾部
    private int size;

    public ArrayQueue(int capacity) {
        array = (T[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public boolean enQueue(T item) {
        if (size == array.length) {
            // 队列已满
            return false;
        }
        rear = (rear + 1) % array.length; // 循环队列，计算新的尾部位置
        array[rear] = item;
        size++;
        return true;
    }

    @Override
    public boolean deQueue() {
        if (isEmpty()) {
            // 队列为空
            return false;
        }
        front = (front + 1) % array.length; // 循环队列，移动头部指针
        size--;
        return true;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
