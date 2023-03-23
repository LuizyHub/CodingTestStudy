import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums= new int[N+1];
        st = new StringTokenizer(br.readLine());
        int maxLength = 0;
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num >= S){
                System.out.println(1);
                System.exit(0);
            }
            nums[i] = nums[i-1] + num;
            if (nums[i] >= S){
                maxLength = i;
                break;
            }
        }
        if (maxLength == 0) {
            System.out.println(0);
            System.exit(0);
        }
        for (int i = maxLength+1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num >= S){
                System.out.println(1);
                System.exit(0);
            }
            nums[i] = nums[i-1] + num;
            while (nums[i] - nums[i-maxLength+1] >= S){
                maxLength--;
            }
        }
        if (maxLength==N){
            while (nums[N] - nums[N-maxLength+1] >= S){
                maxLength--;
            }
        }
        System.out.println(maxLength);
    }
}
