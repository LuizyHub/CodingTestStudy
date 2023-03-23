import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak15623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N < 2){
            System.out.println(N);
            System.exit(0);
        }
        int i1 = 1, i2 = 0;
        boolean isi1 = true;
        for (int i = 1; i < N; i++) {
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
