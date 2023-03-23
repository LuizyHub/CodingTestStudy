import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak2243 {
    private static int N, startIdx = 1<<20;
    private static int[] tree = new int[1<<21];
    private static int getCandy(int order){
        int idx = 1;
        int sum = tree[idx];
        while (idx < startIdx){
            if (order > sum - tree[idx*2 + 1]){
                idx = idx*2 + 1;
            }
            else {
                sum -= tree[idx*2 + 1];
                idx = idx*2;
            }
        }
        idx = idx - startIdx + 1;
        updateTree(idx, -1);
        return idx;
    }
    private static void updateTree(int idx, int num){
        idx = startIdx + idx - 1;
        while (idx > 0){
            tree[idx] += num;
            idx >>= 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")){
                sb.append(getCandy(Integer.parseInt(st.nextToken()))).append('\n');
            }
            else {
                updateTree(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            }
        }

        System.out.println(sb);
    }
}
