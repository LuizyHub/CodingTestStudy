import java.util.ArrayList;
import java.util.Arrays;

public class Programmers12936 {
    public static void main(String[] args) {
        for (int i = 1; i < 25; i++) {
            System.out.println(Arrays.toString(new Solution().solution(4,i)));
        }



    }
    static class Solution {
        public int[] solution(int n, long k) {
            int[] answer = new int[n];
            ArrayList<Integer> num = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) {
                num.add(i);
            }
            long[] fac = new long[n+1];
            fac[1] = 1l;
            for (int i = 2; i <= n; i++) {
                fac[i] = i * fac[i-1];
            }
            for (int i = n; i > 1; i--) {
                int idx = (int)((k-1)/fac[i-1]);
                int tmp = num.remove(idx);
                answer[n-i] = tmp;
                k -= fac[i-1]*idx;
            }
            answer[n-1] = num.get(0);
            return answer;
        }
    }
}
