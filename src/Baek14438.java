import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek14438 {
    private static int N, startIdx = 1;
    private static final int MAX = 0x7fffffff;
    private static int[] tree;
    private static void add(int idx, int value) {
        idx = idx + startIdx - 1;

        tree[idx] = value;
        idx >>= 1;

        while (idx > 0) {
            tree[idx] = Math.min(tree[idx<<1], tree[(idx<<1) + 1]);
            idx >>= 1;
        }
    }

    private static int query(int from, int to) {
        from = from + startIdx - 1;
        to = to + startIdx - 1;

        int min = MAX;

        while (from <= to) {
            if ((from & 1) == 1) min = Math.min(tree[from], min);
            if ((to & 1) == 0) min = Math.min(tree[to], min);

            from = (from + 1) >> 1;
            to = (to - 1) >> 1;
        }

        return min;
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        while (startIdx < N)
            startIdx <<= 1;

        tree = new int[startIdx<<1];

        Arrays.fill(tree, MAX);

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            add(i, Integer.parseInt(st.nextToken()));
        }

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            else {
                sb.append(query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
            }
        }

        System.out.print(sb);
    }
}
