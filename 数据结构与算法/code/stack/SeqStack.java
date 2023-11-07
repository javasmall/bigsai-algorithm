package stack;


import java.util.EmptyStackException;

public class SeqStack<T> implements Stack<T> {

    private T array[];
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public SeqStack() {
        this.size = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void push(T data) {
        if (size == array.length) {
            // 如果数组已满，扩展数组
            resizeArray();
        }
        array[size++] = data;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // 下面可以写成 return array[--size];
        T data = array[size - 1];
        size--;
        return data;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeArray() {
        int newCapacity = (int) (array.length * 2);
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public static void main(String[] args) {
        SeqStack<Integer> seqStack = new SeqStack();
        seqStack.push(4);
        seqStack.push(7);
        seqStack.push(8);
        System.out.println(seqStack.peek());
        seqStack.pop();
        System.out.println(seqStack.peek());

    }
}
