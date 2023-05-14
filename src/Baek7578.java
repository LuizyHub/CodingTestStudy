import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek7578 {
    private static final int MAXINPUT = 1_000_000;
    private static int N, startIdx = 1;
    private static long sum = 0;
    private static int[] tree, fac;

    private static void update(int idx) {
        idx += startIdx - 1;

        while (idx > 0) {
            tree[idx]++;
            idx >>= 1;
        }
    }

    private static int query(int from, int to) {
        if (from > to) {
            int tmp = from;
            from = to;
            to = tmp;
        }

        from += startIdx - 1;
        to += startIdx - 1;
        int sum = 0;

        while (from <= to) {
            if ((from & 1) == 1) sum += tree[from];
            if ((to & 1) == 0) sum += tree[to];

            from = (from + 1)>>1;
            to = (to - 1)>>1;
        }

        return sum;
    }

    private static void printTree() {
        int idx = 1;
        for (int i = 0; i < tree.length; i++) {
            if (idx == i) {
                System.out.println();
                idx <<= 1;
                for (int j = 0; j < (tree.length - idx) / 4; j++) {
                    System.out.print("  ");
                }
            }
            System.out.print(tree[i] + " ");

        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while (startIdx < N)
            startIdx <<= 1;

        tree = new int[startIdx << 1];

        fac = new int[MAXINPUT + 1];
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            fac[Integer.parseInt(st.nextToken())] = i;
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int idx = fac[Integer.parseInt(st.nextToken())];
//            System.out.println("idx = " + idx);
            sum += query(idx, N);
//            System.out.println("i = " + i + " : " + sum);
//            printTree();
            update(idx);
        }

        System.out.print(sum);
    }
}