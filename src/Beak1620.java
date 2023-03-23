import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Beak1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String,Integer> hm = new HashMap<>();
        ArrayList<String> al = new ArrayList<>(N+1);
        al.add("");
        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            hm.put(s,i);
            al.add(s);
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (s.charAt(0) < 'A')
                sb.append(al.get(Integer.parseInt(s))).append('\n');
            else
                sb.append(hm.get(s)).append('\n');
        }
        System.out.println(sb);
    }
}
