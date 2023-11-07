package queue;

public interface Queue<T> {
    // 向队列尾部插入元素，如果成功插入则返回 true，否则返回 false
    boolean enQueue(T item);

    // 从队列头部删除一个元素，如果成功删除则返回 true，否则返回 false
    boolean deQueue();

    // 返回队列头部的元素，如果队列为空，返回 null
    T peek();

    // 检查队列是否为空
    boolean isEmpty();

    // 返回队列的大小
    int size();
}
