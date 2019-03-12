package trees;

import java.util.LinkedList;
import java.util.Queue;

public class BST implements ITree {

    Node root;

    BST() {
        this.root = null;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public void setRoot(Node root) {
        this.root = root;
    }

    // Helper function to find maximum value node in subtree rooted at ptr
    public Node maximumKey(Node ptr)
    {
        while (ptr.right != null) {
            ptr = ptr.right;
        }

        return ptr;
    }

    @Override
    public boolean search(Node root, int key)
    {
        // if key is not present in the key
        if (root == null) {
            return false;
        }

        // if key is found
        if (root.val == key) {
            return true;
        }

        // if given key is less than the root node, search in the left subtree
        if (key < root.val) {
            return search(root.left, key);
        }

        // else search in the right subtree
        return search(root.right, key);
    }

    // Recursive function to insert an key into BST
    @Override
    public Node insert(Node root, int key)
    {
        // if the root is null, create a new node an return it
        if (root == null) {
            return new Node(key);
        }

        // if given key is less than the root node, recurse for left subtree
        if (key < root.val) {
            root.left = insert(root.left, key);
        }

        // if given key is more than the root node, recurse for right subtree
        else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    // Function to delete node from a BST
    @Override
    public Node delete(Node root, int key) {
        // base case: key not found in tree
        if (root == null) {
            return root;
        }

        // if given key is less than the root node, recurse for left subtree
        if (key < root.val) {
            return delete(root.left, key);
        }

        // if given key is more than the root node, recurse for right subtree
        else if (key > root.val) {
            return delete(root.right, key);
        }

        // key found
        else {
            // Case 1: node to be deleted has no children (it is a leaf node)
            if (root.left == null && root.right == null) {
                // update root to null
                return null;
            }

            // Case 2: node to be deleted has two children
            else if (root.left != null && root.right != null) {
                // find its in-order predecessor node
                Node predecessor = maximumKey(root.left);

                // Copy the value of predecessor to current node
                root.val = predecessor.val;

                // recursively delete the predecessor. Note that the
                // predecessor will have at-most one child (left child)
                delete(root.left, predecessor.val);
            }

            // Case 3: node to be deleted has only one child
            else {
                // find child node
                Node child = (root.left != null) ? root.left : root.right;
                root = child;
            }
        }
        return root;
    }

    @Override
    public void print() {

        Queue<Node> q = new LinkedList<>();
        q.offer(this.root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                Node cur = q.poll();
                if(cur == null){
                    System.out.print("\t\t");
                } else {
                    System.out.print("\t" + cur.val + "\t");
                    q.offer(cur.left);
                    q.offer(cur.right);
                }
            }
            System.out.println();
        }
        return;

    }


}
