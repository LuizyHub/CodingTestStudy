import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek12837 {
    private static int N, M, startIdx = 1;
    private static long[] tree;
    private static void add(int idx, long value) {
        idx += startIdx - 1;
        while (idx > 0) {
            tree[idx] += value;
            idx >>= 1;
        }
    }
    private static long query(int from, int to) {
        long sum = 0L;
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
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (startIdx < N)
            startIdx <<= 1;

        tree = new long[startIdx<<1];

        for (int i = 0; i < M; i++) {
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
