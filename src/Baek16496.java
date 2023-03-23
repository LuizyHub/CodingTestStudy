import java.io.*;
import java.util.Arrays;
public class Baek16496 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br.readLine();
        String[] nums = br.readLine().split(" ");
        Arrays.sort(nums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if (nums[0].equals("0"))
            bw.write("0");
        else
            for (String num : nums)
                bw.write(num);
        bw.flush();
    }
}
