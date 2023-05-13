import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek10090 {
    private static int N = 1000000, M, startIdx = 1;
    private static int[] tree;
    private static long sum = 0L;
    private static void add(int idx) {
        query(idx, N);
        idx += startIdx - 1;
        while (idx > 0) {
            tree[idx] += 1;
            idx >>= 1;
        }
    }
    private static void query(int from, int to) {
        from += startIdx - 1;
        to += startIdx - 1;

        while (from <= to) {
            if ((from & 1) == 1) sum += tree[from];
            if ((to & 1) == 0) sum += tree[to];

            from = (from + 1) >> 1;
            to = (to - 1) >> 1;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());

        while (startIdx < N)
            startIdx <<= 1;

        tree = new int[startIdx<<1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            add(Integer.parseInt(st.nextToken()));
        }

        System.out.print(sum);
    }
}
