import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak17386 {
    private static class Dot{
        public long x,y;

        public Dot(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Dot[] dots = new Dot[4];
        st = new StringTokenizer(br.readLine());
        dots[0] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        dots[1] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        dots[2] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        dots[3] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        int ans = 0;
        long rel1 = (dots[2].x-dots[0].x)*(dots[1].y-dots[0].y) - (dots[2].y - dots[0].y)*(dots[1].x - dots[0].x);
        long rel0 = (dots[3].x-dots[0].x)*(dots[1].y-dots[0].y) - (dots[3].y - dots[0].y)*(dots[1].x - dots[0].x);
        if (!((rel0<0 && rel1<0) || (rel0>0 && rel1>0))){
            rel1 = (dots[1].x-dots[2].x)*(dots[3].y-dots[2].y) - (dots[1].y - dots[2].y)*(dots[3].x - dots[2].x);
            rel0 = (dots[0].x-dots[2].x)*(dots[3].y-dots[2].y) - (dots[0].y - dots[2].y)*(dots[3].x - dots[2].x);
            if (!((rel0<0 && rel1<0) || (rel0>0 && rel1>0)))
                ans = 1;
        }
        System.out.println(ans);
    }
}

