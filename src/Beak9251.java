import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Beak9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int[] alpha = new int[26];
        Arrays.fill(alpha,-1);
        int[] index = new int[s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            index[i] = alpha[c-'A'] = s1.indexOf(c,alpha[c-'A'] + 1);
        }
        System.out.println(Arrays.toString(index));
        System.out.println('Z' - 'A');

    }
}
