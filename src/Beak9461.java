import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Beak9461 {
    public static void main(String[] args) throws IOException {
        ArrayList<Long> P = new ArrayList<>(101);
        for (long i : new long[]{0,1,1,1,2,2,3,4,5,7,9}) {
            P.add(i);
        }
        for (int i = 11; i <= 100; i++) {
            P.add(P.get(i-1)+P.get(i-5));
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(P.get(N));
        }
    }
}
