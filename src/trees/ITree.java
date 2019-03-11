package trees;

public interface ITree {
    Node search(Node root, int key);

    Node insert(Node root, int key);

    Node delete(Node root, int key);

    void print();

    void printElementsAtDepth();
}
