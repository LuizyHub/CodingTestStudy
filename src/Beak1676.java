import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Beak1676 {
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        System.out.println(N/5 + N/25 + N/125);
    }
}
