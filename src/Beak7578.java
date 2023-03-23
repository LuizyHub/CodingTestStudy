import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Beak7578 {
    private static long sum = 0;
    private static int N, startIdx;
    private static int[] cables, tree;

    private static void updateTree(int from, int to){
        from = from + startIdx - 1;
        to = to + startIdx - 1;
        while (from != to){
            if (from % 2 == 1) tree[from/2]--;
            else tree[from/2]++;

            if (to % 2 == 1) tree[to]++;
            else tree[to]--;

            to >>= 1;
            from >>= 1;

        }
    }
    private static int getCross(int from, int to){
        int ans = 0;
        from = from + startIdx - 1;
        to = to + startIdx - 1;
        while (from != to){
            if (from % 2 == 1) ans += tree[from/2];
            else ans += tree[from/2];

            if (to % 2 == 1) ans += tree[to];
            else ans += tree[to];

            to >>= 1;
            from >>= 1;

        }
        return Math.abs(ans);
    }

    private static int log(int num){
        int ans = 0;
         while (num > 0){
             ans++;
             num >>= 1;
         }
         return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cables = new int[N + 1];
        startIdx = (1<<log(N));
        tree = new int[startIdx<<1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> lines = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            lines.put(Integer.parseInt(st.nextToken()), i);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            updateTree(lines.get(num), i);
            cables[lines.get(num)] = i;
        }
        for (int i = 1; i <= N; i++) {
            sum += getCross(i, cables[i]);
        }

        System.out.println(sum/2);

        lines.clear();
        System.out.println(Arrays.toString(cables));
        System.out.println(Arrays.toString(tree));

    }
}
