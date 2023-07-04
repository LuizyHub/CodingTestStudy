import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {
    private static int N, max = Integer.MIN_VALUE;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (arr[i -1] > 0) {
                arr[i] += arr[i - 1];
            }
            max = Math.max(max, arr[i]);
        }

        System.out.print(max);
    }
}
