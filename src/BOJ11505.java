import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11505 {
    private static final int MOD = 1_000_000_007;
    private static int N, M, startIdx = 1;

    private static long[] tree;

    private static void updateTree(int idx, long value) {
        idx += startIdx - 1;

        tree[idx] = value;
        idx >>= 1;

        while (idx > 0) {
            tree[idx] = tree[idx << 1] * tree[(idx << 1) + 1] % MOD;
            idx >>= 1;
        }
    }

    private static int query(int from, int to) {
        from += startIdx - 1;
        to += startIdx - 1;
        long sum = 1;

        while (from <= to) {
            if ((from & 1) == 1) {
                sum *= tree[from];
                sum %= MOD;
            }
            if ((to & 1) == 0) {
                sum *= tree[to];
                sum %= MOD;
            }

            from = (from + 1) >> 1;
            to = (to - 1) >> 1;
        }

        return (int) sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        M += Integer.parseInt(st.nextToken());

        while (startIdx < N) {
            startIdx <<= 1;
        }

        tree = new long[startIdx << 1];

        Arrays.fill(tree, 1);

        for (int i = 1; i <= N; i++) {
            updateTree(i, Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")) {
                updateTree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                sb.append(query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
            }
        }

        System.out.print(sb);

    }
}
