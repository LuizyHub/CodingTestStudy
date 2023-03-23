import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Beak9466 {
    private static int N;
    private static boolean[] visited;
    private static int[] list;


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- != 0){
            N = Integer.parseInt(br.readLine());
            int count = N;
            visited = new boolean[N];
            list = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken())-1;
                list[i] = num;
                if (num == i){
                    visited[i] = true;
                    count--;
                }
            }

            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                HashMap<Integer,Integer> check = new HashMap<>();
                int idx = 1;
                check.put(i,idx++);
                int start = list[i];
                while (true){
                    if (check.containsKey(start)){ //cycle start
                        count -= (idx - check.get(start));
                        break;
                    }
                    check.put(start,idx++);

                    if (visited[start]){
                        break;
                    }

                    visited[start] = true;

                    start = list[start];

                }
            }

            sb.append(count).append('\n');



        }

        System.out.println(sb);
    }
}
