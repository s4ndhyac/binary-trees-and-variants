package trees;

import java.util.Random;

public class Node {

    int val;
    Node left;
    Node right;

    float priority;
    Random r = new Random();

    int height;

    public Node()
    {
        this.height = 1;
        this.val = 0;
        this.priority = r.nextFloat();
        this.left = null;
        this.right = null;
    }

    public Node(int val)
    {
        this.val = val;
        this.priority = r.nextFloat();
        this.left = null;
        this.right = null;
    }

    public Node(int val, int height, Node left, Node right)
    {
        this.val = val;
        this.height = height;
        this.left = left;
        this.right = right;
    }

    public Node(int val, int height, Node left, Node right, float priority)
    {
        this.val = val;
        this.height = height;
        this.left = left;
        this.right = right;
        this.priority = priority;
    }

}
