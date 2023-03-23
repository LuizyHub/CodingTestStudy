import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Beak1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> dudbo = new HashSet<>();
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < N + M; i++) {
            String s = br.readLine();
            if (dudbo.contains(s))
                ans.add(s);
            else
                dudbo.add(s);
        }
        ans.sort(String::compareTo);
        sb.append(ans.size()).append('\n');
        for (String an : ans) {
            sb.append(an).append('\n');
        }
        System.out.println(sb);
    }
}
