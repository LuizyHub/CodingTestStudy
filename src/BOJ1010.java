import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1010 {
    private static int N, M, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            sb.append(combination(M, N)).append('\n');
        }

        System.out.print(sb);
    }

    private static long combination(int m, int n) {
        long ans = 1;
        n = Math.min(n, m-n);

        for (int i = 0; i < n; i++) {
           ans *= (m - i);
        }

        for (int i = 2; i <= n; i++) {
            ans /= i;
        }

        return ans;
    }
}
