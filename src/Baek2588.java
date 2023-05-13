import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek2588 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        sb.append(N * (M%10)).append('\n');
        sb.append(N * ((M/10)%10)).append('\n');
        sb.append(N * ((M/100)%10)).append('\n');
        sb.append(N * M);

        System.out.print(sb);
    }
}
