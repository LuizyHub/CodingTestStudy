import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Beak1005 {
    private static Building[] buildings;
    private static int[] buildingTime;
    private static class Building{
        public int time;
        public ArrayList<Integer> builtList = new ArrayList<>();
        public Building(int time) {
            this.time = time;
        }
    }
    private static int build(int i){
        if (buildingTime[i]==-1){ // if building time is never calculated
            int max=0;
            for (Integer integer : buildings[i].builtList) {
                max = Math.max(max, build(integer));
            }
            buildingTime[i] = buildings[i].time + max;
        }
        return buildingTime[i];
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T--!=0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            buildings = new Building[N];
            buildingTime = new int[N];
            Arrays.fill(buildingTime,-1);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                buildings[i] = new Building(Integer.parseInt(st.nextToken()));
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken())-1;
                int to = Integer.parseInt(st.nextToken())-1;
                buildings[to].builtList.add(from);
            }
            sb.append(build(Integer.parseInt(br.readLine())-1)).append('\n');
        }
        System.out.println(sb);
    }
}