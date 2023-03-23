import java.io.*;
import java.util.Arrays;

public class Baek16496 {
    private static int N;
    private static String[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        nums = br.readLine().split(" ");
        if (nums[0].equals("0")){
            bw.write("0");
        }
        else {
            Arrays.sort(nums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
            for (String num : nums) {
                bw.write(num);
            }
        }
        bw.flush();
    }
}
