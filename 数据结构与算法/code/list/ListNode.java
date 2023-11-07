package list;

/**
 * @author bigsai
 * @param <T>
 */
public class ListNode<T> {
    T data;//节点的结果
    ListNode next;//下一个连接的节点
    public ListNode(){}
    public ListNode(T data)
    {
        this.data=data;
    }
    public ListNode(T data, ListNode next) {
        this.data = data;
        this.next = next;
    }
}
