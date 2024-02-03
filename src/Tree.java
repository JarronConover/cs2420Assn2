import com.sun.source.tree.BinaryTree;

import java.util.*;

public class Tree<E extends Comparable<? super E>> {
    private BinaryTreeNode root;  // Root of tree
    private String name;     // Name of tree

    /**
     * Create an empty tree
     * 
     * @param label Name of tree
     */
    public Tree(String label) {
        name = label;
    }

    /**
     * Create BST from ArrayList
     *
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Return a string containing the tree contents as a tree with one node per line
     */
    public String toString() {

        if (this.root == null){
            return "Empty Tree";
        }


        return this.name + "\n" + toStringRecursion(this.root, 0);
    }

    private String toStringRecursion(BinaryTreeNode node, int depth){
        String parent = "no parent";

        if (node == null){
            return "";
        }
        if (node.parent != null){
            parent = node.parent.key + "";
        }

        String indent = "";
        for (int i = depth; i>0; i--)
        {
            indent += "  ";
        }

        return toStringRecursion(node.right, depth+1) + indent + node.key + "["+ parent + "]\n" + toStringRecursion(node.left, depth+1);
    }

    /**
     * Return a string containing the tree contents as a single line
     */
    public String inOrderToString() {
        return this.name + ": " + inOrderToStringRecursion(this.root);
    }

    private String inOrderToStringRecursion(BinaryTreeNode node){
        if (node == null){
            return "";
        }
        return inOrderToStringRecursion(node.left) + node.key + " " + inOrderToStringRecursion(node.right);
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
        flipRecursion(this.root);
    }

    private void flipRecursion(BinaryTreeNode node){
        if (node == null){
            return;
        }
        BinaryTreeNode temp = node.right;
        node.right = node.left;
        node.left = temp;

        flipRecursion(node.left);
        flipRecursion(node.right);
    }


    /**
     * Returns the in-order successor of the specified node
     * @param node node from which to find the in-order successor
     */
    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {

       if (node.right != null){
           return rightChildSuccessor(node.right);
       }
       if (node.parent == null){
           return null;
       }
        if (node.parent.left == node){
            return node.parent;
        }
       return parentSuccessor(node.parent);

    }
    private BinaryTreeNode parentSuccessor(BinaryTreeNode node){
        if (node.parent == null){
            return null;
        }
        if (node.parent.left == node)
        {
            return node.parent;
        }
        return parentSuccessor(node.parent);

    }

    private BinaryTreeNode rightChildSuccessor(BinaryTreeNode node){
        if (node.left == null){
            return node;
        }
        return rightChildSuccessor(node.left);
    }

    /**
     * Counts number of nodes in specified level
     *
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        return numOfNodes(this.root, level);
    }
    private int numOfNodes(BinaryTreeNode node, int level){
        if (node == null){
            return 0;
        }
        if (levelOfNode(node, 0) == level){
            return  1;
        }
        return numOfNodes(node.left, level) + numOfNodes(node.right, level);
    }

    private int levelOfNode(BinaryTreeNode node, int level){
        if (node.parent == null){
            return level;
        }
        return levelOfNode(node.parent, level + 1);
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        System.out.println(findLeafs(this.root));
    }

    private String findLeafs(BinaryTreeNode node){
        if (node==null){
            return "";
        }

        if (isLeaf(node))
            return  parentChain(node) +" "+ node.key   + "\n";

        return findLeafs(node.left) + findLeafs(node.right);
    }

    private String parentChain(BinaryTreeNode node){
        if (node.parent == null)
            return "";
        return  parentChain(node.parent)+ " " + node.parent.key;
    }

    private boolean isLeaf(BinaryTreeNode node){
        if (node.left == null && node.right == null){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public int countBST() {
        return countBSTRecursion(this.root);
    }

    private int countBSTRecursion(BinaryTreeNode node){
        if (node == null)
        {
            return 0;
        }
        if (isBST(node)) {
            return countBSTRecursion(node.left) + 1 + countBSTRecursion(node.right);
        }
        return countBSTRecursion(node.left) + countBSTRecursion(node.right);

    }

    private boolean isBST(BinaryTreeNode node){
        if (node == null){
            return true;
        }
        boolean value = false;
        if (node.left != null){
            value = node.key.compareTo(node.left.key) > 0;
        }
        if (node.right != null){
            value = node.key.compareTo(node.right.key) < 0;
        } else {
            value = true;
        }
        return value;



    }



    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root, null);
    }

    public BinaryTreeNode getByKey(E key) {
        return getByKeyRecursion(key, this.root);

    }

    private BinaryTreeNode getByKeyRecursion(E key, BinaryTreeNode node){
        if (node == null){
            return null;
        }
        if (node.key.compareTo(key) == 0){
            return node;
        }
        if (node.key.compareTo(key) > 0){
            return getByKeyRecursion(key, node.left);
        }
        return getByKeyRecursion(key, node.right);
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        ArrayList<BinaryTreeNode> array = new ArrayList<>();
        balanceTreeRecursion(this.root, array);
        this.root = null;
        inputBalanceTree(array, 0, array.size()-1);
    }

    private void inputBalanceTree(ArrayList<BinaryTreeNode> array, int start, int end){
        if (start > end)
            return;

        insert(array.get((start+end)/2).key);
        inputBalanceTree(array, start, (start+end)/2 - 1);
        inputBalanceTree(array, (start+end)/2 + 1, end);
    }

    private void balanceTreeRecursion(BinaryTreeNode node, ArrayList<BinaryTreeNode> array){
        if (node == null){
            return;
        }
        balanceTreeRecursion(node.left, array);
        array.add(node);
        balanceTreeRecursion(node.right, array);
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryTreeNode insert(E x, BinaryTreeNode t, BinaryTreeNode parent) {
        if (t == null) return new BinaryTreeNode(x, null, null, parent);

        int compareResult = x.compareTo(t.key);
        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else {
            t.right = insert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryTreeNode t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.key);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return true;    // Match
        }
    }

    // Basic node stored in unbalanced binary trees
    public class BinaryTreeNode {
        E key;            // The data/key for the node
        BinaryTreeNode left;   // Left child
        BinaryTreeNode right;  // Right child
        BinaryTreeNode parent; //  Parent node

        // Constructors
        BinaryTreeNode(E theElement) {
            this(theElement, null, null, null);
        }

        BinaryTreeNode(E theElement, BinaryTreeNode lt, BinaryTreeNode rt, BinaryTreeNode pt) {
            key = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(key);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.key);
                sb.append(">");
            }

            return sb.toString();
        }
    }
}
