import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek1193 {
    public static void main(String[] args) throws IOException {
        int X = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int i = 1;
        int sum = 0;
        for (; sum < X; i++)
            sum += i;

        if ((i & 1) == 0) {
            System.out.print((sum - X + 1) + "/" + (i - (sum - X + 1)));
        }
        else {
            System.out.print((i - (sum - X + 1)) + "/" + (sum - X + 1));
        }
    }
}
