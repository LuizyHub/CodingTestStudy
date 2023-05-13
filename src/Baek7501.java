import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Baek7501 {
    private static long A, B;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        StringBuilder sb = new StringBuilder();
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        for (long i = A; i <= B; i++) {
            if (BigInteger.valueOf(i).isProbablePrime(10))
                sb.append(i).append(' ');
        }
        System.out.print(sb);
    }
}
