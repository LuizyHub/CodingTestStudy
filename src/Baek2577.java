import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek2577 {
    private static int A, B, C;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());
        String s = Integer.toString(A*B*C);
        int[] count = new int[10];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - '0']++;
        }
        for (int i : count) {
            sb.append(i).append('\n');
        }
        System.out.print(sb);
    }
}
