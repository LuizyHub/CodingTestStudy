

import java.util.*;
import java.util.stream.Collectors;

public class Programmers42746 {

    public static void main(String[] args) {
        Solution s = new Solution();
        for (int i = 0; i < 10; i++) {
            System.out.printf((int)(Math.random()*1000) + ", ");
        }
        System.out.println();
        int a,b;
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));
        a = (int)(Math.random()*1000);b = (int)(Math.random()*1000);System.out.println(a + ", " + b + ": " + s.solution(new int[]{a,b}));

        System.out.println(s.solution(new int[]{121 ,12}));
        System.out.println(s.solution(new int[]{12,121}));
        System.out.println(s.solution(new int[]{1}));
        System.out.println(s.solution(new int[]{6, 65, 681, 61}));
        System.out.println(s.solution(new int[]{3, 30, 34, 5, 9}));
        System.out.println(s.solution(new int[]{3,1000, 1000, 30, 0, 0, 34, 5, 9}));
        System.out.println(s.solution(new int[]{3}));
        System.out.println(s.solution(new int[]{0, 0, 0, 0, 0}));
        System.out.println(s.solution(new int[]{675, 282, 849, 195, 748, 617, 215, 661, 810, 36}));
    }


    static class Solution {
        public String solution(int[] numbers) {
            StringBuilder answer = new StringBuilder();
            String[] ans = new String[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                ans[i] = Integer.toString(numbers[i]);
            }
            Arrays.sort(ans, Collections.reverseOrder());
            if (ans[0].equals("0"))
                return "0";
            for (String s :
                    ans) {
                answer.append(s);
            }
            return answer.toString();
        }
    }
}
