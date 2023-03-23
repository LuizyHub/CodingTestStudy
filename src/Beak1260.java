import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak1260 {
    private static int N, M, V;
    private static boolean[][] Edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        Edges = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Edges[a][b] = true;
            Edges[b][a] = true;
        }
        dfs(V);
        System.out.println(sb);
        bfs();
    }
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();
    private static void dfs(int v){
        sb.append(v).append(' ');
        visited[v] = true;
        for (int i = 1; i <= N; i++) {
            if (Edges[v][i] && !visited[i]){
                dfs(i);
            }
        }
    }
    private static void bfs(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        StringBuilder sb = new StringBuilder();

        q.addLast(V);
        visited[V] = true;

        while (!q.isEmpty()){
            int cnt = q.pollFirst();
            sb.append(cnt).append(' ');
            for (int i = 1; i <= N; i++) {
                if (Edges[cnt][i] && !visited[i]) {
                    q.addLast(i);
                    visited[i] = true;
                }
            }
        }

        System.out.println(sb);

    }
}
