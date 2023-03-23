import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak5615 {
    private static int count = 0;
    private static long primeTest[] = {2, 7, 61};
    // calculate x^y % m
    private static long powmod(long x, long y, long m) {
        x %= m;
        long r = 1L;
        while (y > 0) {
            if (y % 2 == 1)
                r = (r * x) % m;
            x = (x * x) % m;
            y /= 2;
        }
        return r;
    }
    // true for probable prime, false for composite
    public static boolean miller_rabin(long n, long a) {
        long d = n - 1;
        while (d % 2 == 0) {
            if (powmod(a, d, n) == n-1)
                return true;
            d /= 2;
        }
        long tmp = powmod(a, d, n);
        return tmp == n-1 || tmp == 1;
    }

    public static boolean isPrime(long n) {
        if (n <= 1)
            return false;
        if (n <= 10000L) {
            for (long i = 2; i*i <= n; i++)
                if (n % i == 0)
                    return false;
            return true;
        }
        for (long a : primeTest)
            if (!miller_rabin(n, a))
                return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0){
            if (isPrime(Long.parseLong(br.readLine()) * 2 + 1)){
                count++;
            }
        }
        System.out.print(count);
    }
}
