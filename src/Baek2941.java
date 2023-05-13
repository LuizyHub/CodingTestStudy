import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek2941 {
    private static String[] croas = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    private static String s;
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        for (String croa : croas) {
            int idx = 0;
            while ((idx = s.indexOf(croa, idx) + 1) > 0) {
                count += croa.length() - 1;
            }
        }

        int idx = 0;
        while ((idx = s.indexOf("z=", idx) + 1) > 1) {
            if (s.charAt(idx - 2) == 'd')
                count -= 1;
        }

        System.out.print(s.length() - count);
    }
}
