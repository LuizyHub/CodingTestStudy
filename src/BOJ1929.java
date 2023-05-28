import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1929 {
    private static int M, N;
    private static final int MAX = 1_000_000;

    private static boolean[] primes = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        primes[1] = true;
        for (int i = 2; i*i <= MAX; i++) {
            if (primes[i]) continue;
            for (int j = 2; i*j <= MAX; j++) {
                primes[i*j] = true;
            }
        }

        for (int i = M; i <= N; i++) {
            if (!primes[i]) {
                sb.append(i).append('\n');
            }
        }

        System.out.print(sb);
    }
}