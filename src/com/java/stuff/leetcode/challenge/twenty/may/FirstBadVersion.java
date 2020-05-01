package com.java.stuff.leetcode.challenge.twenty.may;

public class FirstBadVersion {
    int bad;

    public FirstBadVersion(int bad) {
        this.bad = bad;
    }

    private boolean isBadVersion(int n) {
        return n >= bad;
    }

    public int firstBadVersion(int n) {
        return rec(1, n);
    }

    private int rec(int l, int r) {
        if(l >= r)
            return l;

        int mid = l + (r - l) / 2;
        if(isBadVersion(mid))
            return rec(l, mid);
        else
            return rec(mid+1, r);
    }

    public static void main(String[] args){
        FirstBadVersion fbv = new FirstBadVersion(4);
        System.out.println(fbv.firstBadVersion(5));

        fbv = new FirstBadVersion(1);
        System.out.println(fbv.firstBadVersion(1));

        fbv = new FirstBadVersion(1);
        System.out.println(fbv.firstBadVersion(2));

        fbv = new FirstBadVersion(2);
        System.out.println(fbv.firstBadVersion(2));
    }

}
