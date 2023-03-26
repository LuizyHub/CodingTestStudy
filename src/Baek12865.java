import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek12865 {
    private static class Goods{
        int W, V;

        public Goods(int w, int v) {
            W = w;
            V = v;
        }
    }
    private static int N, K;
    private static int[][] dp;
    private static Goods[] goods;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        goods = new Goods[N + 1];
        dp = new int[N + 1][K + 1]; // i번째까지 j용량까지 담을때 가질 수 있는 최대 가치

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            goods[i] = new Goods(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= N; i++) {
            Goods g = goods[i];
            for (int j = 0; j <= K; j++) {
                dp[i][j] = dp[i - 1][j];
                if (g.W <= j){
                    dp[i][j] = Math.max(dp[i - 1][j - g.W] + g.V, dp[i][j]);
                }
            }
        }

//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }

        System.out.println(dp[N][K]);
    }
}
