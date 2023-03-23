import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Beak1753_2 {
    private static final int MAX = 200_000;
    private static class Node{
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine())-1;
        ArrayList<Node>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e,c));
        }

        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = MAX;
        }

        PriorityQueue<Node> q =new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        q.offer(new Node(start,0));

        dist[start] = 0;

        while (!q.isEmpty()){
            Node curNode = q.poll();

            if (dist[curNode.idx] < curNode.cost){
                continue;
            }

            for (int i = 0; i < graph[curNode.idx].size(); i++) {
                Node nxtNode = graph[curNode.idx].get(i);

                if (dist[nxtNode.idx] > curNode.cost + nxtNode.cost){
                    dist[nxtNode.idx] = curNode.cost + nxtNode.cost;
                    q.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
                }
            }
        }

        for (int i : dist) {
            if (i >= MAX)
                sb.append("INF\n");
            else {
                sb.append(i).append('\n');
            }
        }

        System.out.println(sb);
    }
}
