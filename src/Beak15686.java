import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Beak15686 {
    private static int M;
    private static int C;
    private static int min = Integer.MAX_VALUE;
    private static ArrayList<Point> houses = new ArrayList<>();
    private static ArrayList<Point> chis = new ArrayList<>();
    private static void com(ArrayList<Integer> ai){
        int len = ai.size();
        if (len == M){
            int ans = 0;
            for (Point house : houses) {
                int dis = Integer.MAX_VALUE;
                for (Integer p : ai) {
                    int xx = chis.get(p).x;
                    int yy = chis.get(p).y;
                    int cntdis = Math.abs(house.x-xx) + Math.abs(house.y-yy);
                    dis = Math.min(dis,cntdis);
                }
                ans += dis;
            }
            min = Math.min(min,ans);
        }
        if (len == 0){
            for (int i = 0; i < C; i++) {
                ArrayList<Integer> nxt = (ArrayList<Integer>)ai.clone();
                nxt.add(i);
                com(nxt);
            }
        }
        else {
            for (int i = ai.get(len-1)+1; i < C; i++) {
                ArrayList<Integer> nxt = (ArrayList<Integer>)ai.clone();
                nxt.add(i);
                com(nxt);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1)
                    houses.add(new Point(i, j));
                else if (tmp ==2)
                    chis.add(new Point(i, j));
            }
        }
        C = chis.size();
        com(new ArrayList<Integer>());

        System.out.println(min);
    }
}