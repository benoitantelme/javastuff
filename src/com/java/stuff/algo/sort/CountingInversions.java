package com.java.stuff.algo.sort;

public class CountingInversions {

    public static void main(String[] args) {
        CountingInversions ci = new CountingInversions();

        if(ci.countInversions(new int[]{1, 1, 1, 2, 2}) != 0)
            System.out.println("wrong");

        if(ci.countInversions(new int[]{2, 1, 3, 1, 2}) != 4)
            System.out.println("wrong");

    }

    public int countInversions(int[] array){
        return sort(array, 0, array.length-1);
    }

    public int sort(int[] array, int l, int r){
        int res = 0;

        if(l < r){
            int m = (l+r)/2;
            res += sort(array, l, m);
            res += sort(array, m+1, r);
            res += merge(array, l, m, r);
        }

        return res;
    }

    public int merge(int[] array, int l, int m, int r){
        int res = 0;
        int s1 = m - l + 1;
        int s2 = r - m;

        int i;
        int[] tmpL = new int[s1];
        for(i = 0; i < s1; i++)
            tmpL[i] = array[l+i];

        int j;
        int[] tmpR = new int[s2];
        for(j = 0; j < s2; j++)
            tmpR[j] = array[m+j+1];

        i = 0;
        j = 0;
        int k =l;
        while (i < s1 && j < s2){
            if(tmpL[i] <= tmpR[j])
                array[k++] = tmpL[i++];
            else {
                array[k++] = tmpR[j++];
                res += s1 - i;
            }
        }

        while(i < s1)
            array[k++] = tmpL[i++];


        while(j < s2)
            array[k++] = tmpR[j++];

        return res;
    }


}
