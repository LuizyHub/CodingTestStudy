import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak13549 {

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
        int[] visited = new int[MAX+1];
        q.addLast(N); // startPoint
        int twice = N<<1;
        while (twice<K){
            if (0 <= twice && twice <= MAX && visited[twice] == 0){
                visited[twice] = visited[N];
                q.addLast(twice);
            }
            twice <<= 1;
        }

        while (!q.isEmpty()){
            int cur = q.pollFirst();

            if (cur == K){
                System.out.println(visited[cur]);
                return;
            }
            for (int next : new int[]{cur - 1,cur +1}) {

                if (0 <= next && next <= MAX && visited[next] == 0){
                    visited[next] = visited[cur] + 1;
                    q.addLast(next);
                    twice = next;
                    while (twice<K){
                        twice <<= 1;
                        if (0 <= twice && twice <= MAX && visited[twice] == 0){
                            visited[twice] = visited[cur] + 1;
                            q.addLast(twice);
                        }
                    }

                }
            }
        }
    }
}
