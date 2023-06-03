import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2193 {
    private static int N;
    private static long[] endZero, endOne;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        endZero = new long[N + 1];
        endOne = new long[N + 1];

        endOne[1] = 1L;

        for (int i = 2; i <= N; i++) {
            endOne[i] = endZero[i - 1];
            endZero[i] = endZero[i - 1] + endOne[i - 1];
        }

        System.out.print(endOne[N] + endZero[N]);
    }
}
