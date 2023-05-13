import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Beak2098 {
    private static class Node{
        int idx, weight;
        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
    private static int N, min = Integer.MAX_VALUE;
    private static ArrayList<Node>[] nodeList;
    private static int[][] matrix;
    private static boolean[] visit;
    private static void dfs(int count, int idx, int sum){
        if (count == N){
            sum += matrix[idx][0];
            min = Math.min(min, sum);
            return;
        }

        for (Node node : nodeList[idx]) {
            int nextIdx = node.idx;
            if (visit[nextIdx]) continue;
            int weight = node.weight;
            sum += weight;
            if (weight >= min){
                sum -= weight;
                continue;
            }
            visit[nextIdx] = true;
            dfs(count + 1, nextIdx, sum);
            sum -= weight;
            visit[nextIdx] = false;
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodeList = new ArrayList[N];
        visit = new boolean[N];
        matrix = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodeList[i] = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (i != j){
                    matrix[i][j] = num;
                    nodeList[i].add(new Node(j, num));
                }
            }
            nodeList[i].sort((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        }
        visit[0] = true;
        dfs(1,0, 0);
        System.out.print(min);
    }
}
