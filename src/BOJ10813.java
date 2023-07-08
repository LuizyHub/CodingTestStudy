import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10813 {
    private static int[] arr;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int tmp = arr[from];
            arr[from] = arr[to];
            arr[to] = tmp;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            sb.append(arr[i]).append(' ');
        }

        System.out.print(sb);


    }
}
