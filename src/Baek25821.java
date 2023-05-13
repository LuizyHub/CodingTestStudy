import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Baek25821 {
    private static long N, M, ans = 0;

    private static void palindromic(char[] chars, int idx, int len){
        if (idx == (len + 1) / 2){
            long num = Long.parseLong(new String(chars));
            if (num < N || num > M) return;
            if (BigInteger.valueOf(num).isProbablePrime(10))
                ans++;
            return;
        }
        for (int i = 0; i < 10; i++) {
            chars[idx] = chars[len - idx - 1] = (char) (i + '0');
            palindromic(chars, idx + 1, len);
        }
    }


    public static void main(String[] args) throws IOException {
        String[] s = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");
        N = Long.parseLong(s[0]);
        M = Long.parseLong(s[1]);

        for (int i = s[0].length(); i <= s[1].length(); i++) {
            char[] chars = new char[i];
            palindromic(chars, 0, i);
        }

        System.out.print(ans);
    }
}
