import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int count = 0;
            for (int i = n; i >= 0; i -= 3) {
                count += i / 2;
                count++;
            }
            sb.append(count).append('\n');
        }

        System.out.print(sb);
    }
}
