import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak4386 {
    private static int N;
    private static double[][] stars;
    private static double[] distance;
    private static boolean[] visited;
    private static double sumMinDistance = 0.0;
    private static double getDistance(int from, int to){
        return Math.sqrt(Math.pow(stars[from][0] - stars[to][0],2) + Math.pow(stars[from][1] - stars[to][1],2));
    }
    private static void prim(int start){
        distance[start] = 0;
        for (int i = 0; i < N; i++) {
            visited[start] = true;
            sumMinDistance += distance[start];
            int next = -1;
            double minDistance = Double.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (visited[j]) continue;
                double dis = getDistance(start,j);
                if (distance[j] > dis){
                    distance[j] = dis;
                }
                if (distance[j] < minDistance){
                    minDistance = distance[j];
                    next = j;
                }
            }
            start = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        stars = new double[N][2];
        distance = new double[N];
        visited = new boolean[N];
        Arrays.fill(distance, Double.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }
        prim(0);
        System.out.println(sumMinDistance);
    }
}
