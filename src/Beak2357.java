import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak2357 {
    private static int[] treeMin, treeMax;
    private static int depth, startIdx, treeN;

    private static int log(int n){
        int ans = 0;
        while (n != 0){
            ans++;
            n >>= 1;
        }
        return ans;
    }

    private static void updateTreeMax(int idx, int num){
        idx = startIdx + idx - 1;
        treeMax[idx] = num;

        idx >>= 1;

        while (idx > 0){
            treeMax[idx] = Math.max(treeMax[idx<<1], treeMax[(idx<<1) + 1]);
            idx >>= 1;
        }
    }
    private static int queryMax(int start, int end){
        int max = 0;
        start = startIdx + start - 1;
        end = startIdx + end - 1;

        while (start <= end) {
            if((start & 1) == 1) max = Math.max(treeMax[start], max);
            if((end & 1) == 0) max = Math.max(treeMax[end], max);

            start = (start + 1) >> 1;
            end = (end - 1) >> 1;
        }
        return max;
    }
    private static void updateTreeMin(int idx, int num){
        idx = startIdx + idx - 1;
        treeMin[idx] = num;

        idx >>= 1;

        while (idx > 0){
            treeMin[idx] = Math.min(treeMin[idx<<1], treeMin[(idx<<1) + 1]);
            idx >>= 1;
        }
    }
    private static int queryMin(int start, int end){
        int min = Integer.MAX_VALUE;
        start = startIdx + start - 1;
        end = startIdx + end - 1;

        while (start <= end) {
            if((start & 1) == 1) min = Math.min(treeMin[start], min);
            if((end & 1) == 0) min = Math.min(treeMin[end], min);

            start = (start + 1) >> 1;
            end = (end - 1) >> 1;
        }
        return min;
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        depth = log(N);
        startIdx = (1<<depth);
        treeN = (1<<(depth+1));
        treeMax = new int[treeN + 1];
        treeMin = new int[treeN + 1];
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            updateTreeMax(i, num);
            updateTreeMin(i, num);
        }
        while (M-- > 0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(queryMin(from, end)).append(' ').append(queryMax(from, end)).append('\n');
        }

        System.out.println(sb);
    }
}
