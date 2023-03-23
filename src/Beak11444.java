import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Beak11444 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger N = new BigInteger(br.readLine());
        if (N.equals(BigInteger.ONE)){
            System.out.println(N);
            System.exit(0);
        }
        BigInteger mod = new BigInteger("1000000007");
        BigInteger i1 = BigInteger.ONE;
        BigInteger i2 = BigInteger.ZERO;

        //int i1 = 1, i2 = 0;
        boolean isi1 = true;
        for (BigInteger i = BigInteger.ONE; !i.equals(N); i = i.add(BigInteger.ONE)) {
            isi1 = !isi1;
            if (isi1) {
                i1 = i1.add(i2).mod(mod);
            }
            else {
                i2 = i2.add(i1).mod(mod);
            }
        }
        if (isi1)
            System.out.println(i1);
        else
            System.out.println(i2);
    }
}
