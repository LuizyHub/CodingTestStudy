import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak24416 {
    private static int count = 0;
    private static int pib(int i){
        count++;
        if (i == 1 || i == 2)
            return 1;
        else return pib(i-1) + pib(i-2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        pib(N);
        System.out.println(count);

    }
}
