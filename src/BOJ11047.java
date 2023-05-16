import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047 {
    private static int N, K, count = 0;

    private static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int idx = N - 1;

        while (K > 0) {
            while (coins[idx] > K) {
                idx--;
            }

            int coin = coins[idx];

            int tmp = K / coin;

            K -= coin * tmp;
            count += tmp;

        }

        System.out.print(count);

    }
}
