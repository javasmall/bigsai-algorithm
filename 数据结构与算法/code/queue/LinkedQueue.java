package queue;

public class LinkedQueue<T> implements Queue<T> {
    private Node<T> head; // 队列头部
    private Node<T> tail; // 队列尾部
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean enQueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode; // 将新元素添加到队列尾部
            tail = newNode; // 更新队列尾部指针 也可携程tail=tail.next
        }
        size++;
        return true;
    }

    @Override
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = head.next; // 移除队列头部的元素
        size--;
        if (head == null) {
            // 如果队列为空，将尾部也设置为null
            tail = null;
        }
        return true;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return head.data; // 返回队列头部的元素数据
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}