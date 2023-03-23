import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak2042_1 {
    private static int N, M, K, startIdx;
    private static long[] tree;
    private static int log(int num){
        int ans = 0;
        while (num > 0){
            ans++;
            num >>= 1;
        }
        return ans;
    }
    private static long query(int start, int end){
        long sum = 0;
        start = start + startIdx - 1;
        end = end + startIdx - 1;
        while (start <= end){
            if (start % 2 == 1) sum += tree[start];
            if (end % 2 == 0) sum += tree[end];

            start = (start + 1) / 2;
            end = (end - 1) / 2;

        }
        return sum;
    }
    private static void updateTree(int idx, long num){
        idx = startIdx + idx - 1;
        num -= tree[idx];
        while (idx > 0){
            tree[idx] += num;
            idx >>= 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int depth = log(N);
        startIdx = 1<<depth;
        tree = new long[(startIdx<<1)+1];

        for (int i = 1; i <= N; i++) {
            updateTree(i,Long.parseLong(br.readLine()));
        }


        for (int i = 0; i < K + M; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1){
                updateTree(Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
            }
            else {
                sb.append(query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
            }
        }

        System.out.println(sb);
    }
}
