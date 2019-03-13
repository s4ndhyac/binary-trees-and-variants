package trees;

import java.util.Random;

public class Node {

    int key;
    Node left;
    Node right;

    float priority;
    Random r = new Random();

    int height;

    public Node()
    {
        this.height = 1;
        this.key = 0;
        this.priority = r.nextFloat();
        this.left = null;
        this.right = null;
    }

    public Node(int key)
    {
        this.key = key;
        this.priority = r.nextFloat();
        this.left = null;
        this.right = null;
    }

    public Node(int key, int height, Node left, Node right)
    {
        this.key = key;
        this.height = height;
        this.left = left;
        this.right = right;
    }

    public Node(int key, int height, Node left, Node right, float priority)
    {
        this.key = key;
        this.height = height;
        this.left = left;
        this.right = right;
        this.priority = priority;
    }

}
