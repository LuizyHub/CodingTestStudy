import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1922_1 {
    private static class Node implements Comparable<Node>{
        public int to;
        public int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight,o.weight);
        }
    }
    private static ArrayList<Node>[] weights;
    private static boolean[] visit;
    private static int V,E;
    private static int primPQ(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int total = 0;
        pq.offer(new Node(start,0));
        while (!pq.isEmpty()){
            Node node = pq.poll();
            if (visit[node.to])
                continue;
            total += node.weight;
            visit[node.to] = true;
            for (int i = 0; i < weights[node.to].size(); i++) {
                Node next = weights[node.to].get(i);
                if (!visit[next.to])
                    pq.add(next);
            }
        }
        return total;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        visit = new boolean[V];
        weights = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            weights[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            if (from == to)
                continue;
            weights[from].add(new Node(to,weight));
            weights[to].add(new Node(from,weight));
        }

        System.out.println(primPQ(0));
    }
}
