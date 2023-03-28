import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek11049 {
    private static class Matrix{
        int r, c;
        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static Matrix[] matrices;
    private static int N;
    private static int[][] dp;
    private static int getdp(int from, int to){
        if (from == to) return 0;
        if (dp[from][to] != 0) return dp[from][to];
        int min = Integer.MAX_VALUE;
        int multiply = nums[from] * nums[to];
        for (int i = from + 2; i < to - 1; i++) {
            min = Math.min(min, getdp(from,i) + getdp(i, to) + multiply * nums[i]);
        }


        return dp[from][to];
    }
    private static int[] nums;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        nums = new int[N + 1];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }
        nums[N] = Integer.parseInt(st.nextToken());
        for (int i = 2; i <= N; i++) {
            dp[i-2][i] = nums[i-2] * nums[i-1] * nums[i];
        }
        getdp(0, N);
        System.out.println(Arrays.toString(nums));

        System.out.println(sb);
    }
}
