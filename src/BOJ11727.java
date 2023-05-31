import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11727 {
    private static int[] tails;
    private static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        tails = new int[n + 1];
        if (n == 1) {
            System.out.print(1);
            return;
        }
        tails[1] = 1;
        tails[2] = 3;
        for (int i = 3; i <= n; i++) {
            tails[i] += tails[i - 1] + tails[i - 2] * 2;
            tails[i] %= 10_007;
        }

        System.out.print(tails[n]);
    }
}
