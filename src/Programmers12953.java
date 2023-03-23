public class Programmers12953 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{2, 6, 8, 14}));
        System.out.println(s.solution(new int[]{99, 98, 97, 96, 95, 94, 93, 92, 91}));

    }
    static class Solution {
        public long solution(int[] arr) {
            long answer = arr[0];
            for (int i = 1; i < arr.length; i++) {
                answer = lcm(answer, arr[i]);
            }
            return answer;
        }
        static long lcm(long a, long b){
            return a / gcd(a,b) * b;
        }

        static long gcd(long a, long b){
            return b!=0 ? gcd(b, a % b) : a;
        }
    }
}
