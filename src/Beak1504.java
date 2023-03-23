import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Beak1504 {
    private static final int MAX = 800*1000;
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
        int start = 0;
        int end = V -1;
        ArrayList<Node>[] graph = new ArrayList[V];
        ArrayList<Integer>[] route = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
            route[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e,c));
            graph[e].add(new Node(s,c));
        }

        st = new StringTokenizer(br.readLine());
        int V1 = Integer.parseInt(st.nextToken())-1;
        int V2 = Integer.parseInt(st.nextToken())-1;

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
                    route[nxtNode.idx].clear();
                    route[nxtNode.idx].addAll(route[curNode.idx]);
                    route[nxtNode.idx].add(curNode.idx);
                    q.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
                }
            }
        }

//        System.out.println(Arrays.toString(dist));
//
//        for (ArrayList<Integer> arrayList : route) {
//            System.out.println(arrayList.toString());
//        }


        /*

        start----v1
        |       / |
        |     /   |
        |   /     |
        v2 -----end

         */



        // start ---- V1 ---- V2 ---- end
        if (route[end].contains(V1) && route[end].contains(V2) && dist[end] < MAX) {
            System.out.println(dist[end]);
            System.exit(0);
        }

        int starToV1 = dist[V1];
        int starToV2 = dist[V2];

        // start ---- V2
        //            |
        //            V1
        if (route[V1].contains(V2))
            starToV2 = MAX;

        // start ---- V1
        //            |
        //            V2
        if (route[V2].contains(V1))
            starToV1 = MAX;


        q.clear();

        q.offer(new Node(end,0));

        for (int i = 0; i < V; i++) {
            dist[i] = MAX;
        }

        dist[end] = 0;

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

        int V1ToEnd = dist[V1];
        int V2ToEnd = dist[V2];



        // start ---- V2
        //            |
        //            V1 ---- end
        if (starToV2 == MAX && V1ToEnd < MAX){
            System.out.println(starToV1 + V1ToEnd);
            System.exit(0);
        }

        // start ---- V1
        //            |
        //            V2 ---- end
        if (starToV1 == MAX && V2ToEnd < MAX){
            System.out.println(starToV2 + V2ToEnd);
            System.exit(0);
        }


        q.clear();

        q.offer(new Node(V1,0));

        for (int i = 0; i < V; i++) {
            dist[i] = MAX;
        }

        dist[V1] = 0;

        while (!q.isEmpty()){
            Node curNode = q.poll();
            if (curNode.idx == V2)
                break;
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

        int V1ToV2 = dist[V2];


        int min = Math.min(starToV1 + V1ToV2 + V2ToEnd, starToV2 + V1ToV2 + V1ToEnd);

        if (min >= MAX)
            System.out.println(-1);
        else
            System.out.println(min);


    }
}
