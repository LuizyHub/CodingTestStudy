import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1016 {
    private static long min, max;
    private static int ans;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        int dif = ans = (int) (max - min);
        visit = new boolean[dif + 1];
        for (long i = 2; i*i <= max; i++) {
            long d = i * i;
            for (long j = (min / d) * d; j <= max; j += d) {
                if (j < min) continue;
                int idx = (int) (j - min);
                if (!visit[idx]){
                    ans--;
                    visit[idx] = true;
                }
//                System.out.println(j);
            }
        }
        System.out.println(ans + 1);
    }
}
