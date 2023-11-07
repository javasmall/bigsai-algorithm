package stack;

import java.util.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> top;
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LinkedStack<Integer> seqStack = new LinkedStack();
        seqStack.push(4);
        seqStack.push(7);
        seqStack.push(8);
        System.out.println(seqStack.peek());
        seqStack.pop();
        System.out.println(seqStack.peek());

    }

    public static int longestValidParentheses(String s) {
        int max = 0;
        int value[] = new int[s.length() + 1];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                index++;
                value[index] = 0;
            } else {//")"
                if (index == 0) {
                    value[0] = 0;
                } else {
                    value[index - 1] += value[index--] + 2;//叠加
                    if (value[index] > max)//更新
                        max = value[index];
                }
            }
        }
        return max;
    }

}
