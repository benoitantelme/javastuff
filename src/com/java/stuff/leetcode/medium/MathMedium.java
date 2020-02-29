package com.java.stuff.leetcode.medium;

public class MathMedium {


    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n == 1)
            return x;

        // abs(MAX VALUE) = abs(MIN VALUE - 1) so -n does not work because of 32 bits limit
        if(n==Integer.MIN_VALUE)
        {
            x=x*x;
            n/=2;
        }

        if(n <0){
            n = -n;
            x = 1/x;
        }

        if(n % 2 == 0)
            return myPow(x*x, n/2);
        else
            return myPow(x*x, n/2) * x;
    }

    private String[] getCoord(String s){
        String[] coord = s.split("\\+");
        coord[1] = coord[1].substring(0, coord[1].length()-1);
        return coord;
    }
    public String complexNumberMultiply(String a, String b) {
        String[] coorda = getCoord(a);
        String[] coordb = getCoord(b);

        //  (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i
        int im = Integer.valueOf(coorda[0]) * Integer.valueOf(coordb[1]) + Integer.valueOf(coorda[1]) * Integer.valueOf(coordb[0]);
        int re = Integer.valueOf(coorda[0]) * Integer.valueOf(coordb[0]) - Integer.valueOf(coorda[1]) * Integer.valueOf(coordb[1]);

        String res = re + "+" + im + "i";

        return res;
    }


    public boolean stoneGame(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }

        int[][] dp = new int[nums.length][nums.length];

        // dp initialization
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = nums[i];
        }

        // |5 0 0 0|
        // |0 3 0 0|
        // |0 0 4 0|
        // |0 0 0 5|

        // filling dp table
        for (int len = 2; len <= nums.length; len++) {
            for (int i = 0; i < nums.length; i++) {
                int j = i + len - 1;
                if (j >= nums.length) {
                    continue;
                }
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        // top 2 = max(5-3, 3-5) 0,1
        // middle 1 = max(3-4, 4-3) 1,2
        // bot 1 = max(4-5, 5-4) 2,3
        // top 4 = max(5-1, 4-2) 0,2
        // bot 4 = max(3-1, 5-1) 1,3
        // last 1 = max(5-4, 5-4) 0,3
        // |5 2 4 1|
        // |0 3 1 4|
        // |0 0 4 1|
        // |0 0 0 5|
        return dp[0][nums.length - 1] > 0;
    }


    public static void main(String[] args){
        MathMedium mm = new MathMedium();
        System.out.println(mm.myPow(2.00000, 10));
        System.out.println(mm.myPow(2.10000, 3));
        System.out.println(mm.myPow(2.00000, -2));
        System.out.println(mm.myPow(0.00001, 2147483647));
        System.out.println(mm.myPow(2.00000,-2147483648));

        System.out.println(mm.complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(mm.complexNumberMultiply("1+-1i", "1+-1i"));

        System.out.println(mm.stoneGame(new int[]{5,3,4,5}));

    }

}




