package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int iterationSeq = 1;
        int iteration = 2000;
        int maxSize = 1<<12;

        int[] searchSeqCosts = new int[maxSize+1];
        int[] insertSeqCosts = new int[maxSize+1];
        int[] deleteSeqCosts = new int[maxSize+1];

        double[] searchAvgCosts = new double[maxSize+1];
        double[] insertAvgCosts = new double[maxSize+1];
        double[] deleteAvgCosts = new double[maxSize+1];

        HashSet<Integer> set = new HashSet<>(2*maxSize);
        ArrayList<Integer> listRandom = new ArrayList<>(maxSize);
        ArrayList<Integer> listSeq = new ArrayList<>(maxSize);

        int tmp = 0;
        while(tmp < maxSize) {
            listSeq.add(tmp++);
        }

        Random random = new Random();
        while(listRandom.size() < maxSize) {
            tmp = random.nextInt(Integer.MAX_VALUE);
            if(set.contains(tmp))
                continue;
            set.add(tmp);
            listRandom.add(tmp);
        }

        searchSeqCosts[0] = 1;
        insertSeqCosts[0] = 1;
        deleteSeqCosts[0] = 1;

        searchAvgCosts[0] = 1.0;
        insertAvgCosts[0] = 1.0;
        deleteAvgCosts[0] = 1.0;



        double start = 0, end = 0, seqInsertAvg = 0, seqSearchAvg = 0, seqDeleteAvg = 0;
        // test sequential data set
        for (int i = 0; i < iterationSeq; i++) {

            BST t = new BST();

            start = System.currentTimeMillis();
            for (int j = 0; j < maxSize; j++) {
                t.root = t.insert(t.root, listSeq.get(j));
            }
            end = System.currentTimeMillis();
            seqInsertAvg += (end - start);

            start = System.currentTimeMillis();
            for (int j = 0; j < maxSize; j++) {
                t.root = t.search(t.root, listSeq.get(j));
            }
            end = System.currentTimeMillis();
            seqSearchAvg += (end - start);

            for (int j = maxSize-1; j > -1; j--) {
                t.root = t.delete(t.root, listSeq.get(j));
            }
            end = System.currentTimeMillis();
            seqDeleteAvg += (end - start);
        }

        insertSeqCosts[i+1] /= (double)iterationSeq;
        searchSeqCosts[i+1] /= (double)iterationSeq;
        deleteSeqCosts[i+1] /= (double)iterationSeq;

        // test random data set
        for (int i = 0; i < iteration; i++) {
            BST t = new BST();

            Collections.shuffle(listRandom);
            ArrayList<Integer> tmpList = new ArrayList<>(maxSize);
            for (int j = 0; j < maxSize; j++) {
                tmp = t.insertAndCount(listRandom.get(j));
                tmpList.add(listRandom.get(j));
                insertAvgCosts[j+1] += tmp;

                tmp = (int)t.searchAndCount(tmpList.get(random.nextInt(tmpList.size())))[1];
                searchAvgCosts[j+1] += tmp;
            }

            Collections.shuffle(listRandom);
            for (int j = 0; j < maxSize; j++) {
                tmp = t.deleteAndCount(listRandom.get(j));
                deleteAvgCosts[t.size+1] += tmp;
            }
        }
        for (int i = 0; i < maxSize; i++) {
            searchAvgCosts[i+1] /= (double)iteration;
            insertAvgCosts[i+1] /= (double)iteration;
            deleteAvgCosts[i+1] /= (double)iteration;
        }

        StringBuffer s1 = new StringBuffer("searchSeqCosts = [");
        StringBuffer s2 = new StringBuffer("insertSeqCosts = [");
        StringBuffer s3 = new StringBuffer("deleteSeqCosts = [");
        StringBuffer s4 = new StringBuffer("searchAvgCosts = [");
        StringBuffer s5 = new StringBuffer("insertAvgCosts = [");
        StringBuffer s6 = new StringBuffer("deleteAvgCosts = [");
        String tmpS;

        for (int i = 0; i < maxSize+1; i++) {
            s1.append(searchSeqCosts[i]);
            s1.append(",");
            s2.append(insertSeqCosts[i]);
            s2.append(",");
            s3.append(deleteSeqCosts[i]);
            s3.append(",");

            tmpS = String.valueOf(searchAvgCosts[i]);
            if(tmpS.length() > 4)
                tmpS = tmpS.substring(0, 4);
            s4.append(tmpS);
            s4.append(",");

            tmpS = String.valueOf(insertAvgCosts[i]);
            if(tmpS.length() > 4)
                tmpS = tmpS.substring(0, 4);
            s5.append(tmpS);
            s5.append(",");

            tmpS = String.valueOf(deleteAvgCosts[i]);
            if(tmpS.length() > 4)
                tmpS = tmpS.substring(0, 4);
            s6.append(tmpS);
            s6.append(",");
        }
        s1.deleteCharAt(s1.length()-1);
        s2.deleteCharAt(s2.length()-1);
        s3.deleteCharAt(s3.length()-1);
        s1.append("];");
        s2.append("];");
        s3.append("];");
        s4.deleteCharAt(s4.length()-1);
        s5.deleteCharAt(s5.length()-1);
        s6.deleteCharAt(s6.length()-1);
        s4.append("];");
        s5.append("];");
        s6.append("];");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(" ");
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);



    }
}
