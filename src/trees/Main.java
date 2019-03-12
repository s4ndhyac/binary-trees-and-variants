package trees;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        int dataPoints = 10;//Integer.parseInt(args[0]);
        int intervalSize = 10000;//Integer.parseInt(args[1]);
        int numTrials = 10;//Integer.parseInt(args[2]);
        String dataStructure = "1";//args[3];
        String randomOrSequential = "S";//args[4];
        if(randomOrSequential.equals("R"))
            System.out.println("Random: ");
        else if(randomOrSequential.equals("S"))
            System.out.println("Sequential: ");

        double start, end, setAvg = 0, deleteAvg = 0, searchAvg = 0;
        double[] setRT = new double[dataPoints];
        double[] deleteRT = new double[dataPoints];
        double[] searchRT = new double[dataPoints];

        Random random = new Random();
        ITree t;

        for (int i = 1; i < dataPoints; i++) {
            setAvg = 0;
            deleteAvg = 0;
            searchAvg = 0;
            int range = i * intervalSize;
            for (int j = 0; j < numTrials; j++) {
                switch (dataStructure)
                {
                    case "1":
                        t = new BST();
                        break;
                    case "2":
                        t = new Treap();
                        break;
                    case "3":
                        t = new Splay();
                        break;
                    case "4":
                        t = new AVL();
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal function");
                }

                List<Integer> list = new ArrayList<>(i*intervalSize);
                if(randomOrSequential.equals("R"))
                {
                    // generate random keys
                    for (int k = 0; k < range; k++) {
                        list.add(random.nextInt(1000000));
                    }
                }
                else if(randomOrSequential.equals("S"))
                {
                    //generate sequential keys
                    for (int k = 0; k < range; k++) {
                        list.add(k);
                    }
                }

                start = System.currentTimeMillis();
                for (int k = 0; k < range; k++) {
                    Node root = t.insert(t.getRoot(), list.get(k));
                    t.setRoot(root);
                }
                end = System.currentTimeMillis();
                setAvg += (end - start);

                Collections.shuffle(list);
                start = System.currentTimeMillis();
                for (int k = 0; k < range; k++) {
                    t.search(t.getRoot(), list.get(k));
                }
                end = System.currentTimeMillis();
                searchAvg += (end - start);

                Collections.shuffle(list);
                start = System.currentTimeMillis();
                for (int k = 0; k < range; k++) {
                    Node root = t.delete(t.getRoot(), list.get(k));
                    t.setRoot(root);
                }
                end = System.currentTimeMillis();
                deleteAvg += (end - start);
            }

            setRT[i] = setAvg / numTrials;
            System.out.println("i = " + i + ", set: " + setRT[i]);

            searchRT[i] = searchAvg / numTrials;
            System.out.println("i = " + i + ", search: " + searchRT[i]);

            deleteRT[i] = deleteAvg / numTrials;
            System.out.println("i = " + i + ", delete: " + deleteRT[i]);
            System.out.println(" ");

        }
    }
}
