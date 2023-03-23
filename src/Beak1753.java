import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak1753 {
    private static final int INF = 200_000;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine())-1;
        int[][] vert = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(vert[i],INF);
            vert[i][i] = 0;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            vert[x][y] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (vert[N][j] > vert[N][i] + vert[i][j])
                    vert[N][j] = vert[N][i] + vert[i][j];
            }
        }
        for (int i : vert[N]) {
            if (i>=INF)
                sb.append("INF\n").append('\n');
            else
                sb.append(i).append('\n');

        }
        System.out.println(sb);
    }
}
