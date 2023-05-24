import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ10816 {
    private static int N, M;

    private static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        map = new HashMap<>();

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            Integer integer = map.get(num);
            if (integer != null) {
                map.put(num, integer + 1);
            }
            else {
                map.put(num, 1);
            }
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            Integer integer = map.get(Integer.valueOf(st.nextToken()));
            if (integer != null) {
                sb.append(integer).append(' ');
            }
            else {
                sb.append("0 ");
            }
        }

        System.out.print(sb);

    }
}
