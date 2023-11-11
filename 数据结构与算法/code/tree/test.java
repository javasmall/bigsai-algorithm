package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class test {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> value = new ArrayList<>();//存储到的最终结果
    if (root == null)
        return value;
    int index = 0;//判断
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        List<Integer> va = new ArrayList<>();//临时 用于存储到value中
        int len = queue.size();//当前层的数量
        for (int i = 0; i < len; i++) {
            TreeNode node = queue.poll();
            if (index % 2 == 0) {
                va.add(node.val);
            } else {
                va.add(0, node.val);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        value.add(va);
        index++;
    }
    return value;
}
}
