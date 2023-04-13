import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1341 {
    private static long a, b;
    private static char[] ans = {'-', '1'};
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        for (int i = 1; i <= 60; i++) {
            long len = (1L<<i) - 1;
            if (len % b != 0 || len / b == 0) continue;
            ans = new char[i];
            a *= (len / b);
            String binaryA = Long.toBinaryString(a);
            int dif = i - binaryA.length();
            for (int j = 0; j < i; j++) {
                if (j < dif || binaryA.charAt(j-dif) == '0')
                    ans[j] = '-';
                else
                    ans[j] = '*';
            }
            break;
        }

        System.out.print(ans);
    }
}