package tree;

public class BinarySearchTree {
     class TreeNode {
        int value;          // 节点的值
        TreeNode left;    // 左子节点的引用
        TreeNode right;   // 右子节点的引用

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


    private TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    // 插入操作
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    // 查找操作
    public boolean search(int value) {
        return searchNode(root, value);
    }

    private boolean searchNode(TreeNode root, int value) {
        if (root == null) {
            return false;
        }

        if (value == root.value) {
            return true;
        }

        if (value < root.value) {
            return searchNode(root.left, value);
        } else {
            return searchNode(root.right, value);
        }
    }


    // 删除操作
    public void delete(int value) {
        root = deleteNode(root, value);
    }

    private TreeNode deleteNode(TreeNode root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.value) {//向左
            root.left = deleteNode(root.left, value);
        } else if (value > root.value) {//向右
            root.right = deleteNode(root.right, value);
        } else {//等于时候
            //想象链表删除，跳过当前节点root.right = root.right.right
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            //替换
            root.value = maxValue(root.left);
            //删除叶子节点了
            root.right = deleteNode(root.left, root.value);
        }

        return root;
    }

    private int minValue(TreeNode node) {
        int minValue = node.value;
        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }

    private int maxValue(TreeNode node) {
        int minValue = node.value;
        while (node.right != null) {
            minValue = node.right.value;
            node = node.right;
        }
        return minValue;
    }
}
