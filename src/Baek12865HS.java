import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek12865HS {

    private static class Goods {
        int weight;
        int cost;

        public Goods(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Goods[] goods = new Goods[N];
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            goods[i] = new Goods(w, c);
        }

        for (int i = 1; i <= N; i++) {
            Goods now = goods[i - 1];
            for (int j = 0; j <= K; j++) {
                dp[i][j] = dp[i - 1][j];
                if (now.weight <= j) {
                    dp[i][j] = Math.max(dp[i][j], now.cost + dp[i - 1][j - now.weight]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }

}
