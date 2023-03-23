import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1932 {
    private static int tag = 0;
    private static void setTag(){
        if (tag == 0)
            tag = 1;
        else
            tag = 0;
    }
    private static int getTag(){
        if (tag == 0)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] tri = new int[2][n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        tri[tag][0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            setTag();
            st = new StringTokenizer(br.readLine());
            tri[tag][0] = tri[getTag()][0] + Integer.parseInt(st.nextToken());

            for (int j = 1; j < i; j++) {
                tri[tag][j] = Math.max(tri[getTag()][j],tri[getTag()][j-1]) + Integer.parseInt(st.nextToken());
            }
            tri[tag][i] = tri[getTag()][i-1] + Integer.parseInt(st.nextToken());
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(tri[tag][i],max);
        }
        System.out.println(max);
    }
}
