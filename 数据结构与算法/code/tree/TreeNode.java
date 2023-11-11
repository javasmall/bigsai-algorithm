package tree;

public class TreeNode {
    int value;          // 节点的值
    TreeNode left;    // 左子节点的引用
    TreeNode right;   // 右子节点的引用

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
