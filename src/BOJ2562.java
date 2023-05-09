import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 0;
        int max = 0;
        for (int i = 1; i <= 9; i++) {
            int value = Integer.parseInt(br.readLine());
            if (value > max) {
                idx = i;
                max = value;
            }
        }

        System.out.print(new StringBuilder().append(max).append('\n').append(idx));
    }
}
