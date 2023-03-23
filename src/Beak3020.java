import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] layer = new int[H+1];
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (i%2 == 0)
                layer[tmp]--;
            else
                layer[H-tmp]++;
        }
        int ans = N/2;
        int count = 0;
        int min = N;
        for (int i = 0; i < H; i++) {
            ans += layer[i];
            if (ans < min){
                min = ans;
                count = 1;
            }
            else if (ans == min)
                count++;
        }
        System.out.println(min + " " + count);
    }
}