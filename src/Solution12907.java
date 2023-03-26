public class Solution12907 {
    public int solution(int n, int[] money) {

        int[][] dp = new int[money.length + 1][n + 1];

        for (int i = 0; i <= money.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= money.length ; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= money[i - 1]){
                    dp[i][j] += dp[i][j - money[i - 1]];
                    dp[i][j] %= 1_000_000_007;
                }
            }
        }

        return dp[money.length][n];
    }

    public static void main(String[] args) {
        Solution12907 s = new Solution12907();
        System.out.println(s.solution(5, new int[]{1,2,5}));
    }


}
