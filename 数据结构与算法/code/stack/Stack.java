package stack;

public interface Stack<T> {
    void push(T item);      // 压栈
    T pop();               // 弹栈
    T peek();              // 获取栈顶元素
    boolean isEmpty();     // 判断栈是否为空
    int size();            // 返回栈的大小
}
