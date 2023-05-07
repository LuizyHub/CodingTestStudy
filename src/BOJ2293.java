import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2293 {
    private static int n, k;
    private static int[] coins, dp;
    private static int count(int total) {
        if (dp[total] != -1) return dp[total];
        int count = 0;
        for (int coin : coins) {
            if (total - coin >= 0)
                count += count(total - coin);
        }
        return dp[total] = count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        dp = new int[k + 1];


        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, -1);
        dp[0] = 1;

        System.out.print(count(k));

    }
}
