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
        // TODO:
        // get each node and the child node
        //print the child first then in brackets parent node
        // 2 [3]
        //space them parent node to the left and bottom node to the right

        //Think about how to print a tree (sideways) to the console.  The right side of the tree needs to be printed first, then the left side.  The amount to indent a node, is based on how deep it is in the tree.

        return this.name + "\n";
    }

    /**
     * Return a string containing the tree contents as a single line
     */
    public String inOrderToString() {
        // TODO:
        //return each node in least to greatest
        // left node right

        return this.name + ": ";
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
        // TODO:
        //flips the left and right children recursively

    }

    /**
     * Returns the in-order successor of the specified node
     * @param node node from which to find the in-order successor
     */
    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {
        // TODO:
        //find in order successor of node passed in
        //if node has right child it must be along that path
        //if node has no right child it must be parent or higher
        //if parent has right child that isnt the node then return parent
        //else, travers vack up tree following right child

        //two recursive parts
        //one will find the min
        // one performs a recursive traversal of the parents

        //Thinking about the in-order successor problem.  The BST code provided to you, maintains a parent reference at each node.  You'll need to take advantage of the parent reference in order to create an efficient solution to that method.
        return null;
    }

    /**
     * Counts number of nodes in specified level
     *
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        // TODO:
        //Returns number of nodes at specified level in tree
        //If level doesnt exist return 0
        // iterates through the entire bst
        // checks number of parents and adds one if the number of parents is correct

        //When counting the nodes in a level, think about how you count leaf nodes.  Leaf nodes are those that have no children, you do a test specifically for them and count them up.  When counting nodes in a level, you need to keep track of what level you are in during a traversal, and only count those nodes that meet the parameter criteria.
        return 0;
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        // TODO:
        //print root to leaf paths
        //one line at a time
        //check to see if leaf; node; left/right

        //While doing a traversal of the tree (e.g., in-order), when you hit a leaf node, you know there is a path along the traversal that needs to be reported.
        //Once you have that leaf node, you know its value and can start a direct traversal from the root node, moving left and right along the path to that leaf node; printing out the node values (keys) as the path is traversed

    }

    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public int countBST() {
        // TODO:
        // count all sub trees that are bsts
        //traverse entire tree
        //leaf nodes are a binary tree
        // in a bst every node is a binary search tree

        return 0;
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
        // TODO:
        //return the node with the associated key
        //assume BST and key exists in tree

        return null;
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        // TODO:
        ///start over with a new tree.
        //in order traversal
        //store nodes in ArrayList
        //insert(binary traversal of entire array list)


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
