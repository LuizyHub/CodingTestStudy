import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Beak11444_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger N = new BigInteger(br.readLine());
        if (N.equals(BigInteger.ONE)){
            System.out.println(N);
            System.exit(0);
        }
        int i1 = 1, i2 = 0;
        boolean isi1 = true;
        for (BigInteger i = BigInteger.ONE; !i.equals(N); i = i.add(BigInteger.ONE)) {
            isi1 = !isi1;
            if (isi1) {
                i1 += i2;
                i1 %= 1_000_000_007;
            }
            else {
                i2 += i1;
                i2 %= 1_000_000_007;
            }
        }
        if (isi1)
            System.out.println(i1);
        else
            System.out.println(i2);
    }
}
