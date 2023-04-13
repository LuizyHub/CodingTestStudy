import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2098 {
    private static int N;
    private static final int INF = 17 * 1_000_000 + 1;
    private static int[][] matrix, dp;

    private static int getDp(int bit, int idx) {
        if (dp[idx][bit] != 0)
            return dp[idx][bit];
        int ans = INF;
        if (bit == ((1 << N) - 1)) {
            ans = matrix[idx][0];
        }
        else {
            for (int i = 0; i < N; i++) {
                if ((bit & (1<<i)) != 0 || matrix[idx][i] == INF) continue;
                ans = Math.min(getDp((bit | (1<<i)), i) + matrix[idx][i], ans);
            }
        }

        return dp[idx][bit] = ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        dp = new int[N][1 << N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num == 0 ? INF : num;
            }
        }

        System.out.print(getDp(1,0));
    }
}