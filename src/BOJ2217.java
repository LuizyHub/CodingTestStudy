import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2217 {
    private static int N;
    private static int[] ropes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ropes = new int[N];

        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes);

        int max = 0;


        for (int i = 0; i < N; i++) {
            int tmp = ropes[i] * (N - i);

            if (tmp > max)
                max = tmp;
        }

        System.out.print(max);
    }
}
