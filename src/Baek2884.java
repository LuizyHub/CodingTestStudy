import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2884 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int sum = N * 60 + M - 45;

        if (sum < 0)
            sum += 24 * 60;

        sb.append(sum/60).append(' ').append(sum%60);

        System.out.print(sb);
    }
}
