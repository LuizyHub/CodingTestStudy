import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15655 {
    private static int N, M;
    private static int[] arr, ans;
    private static StringBuilder sb;

    private static void dfs(int idx, int cur) {
        if (idx == M) {
            for (int i : ans) {
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = cur; i < N; i++) {
            ans[idx] = arr[i];
            dfs(idx + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        ans = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0, 0);

        System.out.print(sb);



    }
}
