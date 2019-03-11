package trees;


public class Splay implements ITree {

    // right rotate
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    // left rotate
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    private Node splay(Node h, int key) {
        if (h == null) return null;

        if (h.val > key) {
            // key not in tree, so we're done
            if (h.left == null) {
                return h;
            }

            if (h.left.val > key) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            }
            else if (h.left.val < key) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null)
                    h.left = rotateLeft(h.left);
            }

            if (h.left == null)
                return h;
            else
                return rotateRight(h);
        }
        else if (h.val < key) {
            // key not in tree, so we're done
            if (h.right == null) {
                return h;
            }


            if (h.right.val > key) {
                h.right.left  = splay(h.right.left, key);
                if (h.right.left != null)
                    h.right = rotateRight(h.right);
            }
            else if (h.right.val < key) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }

            if (h.right == null)
                return h;
            else
                return rotateLeft(h);
        }
        else
            return h;
    }

    public Node search(Node root, int key)
    {
        return splay(root, key);
    }

    public  Node insert(Node root, int key)
    {
        // Simple case: If tree is empty
        if (root == null) {
            return new Node(key);
        }

        //Bring the closest leaf node to root
        root = splay(root, key);

        //If key is already present then return
        if(root.val == key)
            return root;

        // Insert new node at root
        // If root's key is greater, make root as right child
        // of newnode and copy the left child of root to newnode
        if (root.val > key) {
            Node n = new Node(key);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        // Insert new node at root
        // If root's key is smaller, make root as left child
        // of newnode and copy the right child of root to newnode
        else if (root.val < key) {
            Node n = new Node(key);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        return root;

    }

    public Node delete(Node root, int key)
    {
        // empty tree
        if (root == null)
            return null;

        // Splay the given key
        root = splay(root, key);

        // If key is not present, then return
        if(root.val != key)
            return root;

        // If key is present
        // If left child of root does not exist
        // make root->right as root
        if (root.left == null) {
            root = root.right;
        }
        else {

            Node temp = root;
            /*Note: Since key == root->key,
            so after Splay(key, root->lchild),
            the tree we get will have no right child tree
            and maximum node in left subtree will get splayed*/
            // New root
            root = splay(root.left, key);
            root.right = temp.right;
        }
        // else: it wasn't in the tree to remove
        return root;
    }

    public void print()
    {

    }

    public void printElementsAtDepth()
    {

    }


}