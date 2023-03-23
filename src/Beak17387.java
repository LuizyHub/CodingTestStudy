import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak17387 {
    private static Dot[] dots;
    private static int ans = 0;
    private static class Dot{
        public long x,y;

        public Dot(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int sign(long num){
        if (num > 0)
            return 1;
        else if (num == 0)
            return 0;
        else
            return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        dots = new Dot[4];
        st = new StringTokenizer(br.readLine());
        dots[0] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        dots[1] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        dots[2] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        dots[3] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        int rel012 = sign((dots[2].x-dots[0].x)*(dots[1].y-dots[0].y) - (dots[2].y - dots[0].y)*(dots[1].x - dots[0].x)); //012
        int rel013 = sign((dots[3].x-dots[0].x)*(dots[1].y-dots[0].y) - (dots[3].y - dots[0].y)*(dots[1].x - dots[0].x)); //013
        int rel123 = sign((dots[1].x-dots[2].x)*(dots[3].y-dots[2].y) - (dots[1].y - dots[2].y)*(dots[3].x - dots[2].x)); //123
        int rel023 = sign((dots[0].x-dots[2].x)*(dots[3].y-dots[2].y) - (dots[0].y - dots[2].y)*(dots[3].x - dots[2].x)); //023
        if (rel012 * rel013 <= 0 && rel023 * rel123 <= 0){
            if (rel012 == 0 && rel123 == 0){
                if (((dots[0].x - dots[2].x)*(dots[1].x - dots[2].x) <= 0) && ((dots[0].y - dots[2].y)*(dots[1].y - dots[2].y) <= 0)
                || ((dots[0].x - dots[3].x)*(dots[1].x - dots[3].x) <= 0) && ((dots[0].y - dots[3].y)*(dots[1].y - dots[3].y) <= 0)
                || ((dots[2].x - dots[1].x)*(dots[3].x - dots[1].x) <= 0) && ((dots[2].y - dots[1].y)*(dots[3].y - dots[1].y) <= 0)
                || ((dots[2].x - dots[0].x)*(dots[3].x - dots[0].x) <= 0) && ((dots[2].y - dots[0].y)*(dots[3].y - dots[0].y) <= 0))
                    ans = 1;
            }
            else
                ans =1;
        }

//        if (rel012 == 0 && ((dots[0].x - dots[2].x)*(dots[1].x - dots[2].x) <= 0) && ((dots[0].y - dots[2].y)*(dots[1].y - dots[2].y) <= 0)){ // if 012 parallel and 2 is between 01
//                ans = 1;
//        }
//        else if (rel013 == 0 && ((dots[0].x - dots[3].x)*(dots[1].x - dots[3].x) <= 0) && ((dots[0].y - dots[3].y)*(dots[1].y - dots[3].y) <= 0)){ // if 013 parallel and 3 is between 01
//                ans = 1;
//        }
//        else if (((rel012<0 && rel013>0) || (rel012>0 && rel013<0))){ // 2,3 is other side of line 01
//            if (rel123 == 0 && ((dots[2].x - dots[1].x)*(dots[3].x - dots[1].x) <= 0) && ((dots[2].y - dots[1].y)*(dots[3].y - dots[1].y) <= 0)){ // if 123 parallel and 1 is between 23
//                    ans = 1;
//            }
//            else if (rel023 == 0 && ((dots[2].x - dots[0].x)*(dots[3].x - dots[0].x) <= 0) && ((dots[2].y - dots[0].y)*(dots[3].y - dots[0].y) <= 0)) { // if 023 parallel and 0 is between 23
//                    ans = 1;
//            }
//            else if (((rel123<0 && rel023>0) || (rel123>0 && rel023<0))) // 0,1 is other side of line 23
//                ans = 1;
//        }
        System.out.println(ans);
    }
}
