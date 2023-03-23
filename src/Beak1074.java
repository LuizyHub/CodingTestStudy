import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int ans = 0;
        System.out.println(log2(r));
        System.out.println(log2(c));

    }

    private static int getAnswer(int x, int y, int ans){

        int big = Math.max(x,y);
        int half = 1<<log2(big);
        int xx = x/half;
        int yy = y/half;
        if (xx == 1 || yy == 1){

        } else if (xx == 1 || yy == 0) {

        } else if (xx == 0 || yy == 1) {

        }
        else {

        }

        System.out.println("xx = " + xx);
        System.out.println("yy = " + yy);


        return 0;
    }
    private static int log2(int i){
        int ans = -1;
        while(i>0){
            i = i>>1;
            ans++;
        }
        return ans;
    }
}
