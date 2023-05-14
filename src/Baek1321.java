import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1321 {
    private static int N, startIdx = 1;
    private static int[] tree;

    private static void updateTree(int idx, int value) {
        idx += startIdx - 1;

        while (idx > 0) {
            tree[idx] += value;
            idx >>= 1;
        }
    }

    private static int query(int value) {
        int idx = 1;
        int sum = tree[1];

        while (idx < startIdx) {
            idx <<= 1;
            if (value <= sum - tree[idx + 1])
                sum -= tree[idx + 1];
            else
                idx++;
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

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++)
            updateTree(i, Integer.parseInt(st.nextToken()));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                updateTree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                sb.append(query(Integer.parseInt(st.nextToken()))).append('\n');
            }
        }

        System.out.print(sb);
    }
}