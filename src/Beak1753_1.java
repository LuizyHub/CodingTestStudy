import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Beak1753_1 {
    private static final int INF = 200_000;
    private static HashSet<Integer> set = new HashSet<>();
    private static PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
    private static int getMin(){
        int min = INF;
        for (Integer i : set) {
            min = Math.min(min, i);
        }
        set.remove(min);
        return min;
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Point,Integer> edges = new HashMap<>();
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine())-1;
        int[] vert = new int[V];
        for (int i = 0; i < V; i++) {
            set.add(i);
        }
        set.remove(N);
//        for (int i = 0; i < V; i++) {
//            Arrays.fill(vert[i],INF);
//            vert[i][i] = 0;
//        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            edges.put(new Point(x,y), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < V; i++) {
            if (edges.containsKey(new Point(N,i))){
                vert[i] = edges.get(new Point(N,i));
            }
            else
                vert[i] = INF;
        }
        vert[N] = 0;
        while (!set.isEmpty()){
            int token = getMin();
            for (Integer i : set) {
                if (edges.containsKey(new Point(token,i))){
                    int tmp = edges.get(new Point(token,i));
                    if (vert[i] > vert[token] + tmp)
                        vert[i] = vert[token] + tmp;
                }
            }
        }
//        for (int i = 0; i < V; i++) {
//            if (i == N)
//                continue;
//            for (int j = 0; j < V; j++) {
//                if (j == N)
//                    continue;
//                if (edges.containsKey(new Point(i,j))){
//                    int tmp = edges.get(new Point(i,j));
//                    if (vert[j] > vert[i] + tmp)
//                        vert[j] = vert[i] + tmp;
//                }
//            }
//        }
        for (int i : vert) {
            if (i>=INF)
                sb.append("INF\n");
            else
                sb.append(i).append('\n');

        }
        System.out.println(sb);
    }
}
