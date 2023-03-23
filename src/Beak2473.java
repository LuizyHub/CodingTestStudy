import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak2473 {
    private static int N;
    private static long minSum = Long.MAX_VALUE;
    private static long[] liq;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        liq = new long[N];
        long[] ans = new long[3];
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            liq[i] = num;
        }
        Arrays.sort(liq);
        loop:
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long sum = liq[i] + liq[j];
                int idx = Arrays.binarySearch(liq, -sum);
                if ((idx >= 0 && idx <= j) || (idx < 0 && (-idx - 1) <= j)){
                    sum += liq[j+1];
                    sum = Math.abs(sum);
                    if (sum < minSum){
                        minSum = sum;
                        ans[0] = liq[i];
                        ans[1] = liq[j];
                        ans[2] = liq[j+1];
                    }
                    continue;
                }
                if (idx >= 0){
                    minSum = 0;
                    sb.append(liq[i]).append(' ').append(liq[j]).append(' ').append(liq[idx]);
                    break loop;
                }
                long needLiq;
                if (-idx -1 == j+1){
                    needLiq = liq[j + 1];
                }
                else {
                    if (Math.abs(sum + liq[Math.min(-idx-1,N - 1)]) < Math.abs(sum + liq[Math.max(-idx-2,j + 1)]))
                        needLiq = liq[Math.min(-idx-1,N - 1)];
                    else
                        needLiq = liq[Math.max(-idx-2,j + 1)];
                }
                sum = Math.abs(sum + needLiq);
                if (sum < minSum){
                    minSum = sum;
                    ans[0] = liq[i];
                    ans[1] = liq[j];
                    ans[2] = needLiq;
                }
            }
        }

        if (minSum != 0)
            sb.append(ans[0]).append(' ').append(ans[1]).append(' ').append(ans[2]);

        System.out.println(sb);
    }
}