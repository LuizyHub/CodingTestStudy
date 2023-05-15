import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1920 {
    private static int N;
    private static HashSet<Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        map = new HashSet<>();
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map.add(Integer.valueOf(st.nextToken()));
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (map.contains(Integer.valueOf(st.nextToken())))
                sb.append('1').append('\n');
            else
                sb.append('0').append('\n');
        }

        System.out.print(sb);
    }
}
