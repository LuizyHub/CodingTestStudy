import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10988 {
    public static void main(String[] args) throws IOException {
        String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
        String sr = new StringBuilder(s).reverse().toString();

        System.out.print(s.equals(sr) ? 1 : 0);
    }
}
