import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Beak1238 {
    private static final int MAX = 200_000;
    private static class Node{
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken())-1;
        ArrayList<Node>[] graph = new ArrayList[V];
        ArrayList<Node>[] graphReverse = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
            graphReverse[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e,c));
            graphReverse[e].add(new Node(s,c));
        }

        int[] dist = new int[V];
        int[] distReverse = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = MAX;
            distReverse[i] = MAX;
        }

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        PriorityQueue<Node> qReverse = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        q.offer(new Node(start,0));
        qReverse.offer(new Node(start,0));

        dist[start] = 0;
        distReverse[start] = 0;

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

        while (!qReverse.isEmpty()){
            Node curNode = qReverse.poll();

            if (distReverse[curNode.idx] < curNode.cost){
                continue;
            }

            for (int i = 0; i < graphReverse[curNode.idx].size(); i++) {
                Node nxtNode = graphReverse[curNode.idx].get(i);

                if (distReverse[nxtNode.idx] > curNode.cost + nxtNode.cost){
                    distReverse[nxtNode.idx] = curNode.cost + nxtNode.cost;
                    qReverse.offer(new Node(nxtNode.idx, distReverse[nxtNode.idx]));
                }
            }
        }
        
        int max = 0;
        for (int i = 0; i < V; i++) {
            max = Math.max(max, dist[i] + distReverse[i]);
        }
        System.out.println(max);
    }
}
