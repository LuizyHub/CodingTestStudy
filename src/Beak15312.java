import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak15312 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] chars = new int[]{3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
        String A = br.readLine();
        String B = br.readLine();
        int N = A.length();
        int[] ans = new int[N*2];
        for (int i = 0; i < N; i++) {
            ans[i*2] = chars[A.charAt(i) - 'A'];
            ans[i*2 +1] = chars[B.charAt(i) - 'A'];
        }
        for (int i = 0; i < N*2 - 2; i++) {
            for (int j = 0; j < N*2 - i - 1; j++) {
                ans[j] = (ans[j] +ans[j+1])%10;
            }
        }
        System.out.println(Integer.toString(ans[0]) + Integer.toString(ans[1]));
    }
}
