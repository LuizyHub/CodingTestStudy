import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek18436 {
    private static int N, startIdx = 1;
    private static int[] tree;
    private static void uptade(int idx, int value) {
        idx += startIdx - 1;
        value &= 1;

        value -= tree[idx];

        while (idx > 0) {
            tree[idx] += value;

            idx >>= 1;
        }
    }
    private static int query (int from, int to) {
        int sum = 0;
        from += startIdx - 1;
        to += startIdx - 1;

        while (from <= to) {
            if ((from & 1) == 1) sum += tree[from];
            if ((to & 1) == 0) sum += tree[to];

            from = (from + 1) >> 1;
            to = (to - 1) >> 1;
        }

        return sum;
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while (startIdx < N)
            startIdx <<= 1;

        tree = new int[startIdx << 1];

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++)
            uptade(i, Integer.parseInt(st.nextToken()));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            switch (c) {
                case '1' :
                    uptade(from, to);
                    break;
                case '2' :
                    sb.append(to - from + 1 - query(from, to)).append('\n');
                    break;
                case '3' :
                    sb.append(query(from, to)).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }
}