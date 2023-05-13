import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Beak1629 {
    private static BigInteger A;
    private static BigInteger C;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        A = new BigInteger(st.nextToken());
        BigInteger B = new BigInteger(st.nextToken());
        C = new BigInteger(st.nextToken());
        System.out.println(exp(B));
    }

    private static BigInteger exp(BigInteger b){
        if (b.equals(BigInteger.ONE)){
            return A.mod(C);
        }
        BigInteger ans;
        if (b.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)){
            BigInteger tmp = exp(b.divide(BigInteger.valueOf(2)));
            ans = tmp.mod(C).pow(2).mod(C);
        }
        else {
            BigInteger tmp = exp(b.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2)));
            ans = tmp.mod(C).pow(2).multiply(A.mod(C)).mod(C);
        }
        return ans;
    }

}
