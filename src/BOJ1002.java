import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1002 {
    private static int T;
    private static int x1, y1, x2, y2, r1, r2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            int distance = (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1);
            if (distance == 0 && r1 == r2) {
                sb.append(-1).append('\n');
                continue;
            }
            int powSum = (r2 + r1)*(r2 + r1);
            if (distance > powSum) {
                sb.append(0);
            }
            else if (distance == powSum) {
                sb.append(1);
            }
            else {
                int dif = (r2 - r1)*(r2 - r1);
                if (dif < distance) {
                    sb.append(2);
                }
                else if (dif == distance) {
                    sb.append(1);
                }
                else {
                    sb.append(0);
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}