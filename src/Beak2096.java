import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] max1 = new int[3];
        int[] max2 = new int[3];
        int[] min1 = new int[3];
        int[] min2 = new int[3];
        int[] current = new int[3];
        boolean isfirst = true;
        st = new StringTokenizer(br.readLine());

        min1[0] = max1[0] = Integer.parseInt(st.nextToken());
        min1[1] = max1[1] = Integer.parseInt(st.nextToken());
        min1[2] = max1[2] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            current[0] = Integer.parseInt(st.nextToken());
            current[1] = Integer.parseInt(st.nextToken());
            current[2] = Integer.parseInt(st.nextToken());

            if (isfirst){
                max2[0] = Math.max(max1[0], max1[1]);
                max2[2] = Math.max(max1[1], max1[2]);
                max2[1] = Math.max(max2[0], max2[2]) + current[1];
                max2[0] += current[0];
                max2[2] += current[2];

                min2[0] = Math.min(min1[0], min1[1]);
                min2[2] = Math.min(min1[1], min1[2]);
                min2[1] = Math.min(min2[0], min2[2]) + current[1];
                min2[0] += current[0];
                min2[2] += current[2];

                isfirst = false;
            }
            else {
                max1[0] = Math.max(max2[0], max2[1]);
                max1[2] = Math.max(max2[1], max2[2]);
                max1[1] = Math.max(max1[0], max1[2]) + current[1];
                max1[0] += current[0];
                max1[2] += current[2];

                min1[0] = Math.min(min2[0], min2[1]);
                min1[2] = Math.min(min2[1], min2[2]);
                min1[1] = Math.min(min1[0], min1[2]) + current[1];
                min1[0] += current[0];
                min1[2] += current[2];

                isfirst = true;
            }
        }
        if (isfirst)
            System.out.println(Math.max(max1[0] , Math.max(max1[1], max1[2])) + " " + Math.min(min1[0] , Math.min(min1[1], min1[2])));
        else
            System.out.println(Math.max(max2[0] , Math.max(max2[1], max2[2])) + " " + Math.min(min2[0] , Math.min(min2[1], min2[2])));

    }
}
