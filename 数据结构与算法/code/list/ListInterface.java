package list;


/**
 * @author bigsai
 */
public interface ListInterface<T> {
    void init(int initialSize); // 初始化表
    int length();
    boolean isEmpty(); // 是否为空
    int elemIndex(T value); // 找到编号
    T getElem(int index); // 根据index获取数据
    void add(int index, T value) ; // 根据index插入数据
    void delete(int index) ;
    void add(T value) ; // 尾部插入
    void set(int index, T value) ;
    String toString(); // 转成String输出
}
