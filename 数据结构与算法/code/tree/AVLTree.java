package tree;


public class AVLTree {
    class TreeNode {
        int value;
        int height; // 节点的高度，用于平衡因子计算
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.height = 1; // 初始高度为1
            this.left = null;
            this.right = null;
        }
    }
    private TreeNode root;

    // 获取节点的高度
    private int height(TreeNode node) {
        return (node != null) ? node.height : 0;
    }

    // 计算平衡因子
    private int getBalanceFactor(TreeNode node) {
        return (node != null) ? height(node.left) - height(node.right) : 0;
    }

    // 更新节点的高度
    private void updateHeight(TreeNode node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    // 右旋转操作 返回操作完的新节点
    private TreeNode rightRotate(TreeNode oldRoot) {
        TreeNode newRoot = oldRoot.left;
        TreeNode temp = newRoot.right;

        // 执行旋转
        newRoot.right = oldRoot;
        oldRoot.left = temp;

        // 更新节点高度 其他节点的高度并没有更新
        updateHeight(oldRoot);
        updateHeight(newRoot);

        // 返回新的根节点
        return newRoot;
    }

    // 左旋转操作 返回操作完的新节点
    private TreeNode leftRotate(TreeNode oldRoot) {
        TreeNode newRoot = oldRoot.right;
        TreeNode temp = newRoot.left;

        // 执行旋转
        newRoot.left = oldRoot;
        oldRoot.right = temp;

        // 更新节点高度 先更新低层的
        updateHeight(oldRoot);
        updateHeight(newRoot);

        // 返回新的根节点
        return newRoot;
    }

    // 插入节点
    public void insert(int value) {
        root = insert(root, value);
    }

    // 辅助方法：插入节点（递归）
    private TreeNode insert(TreeNode node, int value) {
        // 执行标准BST插入
        if (node == null) {
            return new TreeNode(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            // 重复值不允许插入（可以根据实际需求进行调整）
            return node;
        }

        // 更新节点的高度
        updateHeight(node);

        // 获取平衡因子
        int balance = getBalanceFactor(node);

        // 进行平衡操作
        // 左子树比右子树高，需要右旋转
        if (balance > 1 && value < node.left.value) {
            return rightRotate(node);
        }
        // 右子树比左子树高，需要左旋转
        if (balance < -1 && value > node.right.value) {
            return leftRotate(node);
        }
        // 左右不平衡，先左旋后右旋
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // 右左不平衡，先右旋后左旋
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 中序遍历（用于验证树的平衡性）
    public void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.value + " ");
            inOrderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        // 插入一些节点
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);
        avlTree.insert(25);

        // 中序遍历输出，验证平衡性
        avlTree.inOrderTraversal(avlTree.root);
    }
}

