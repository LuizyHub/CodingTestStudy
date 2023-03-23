import java.io.*;
import java.util.*;

public class Beak1697 {

    private static int N;
    private static int K;
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
                System.out.println(visited[cur]);
                return;
            }
            for (int next : new int[]{cur - 1,cur +1, cur * 2}) {

                if (0 <= next && next <= MAX && visited[next] == 0){
                    visited[next] = visited[cur] + 1;
                    q.addLast(next);
                }
            }
        }
    }
}
