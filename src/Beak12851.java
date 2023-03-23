import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak12851 {
    private static int N;
    private static int K;
    private static int min = Integer.MAX_VALUE;
    private static int count = 0;

    private final static int MAX = 100_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bfs();
        System.out.println(min);
        System.out.println(count);
    }

    private static void bfs(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addLast(N); // startPoint
        int[] visited = new int[MAX+1];

        while (!q.isEmpty()){
            int cur = q.pollFirst();
            if (visited[cur] > min)
                return;

            if (cur == K) {
                count++;
                visited[K] = min;
            }
            for (int next : new int[]{cur - 1,cur +1, cur * 2}) {
                if (0 <= next && next <= MAX && visited[next] == 0){
                    visited[next] = visited[cur] + 1;
                    q.addLast(next);
                    if (next == K) {
                        min = visited[next];
                        visited[K] = 0;
                    }
                }
            }
        }
    }
}
