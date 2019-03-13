
import java.util.LinkedList;
import java.util.Queue;

public class Splay implements ITree {

    Node root;

    Splay() {
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

        if (h.key > key) {
            // key not in tree, so we're done
            if (h.left == null) {
                return h;
            }

            if (h.left.key > key) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            }
            else if (h.left.key < key) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null)
                    h.left = rotateLeft(h.left);
            }

            if (h.left == null)
                return h;
            else
                return rotateRight(h);
        }
        else if (h.key < key) {
            // key not in tree, so we're done
            if (h.right == null) {
                return h;
            }


            if (h.right.key > key) {
                h.right.left  = splay(h.right.left, key);
                if (h.right.left != null)
                    h.right = rotateRight(h.right);
            }
            else if (h.right.key < key) {
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

    @Override
    public boolean search(Node root, int key)
    {
        this.root = splay(root, key);
        if (this.root.key == key)
            return true;
        else
            return false;
    }

    @Override
    public  Node insert(Node root, int key)
    {
        // Simple case: If tree is empty
        if (root == null) {
            return new Node(key);
        }

        //Bring the closest leaf node to root
        root = splay(root, key);

        //If key is already present then return
        if(root.key == key)
            return root;

        // Insert new node at root
        // If root's key is greater, make root as right child
        // of newnode and copy the left child of root to newnode
        if (root.key > key) {
            Node n = new Node(key);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        // Insert new node at root
        // If root's key is smaller, make root as left child
        // of newnode and copy the right child of root to newnode
        else if (root.key < key) {
            Node n = new Node(key);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        return root;

    }

    @Override
    public Node delete(Node root, int key)
    {
        // empty tree
        if (root == null)
            return null;

        // Splay the given key
        root = splay(root, key);

        // If key is not present, then return
        if(root.key != key)
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

    @Override
    public void print()
    {
        Queue<Node> q = new LinkedList<>();
        q.offer(this.root.left);
        int depth = 0;
        long eNum = 0;
        while(!q.isEmpty()){
            int size = q.size();
            depth++;
            for(int i = 0; i < size; i++){
                Node cur = q.poll();

                if(cur == null){
                    System.out.print("\t\t");
                } else {
                    eNum++;
                    System.out.print("\t" + cur.key + "\t");
                    q.offer(cur.left);
                    q.offer(cur.right);
                }
            }
            System.out.println();
        }
        System.out.println("depth: " + depth + "\telements:" + eNum);
        return;
    }


}
