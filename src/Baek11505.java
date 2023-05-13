import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek11505 {
    private static final int MAX = Integer.MAX_VALUE;
    private static int N, M, startIdx = 1;
    private static int[] tree;
    private static int query(int start, int end) {
        int min = MAX;
        start = startIdx + start - 1;
        end = startIdx + end - 1;

        while (start <= end) {
            if ((start & 1) == 1) min = Math.min(min, tree[start]);
            if ((end & 1) == 0) min = Math.min(min, tree[end]);

            start = (start + 1) >> 1;
            end = (end - 1) >> 1;
        }

        return min;
    }
    private static void add(int idx, int num) {
        idx = startIdx + idx - 1;

        tree[idx] = num;
        idx >>= 1;
        while (idx > 0){
            tree[idx] = Math.min(tree[idx<<1] == 0 ? MAX : tree[idx<<1], tree[(idx<<1) + 1] == 0 ? MAX : tree[(idx<<1) + 1]);
            idx >>= 1;
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (startIdx < N)
            startIdx <<= 1;

        tree = new int[startIdx * 2];

        for (int i = 1; i <= N; i++) {
            add(i, Integer.parseInt(br.readLine()));
        }

//        System.out.println();
//        int tmp = 1;
//        for (int i = 0; i < tree.length; i++) {
//            if (tmp == i){
//                System.out.println();
//                tmp <<= 1;
//            }
//            System.out.print(tree[i] + " ");
//        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
        }


        System.out.print(sb);
    }
}
