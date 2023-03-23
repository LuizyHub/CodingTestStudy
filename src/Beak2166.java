import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak2166 {
    private static long[] x;
    private static long[] y;

//    private static double getTriangleMass(){
//        return Math.abs((x[0]*y[1] + x[1]*y0 + x0*y[0]) - (y[0]*x[1] + y[1]*x0 + y0*x[0])) / 2.0;
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        long longans=0;
        st = new StringTokenizer(br.readLine());

        x = new long[N+1];
        y = new long[N+1];

        x[0] = x[N] = Long.parseLong(st.nextToken());
        y[0] = y[N] = Long.parseLong(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
            longans += (x[i-1]*y[i] - x[i]*y[i-1]);
        }
        longans += (x[N-1]*y[N] - x[N]*y[N-1]);

        System.out.printf("%.1f", Math.abs(longans/2.0));
    }
}
