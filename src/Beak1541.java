import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),"-");
        int ans = 0;
        StringTokenizer nums = new StringTokenizer(st.nextToken(),"+");
        while (nums.hasMoreTokens()){
            ans += Integer.parseInt(nums.nextToken());
        }
        while (st.hasMoreTokens()){
            int tmp = 0;
            nums = new StringTokenizer(st.nextToken(),"+");
            while (nums.hasMoreTokens()){
                tmp += Integer.parseInt(nums.nextToken());
            }
            ans -= tmp;
        }
        System.out.println(ans);

    }
}
