import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Beak2887 {
    private static int V;
    private static int[][] planet;
    private static int[] distance;
    private static boolean[] visit;
    private static int getWeight(int a, int b){
        return Math.min(Math.abs(planet[a][0] - planet[b][0]) , Math.min(Math.abs(planet[a][1] - planet[b][1]), Math.abs(planet[a][2] - planet[b][2])));
    }
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
    }
    private static int primBST(int start){
        TreeSet<Node> treeSet = new TreeSet<>(); // Node(to, weight)
        int total = 0;
        distance[start] = 0;
        treeSet.add(new Node(start,0));
        while (!treeSet.isEmpty()){
            start = treeSet.pollFirst().to;
            total += distance[start];
            visit[start] = true;
            for (int i = 0; i < V; i++) {
                int weight = getWeight(start,i);
                if (!visit[i] && distance[i] > weight){
                    if (distance[i] == Integer.MAX_VALUE) { // never was in tree;
                        distance[i] = weight;
                        treeSet.add(new Node(i,weight));
                    }
                    else { // is in tree;
                        treeSet.remove(new Node(i, distance[i]));
                        distance[i] = weight;
                        treeSet.add(new Node(i,weight));
                    }
                }
            }
        }
        return total;
    }
//    private static class Edge implements Comparable<Edge> {
//        int to;
//        int weight;
//
//        public Edge(int to, int weight) {
//            this.to = to;
//            this.weight = weight;
//        }
//
//        @Override
//        public int compareTo(Edge o) {
//            return this.weight - o.weight;
//        }
//    }

//    private static int prim(int start) {
//        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // weight, index
//        int total = 0;
//
//        for (int i = 0; i < N; i++) {
//            distance[i] = getWeight(start, i);
//            if (distance[i] == 0)
//                visit[i] = true;
//            else
//                treeMap.put(distance[i], i);
//        }
//        visit[start] = true;
//
//        while (!treeMap.isEmpty()) {  // O(N)
//            int idx = treeMap.remove(treeMap.firstKey());
//            visit[idx] = true;
//            total += distance[idx];
//            for (int i = 0; i < N; i++) { // O(N)
//                if (!visit[i]) {
//                    int weight = getWeight(idx, i);
//                    if (distance[i] > weight) {
//                        treeMap.remove(distance[i]); // O(logN)
//                        distance[i] = weight;
//                        treeMap.put(weight, i);
//                    }
//                }
//            }
//        }
//        return total;
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        planet = new int[V][3];
        distance = new int[V];
        visit = new boolean[V];
        Arrays.fill(distance,Integer.MAX_VALUE);
        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            planet[i][0] = Integer.parseInt(st.nextToken());
            planet[i][1] = Integer.parseInt(st.nextToken());
            planet[i][2] = Integer.parseInt(st.nextToken());
        }
        System.out.println(primBST(0));
    }
}
