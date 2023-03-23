import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak1001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(br.read() - br.read() -br.read() + 32);
    }
}
