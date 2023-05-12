import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9012 {
    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int count = 0;
            for (char c : br.readLine().toCharArray()) {
                if (count < 0) break;

                if (c == '(')
                    count++;
                else
                    count--;
            }

            if (count == 0)
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }

        System.out.print(sb);
    }
}