import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak11003 {
    private static int N, L, startIdx;
    private static int[] tree;
    private static int log(int num){
        int ans = 0;
        while (num > 0){
            ans++;
            num >>= 1;
        }
        return ans;
    }
    private static void updateTree(int idx, int num){
        idx = idx + startIdx - 1;
        tree[idx] = num;
        idx >>= 1;
        while (idx > 0){
            tree[idx] = Math.min(tree[idx<<1], tree[(idx<<1) + 1]);
            idx >>= 1;
        }
    }

    private static long query(int Ai){
        int ans = Integer.MAX_VALUE;
        int end = Ai + startIdx - 1;
        int start = Math.max(startIdx, end - L + 1);

        while (start <= end){
            if (start % 2 == 1) ans = Math.min(tree[start], ans);
            if (end % 2 == 0) ans = Math.min(tree[end], ans);

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        startIdx = 1<<log(N);
        tree = new int[startIdx<<1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            updateTree(i, Integer.parseInt(st.nextToken()));
            sb.append(query(i)).append(' ');
        }

        System.out.println(sb);
    }
}
