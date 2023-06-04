import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1904 {
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int x, y, z;
        x = 1;
        y = 1;
        z = 1;
        for (int i = 0; i < N - 1; i++) {
            z = x + y;
            z %= 15746;
            x = y;
            y = z;
        }
        System.out.print(z);
    }
}