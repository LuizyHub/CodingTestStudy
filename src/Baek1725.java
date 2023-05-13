import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek1725 {
    private static int N, startIdx = 1;
    private static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while (startIdx < N)
            startIdx <<= 1;

        tree = new int[startIdx << 1];

    }
}
