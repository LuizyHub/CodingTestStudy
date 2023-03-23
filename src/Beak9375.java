import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Beak9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            int M = Integer.parseInt(br.readLine());
            for (int j = 0; j < M; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                String s = st.nextToken();
                if (!hashMap.containsKey(s))
                    hashMap.put(s,2);
                else{
                    int n = hashMap.get(s);
                    hashMap.replace(s,++n);
                }
            }
            int ans = 1;
            for (String s : hashMap.keySet()) {
                ans *= hashMap.get(s);
            }
            System.out.println(ans-1);
        }
    }
}
