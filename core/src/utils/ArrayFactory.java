package utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by apple on 3/15/16.
 */
public class ArrayFactory {

    public static int[] genArray() {

        int[] a = new int[randInt(10, Constants.MAX_ARRAY_ELEMENT)];

        for (int i = 0; i < a.length; i++) {
            a[i] = randInt(Constants.MIN_ELEMENT_VAL, Constants.MAX_ELEMENT_VAL);
            a[i] = a[i] - a[i] % 10;
        }

        return a;
    }

    public static int[] genArrayForCount() {
        int [] a = new int[randInt(10, Constants.MAX_ARRAY_ELEMENT)];

        for (int i = 0; i < a.length; i++) {
            a[i] = randInt(1, 9);
            a[i] *= 30;
        }

        return a;
    }

    public static int[] genOrderArray(int value1, int valueN, int k) {
        int [] a = new int[valueN - value1 + 1];

        for(int i = 0; i < a.length; i++) {
            a[i] = (i==0) ? value1*k : a[i-1] + k;
        }

        return a;
    }

    public static ArrayList<String> bubbleSort(int[] a) {

        ArrayList<String> ins = new ArrayList<String>();

        int[] b = a.clone();
        int n = b.length;

        boolean swapped = false;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                // highlight (i-1) and i
                if (i == 1) ins.add(new String("HLO " + (i - 1) + " " + i));
                if (b[i - 1] > b[i]) {
                    // swap (i-1) and i
                    ins.add(new String("SW " + (i - 1) + " " + i));
                    int tmp = b[i - 1];
                    b[i - 1] = b[i];
                    b[i] = tmp;
                    swapped = true;
                }
//                ins.add(new String("UHL "+(i-1)+" "+i));
                if (i != n - 1)
                    ins.add(new String("UHLHLO " + (i - 1) + " " + (i + 1)));
//                    ins.add(new String("UHLHLS "+(i-1)+" "+i));
                // unhighlight (i-1) and i
            }
            ins.add(new String("UHLHLG " + (n - 2) + " " + (n - 1)));
            // highlight (n-1)
            n--;
        }
        while (swapped);

        for (int i = n - 1; i >= 0; i--)
            ins.add(new String("HLG " + i));

        return ins;
    }

    public static ArrayList<String> selectionSort(int[] a) {

        ArrayList<String> ins = new ArrayList<String>();

        int[] b = a.clone();
        int n = b.length;

        for (int i = 0; i < n - 1; i++) {

            ins.add(new String("HLR " + i));

            int jMin = i;
            for (int j = i + 1; j < n; j++) {

                if (j == jMin + 1) ins.add(new String("HLO " + j));

                if (b[j] < b[jMin]) {
                    ins.add(new String("UHLHLR " + jMin + " " + j));
                    jMin = j;
                } else {
                    if (j != n - 1)
                        ins.add(new String("UHLHLO " + j + " " + (j + 1)));
                    else ins.add(new String("UHL " + j));
                }
            }

            if (jMin != i) {
                ins.add(new String("SW " + i + " " + jMin));
                int tmp = b[i];
                b[i] = b[jMin];
                b[jMin] = tmp;
            }

            ins.add(new String("HLG " + i));
        }

        ins.add(new String("HLG " + (n - 1)));

        return ins;
    }

    public static ArrayList<String> insertionSort(int[] a) {
        ArrayList<String> ins = new ArrayList<String>();

        int[] b = a.clone();
        int n = b.length;

        for (int i = 1; i < n; i++) {
            int j = i;
            ins.add(new String("HLR " + i));

            while (j > 0 && b[j - 1] > b[j]) {
                int tmp = b[j - 1];
                b[j - 1] = b[j];
                b[j] = tmp;
                ins.add(new String("SW " + (j - 1) + " " + j));

                j--;
            }
            if (j >= 0) ins.add(new String("HLO " + j));
            if (i == 1) ins.add(new String("HLO " + 0 + " " + 1));
        }

        String inse = new String("HLG");
        for (int i = 0; i < n; i++) {
            inse = inse + " " + i;
        }
        ins.add(inse);

        return ins;
    }

    public static ArrayList<String> quickSort(int[] a, int lo, int hi, ArrayList<String> ins) {

        int i = lo;
        int j = hi;
        int x = a[(lo + hi) / 2];
        int trackMidId = (lo + hi) / 2;

        String inse = new String("HLB");
        for (int it = lo; it <= hi; it++)
            inse = inse + " " + it;
        ins.add(inse);

        ins.add(new String("HLR " + (lo + hi) / 2));

        if (lo == trackMidId) ins.add(new String("HLO " + hi));
        else if (hi == trackMidId) ins.add(new String("HLO " + lo));
        else ins.add(new String("HLO " + lo + " " + hi));

        while (i <= j) {
            while (a[i] < x) {
                i++;

                if (i == trackMidId) ins.add(new String("HLB " + (i - 1)));
                else if (i - 1 == trackMidId && i <= hi) ins.add(new String("HLO " + i));
                else if (i != trackMidId && i - 1 != trackMidId && i <= hi)
                    ins.add(new String("HLBHLO " + (i - 1) + " " + i));
            }
            while (a[j] > x) {
                j--;
                if (j == trackMidId) ins.add(new String("HLB " + (j + 1)));
                else if (j + 1 == trackMidId && j >= lo) ins.add(new String("HLO " + j));
                else if (j != trackMidId && j + 1 != trackMidId && j >= lo)
                    ins.add(new String("HLBHLO " + (j + 1) + " " + j));

            }
            if (i <= j) {
                if (i == trackMidId) trackMidId = j;
                else if (j == trackMidId) trackMidId = i;
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                ins.add(new String("SW " + i + " " + j));

                i++;
                j--;

                if (i == trackMidId) ins.add(new String("HLB " + (i - 1)));
                else if (i - 1 == trackMidId && i <= hi) ins.add(new String("HLO " + i));
                else if (i != trackMidId && i - 1 != trackMidId && i <= hi)
                    ins.add(new String("HLBHLO " + (i - 1) + " " + i));

                if (j == trackMidId) ins.add(new String("HLB " + (j + 1)));
                else if (j + 1 == trackMidId && j >= lo) ins.add(new String("HLO " + j));
                else if (j != trackMidId && j + 1 != trackMidId && j >= lo)
                    ins.add(new String("HLBHLO " + (j + 1) + " " + j));
            }
        }

        if (lo <= j && i <= hi) {
            // i <= hi
            inse = new String("HLG");
            for (int it = i; it <= hi; it++)
                inse = inse + " " + it;
            if (i - j == 2) inse = inse + " " + (i-1);
            ins.add(inse);
            ins.add(inse.replace("HLG", "UHL"));

            // lo <= j
            inse = new String("HLG");
            for (int it = lo; it <= j; it++)
                inse = inse + " " + it;
            ins.add(inse);
            ins.add(inse.replace("HLG", "UHL"));
        } else {
            inse = new String("HLG");
            for(int it = lo; it <= hi; it++)
                inse = inse + " " + it;
            ins.add(inse);
            ins.add(inse.replace("HLG", "UHL"));
        }


        ins.add(new String("UHL " + trackMidId));

        if (lo < j) quickSort(a, lo, j, ins);
        if (i < hi) quickSort(a, i, hi, ins);

        if (hi - lo + 1 == a.length) {
            inse = new String("HLG");
            for (int it = lo; it <= hi; it++)
                inse = inse + " " + it;
            ins.add(inse);
        }

        return ins;
    }

    public static ArrayList<String> countSort(int [] a, int k) {
        ArrayList<String> ins = new ArrayList<String>();

        int[] b = a.clone();
        int n = b.length;

        ArrayList<ArrayList<Integer>> clgt = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < findMax(b)/k + 1; i++)
            clgt.add(new ArrayList<Integer>());

        for(int i = 0; i < n; i++) {
            b[i] /= k;
            clgt.get(b[i]).add(i);
            ins.add(new String("HLO " + i));
            ins.add(new String("MVC " + i + " " + (b[i]-1)));
            ins.add(new String("UHL " + i));
        }

        int id = 0;
        for(int i = 0; i < clgt.size(); i++) {
            if (clgt.get(i).size() != 0) {
                for(int j = 0; j < clgt.get(i).size(); j++) {
                    ins.add(new String("MVB " + clgt.get(i).get(j) + " " + id++ + " " + (i-1)));
                    ins.add(new String("HLG " + clgt.get(i).get(j)));
                }
            }
        }

        id = 0;
        for(int i = 0; i < clgt.size(); i++) {
            if (clgt.get(i).size() != 0) {
                for(int j = 0; j < clgt.get(i).size(); j++) {
                    ins.add(new String("SWN " + clgt.get(i).get(j) + " " + id++));
                }
            }
        }

//        String inse = new String("HLG");
//        for(int i = 0; i < n; i++)
//            inse = inse + " " + i;
//        ins.add(inse);

        return ins;
    }

    private static int randInt(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }

    private static int findMax(int [] a) {
        int max = a[0];
        for(int value : a) {
            if (value > max) max = value;
        }
        return max;
    }
}
