import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        long sum = 0;
        char[] s1 = st.nextToken().toCharArray(), s2 = st.nextToken().toCharArray();
        for (char c : s1) {
            for (char c1 : s2) {
                sum += (c - '0') * (c1 - '0');
            }
        }
        System.out.println(sum);
    }
}