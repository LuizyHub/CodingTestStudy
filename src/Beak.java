import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak {

    private static int N;
    private static int K;
    private static int count = 0;
    private final static int MAX = 100_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bfs();
    }

    private static void bfs(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addLast(N); // startPoint
        int[] visited = new int[MAX+1];

        while (!q.isEmpty()){
            int cur = q.pollFirst();

            if (cur == K){
                count++;
                while (!q.isEmpty()) {
                    if (K == q.pollFirst())
                        count++;
                }
                System.out.println(visited[cur] + "\n" + count);
                return;
            }
            for (int next : new int[]{cur - 1,cur +1, cur * 2}) { //O(3^N)
                if (0 <= next && next <= MAX && visited[next] == 0){ // O(1)
                    visited[next] = visited[cur] + 1;
                    q.addLast(next);
                }
            }
        }
    }
}
