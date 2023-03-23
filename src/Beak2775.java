import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak2775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            System.out.println(Solution(k,n));
        }
    }
    private static int Solution(int k, int n){
        if (k==0)
            return n;
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += Solution(k-1, i);
        }
        return ans;
    }
}
