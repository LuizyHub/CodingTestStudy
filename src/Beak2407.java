import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Beak2407 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        if (m*2 > n)
            m = n-m;
        BigInteger up = BigInteger.ONE;
        BigInteger down = BigInteger.ONE;
        while (m>0) {
            up = up.multiply(new BigInteger(Integer.toString(n--)));
            down = down.multiply(new BigInteger(Integer.toString(m--)));
        }
        System.out.println(up.divide(down));
    }
}
