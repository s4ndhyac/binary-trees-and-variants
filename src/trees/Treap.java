
import java.util.LinkedList;
import java.util.Queue;

public class Treap implements ITree {

    Node root;

    Treap() {
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

    public Node rotateLeft(Node root)
    {
        Node R = root.right;
        Node X = root.right.left;

        // rotate
        R.left = root;
        root.right = X;

        // set new root
        return R;
    }


    public Node rotateRight(Node root)
    {
        Node L = root.left;
        Node Y = root.left.right;

        // rotate
        L.right = root;
        root.left = Y;

        // set new root
        return L;
    }

    // Recursive function to insert a given key with a priority into Treap
    @Override
    public Node insert(Node root, int data)
    {
        // base case
        if (root == null) {
            return new Node(data);
        }

        // if given data is less than the root node, insert in the left subtree
        // else insert in the right subtree
        if (data < root.key)
        {
            root.left = insert(root.left, data);

            // rotate right if heap property is violated
            if (root.left != null && root.left.priority > root.priority) {
                root = rotateRight(root);
            }
        }
        else
        {
            root.right = insert(root.right, data);

            // rotate left if heap property is violated
            if (root.right != null && root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
        }

        return root;
    }

    // Recursive function to search for an key in the given Treap
    @Override
    public boolean search(Node root, int key)
    {
        // if key is not present in the key
        if (root == null) {
            return false;
        }

        // if key is found
        if (root.key == key) {
            return true;
        }

        // if given key is less than the root node, search in the left subtree
        if (key < root.key) {
            return search(root.left, key);
        }

        // else search in the right subtree
        return search(root.right, key);
    }

    // Recursive function to delete an key from the given Treap
    @Override
    public  Node delete(Node root, int key)
    {
        // base case: key not found in tree
        if (root == null) {
            return null;
        }

        // if given key is less than the root node, recuse for left subtree
        if (key < root.key) {
            root.left = delete(root.left, key);
        }

        // if given key is more than the root node, recuse for right subtree
        else if (key > root.key) {
            root.right = delete(root.right, key);
        }

        // if key found
        else
        {
            // Case 1: node to be deleted has no children (it is a leaf node)
            if (root.left == null && root.right == null)
            {
                // deallocate the memory and update root to null
                root = null;
            }

            // Case 2: node to be deleted has two children
            else if (root.left != null && root.right != null)
            {
                // if left child has less priority than right child
                if (root.left.priority < root.right.priority)
                {
                    // call rotateLeft on root
                    root = rotateLeft(root);

                    // recursively delete the left child
                    root.left = delete(root.left, key);
                }
                else
                {
                    // call rotateRight on root
                    root = rotateRight(root);

                    // recursively delete the right child
                    root.right = delete(root.right, key);
                }
            }

            // Case 3: node to be deleted has only one child
            else
            {
                // find child node
                Node child = (root.left != null)? root.left: root.right;
                root = child;
            }
        }

        return root;
    }


    @Override
    public void print()
    {
        Queue<Node> q = new LinkedList<>();
        q.offer(this.root.left);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                Node cur = q.poll();
                if(cur == null){
                    System.out.print("\t\t");
                } else {
                    System.out.print("\t" + cur.key + "\t");
                    q.offer(cur.left);
                    q.offer(cur.right);
                }
            }
            System.out.println();
        }
        return;

    }



}
