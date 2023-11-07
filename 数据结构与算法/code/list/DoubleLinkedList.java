package list;

/**
 * @date new version 2023.11.02
 * @author bigsai
 * @param <T>
 */
public class DoubleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    // 在链表头部添加元素
    public void addHead(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.pre = newNode;
            head = newNode;
        }
        size++;
    }

    // 在指定位置插入元素
    public void add(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        if (index == 0) {
            addHead(data);
        } else if (index == size) {
            addTail(data);
        } else {
            Node<T> newNode = new Node<>(data);
            Node<T> preNode = getNode(index-1);
            //step 1 2 新节点与后驱节点建立联系
            newNode.next = preNode;
            preNode.next.pre = newNode;
            //step 3 4 新节点与前驱节点建立联系
            preNode.next = newNode;
            newNode.pre = preNode;
            size++;
        }
    }

    // 在链表尾部添加元素
    public void addTail(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.pre = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // 删除头部元素
    public void deleteHead() {
        if (head != null) {
            head = head.next;
            if (head != null) {
                head.pre = null;
            } else { //此时说明之前head和tail都指向唯一节点，链表删除之后head和tail都应该指向null
                tail = null;
            }
            size--;
        }
    }

    // 删除指定位置的元素
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        if (index == 0) {
            deleteHead();
        } else if (index == size - 1) {
            deleteTail();
        } else {
            Node<T> current = getNode(index);
            current.pre.next = current.next;
            current.next.pre = current.pre;
            size--;
        }
    }

    // 删除尾部元素
    public void deleteTail() {
        if (tail != null) {
            tail = tail.pre;
            if (tail != null) {
                tail.next = null;
            } else {//此时说明之前head和tail都指向唯一节点，链表删除之后head和tail都应该指向null
                head = null;
            }
            size--;
        }
    }

    // 获取指定位置的元素
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> node = getNode(index);
        return node.data;
    }

    // 获取链表的大小
    public int getSize() {
        return size;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        if (index < size / 2) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.pre;
            }
            return current;
        }
    }

    private static class Node<T> {
        T data;
        Node<T> pre;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}

