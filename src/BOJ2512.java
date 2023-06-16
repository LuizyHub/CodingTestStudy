import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2512 {
    private static int N, M, sum;
    private static int[] budgets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        budgets = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            sum += (budgets[i] = Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(budgets);

        for (int i = N - 1; i >= 0; i--) {
            int limit = budgets[i] * (N - i - 1) + sum;
            if (limit <= M) {
                System.out.println(budgets[i] + (M - limit));
            }
        }
    }
}
