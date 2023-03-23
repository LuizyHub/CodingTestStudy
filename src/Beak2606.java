import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        boolean[] virus = new boolean[N];
        virus[0] = true;
        boolean[][] Edges = new boolean[N][N];

        int E = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            Edges[x][y] = true;
            Edges[y][x] = true;
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(0);
        while (!deque.isEmpty()){
            int cur = deque.pollFirst();
            for (int i = 0; i < N; i++) {
                if (Edges[cur][i] && !virus[i]){
                    virus[i] = true;
                    count++;
                    deque.addLast(i);
                }
            }
        }
        System.out.println(count);
    }
}
