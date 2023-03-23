import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[] pn = new boolean[n+1];
        loop:
        for (int i = 2 ; i <= n; i++) {
            if (pn[i])
                continue;
            for (int j = i; j <= n ; j+=i) {
                if (pn[j])
                    continue;
                if (k == 1) {
                    System.out.println(j);
                    break loop;
                }
                pn[j] = true;
                k--;
            }
        }
    }
}
