import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Beak6497 {
    private static class Node implements Comparable<Node>{
        public int to;
        public int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node)obj;
            if (this.weight == node.weight && this.to == node.to)
                return true;
            else
                return false;
        }
        @Override
        public int compareTo(Node o) {
            if (this.weight == o.weight)
                return Integer.compare(this.to,o.to);
            else
                return Integer.compare(this.weight,o.weight);
        }

        @Override
        public String toString() {
            return  "weight : " + weight + ", idx : " + to ;
        }
    }
    private static ArrayList<Node>[] weights;
    private static int[] distance;
    private static boolean[] visit;
    private static int V,E;
    private static int primBST(int start){
        TreeSet<Node> treeSet = new TreeSet<>(); // Node(to, weight)
        int total = 0;
        distance[start] = 0;
        treeSet.add(new Node(start,0));
        while (!treeSet.isEmpty()){
            start = treeSet.pollFirst().to;
            total += distance[start];
            visit[start] = true;
            for (int i = 0; i < weights[start].size(); i++) {
                Node node = weights[start].get(i);
                if (!visit[node.to] && distance[node.to] > node.weight){
                    if (distance[node.to] == Integer.MAX_VALUE) { // never was in tree;
                        distance[node.to] = node.weight;
                        treeSet.add(new Node(node.to, node.weight));
                    }
                    else { // is in tree;
                        treeSet.remove(new Node(node.to, distance[node.to]));
                        distance[node.to] = node.weight;
                        treeSet.add(new Node(node.to, node.weight));
                    }
                }
            }
        }
        return total;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            if (V==0 && E==0)
                break;
            int sum = 0;

            distance = new int[V];
            Arrays.fill(distance,Integer.MAX_VALUE);
            visit = new boolean[V];
            weights = new ArrayList[V];

            for (int i = 0; i < V; i++) {
                weights[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                weights[from].add(new Node(to,weight));
                weights[to].add(new Node(from,weight));
                sum+=weight;
            }

            System.out.println(sum - primBST(0));
        }
    }
}
