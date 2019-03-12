package trees;

public interface ITree {

    boolean search(Node root, int key);

    Node insert(Node root, int key);

    Node delete(Node root, int key);

    Node getRoot();

    void setRoot(Node root);

    void print();
}
