import java.io.IOException;

public class Beak10872 {
    private static final int[] fac = {1,1,2,6,24,120,720,5040,40320,362880,3628800,39916800,479001600};
    public static void main(String[] args) throws IOException {
        int c, n = System.in.read() - 48;
        while ((c = System.in.read()) > 32) n = 10 * n + c - 48;
        System.out.print(fac[n]);
    }
}
