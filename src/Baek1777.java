import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1777 {
    private static int N, startIdx = 1;
    private static int[] tree, input, output;
    private static void updateTree(int idx) {
        idx += startIdx - 1;

        while (idx > 0) {
            tree[idx]++;
            idx >>= 1;
        }
    }
    private static void minusTree(int idx) {
        idx += startIdx - 1;

        while (idx > 0) {
            tree[idx]--;
            idx >>= 1;
        }
    }
    private static int query(int value) {
        int idx = 1;
        int sum = tree[1];
        while (idx < startIdx) {
            idx <<= 1;
            if (value < sum - tree[idx])
                sum -= tree[idx++];
        }

        return idx -= startIdx - 1;
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while (startIdx < N)
            startIdx <<= 1;

        tree = new int[startIdx << 1];
        input = new int[N + 1];
        output = new int[N + 1];

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            updateTree(i);
        }


        for (int i = N; i > 0; i--) {
            int idx = query(input[i]);
            output[idx] = i;
            minusTree(idx);
        }

        for (int i = 1; i <= N; i++)
            sb.append(output[i]).append(' ');

        System.out.print(sb);
    }
}
