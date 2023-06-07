import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13305 {
    private static int N;
    private static int[] distances, prices;
    private static long minSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        N--;
        distances = new int[N];
        prices = new int[N];

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            distances[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;

        while (idx < N) {
            int distance = 0;
            int i;
            for (i = idx; i < N; i++) {
                distance += distances[i];
                if (i == (N - 1) || prices[i + 1] < prices[idx]) break;
            }
            minSum += (long) distance * prices[idx];
            idx = i;
            idx++;
        }

        System.out.print(minSum);
    }
}