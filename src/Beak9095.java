import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak9095 {
    public static void main(String[] args) throws IOException {
        int[] P = new int[12];
        P[0] = 0;
        P[1] = 1;
        P[2] = 2;
        P[3] = 4;
        for (int i = 4; i < 12; i++) {
            P[i] = P[i-1] + P[i-2] + P[i-3];
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            System.out.println(P[Integer.parseInt(br.readLine())]);
        }
    }
}
