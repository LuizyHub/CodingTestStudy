import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak17626 {
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int[] twice = new int[224];
        int[] count = new int[N + 1];
        int max = 0;
        loop:
        while (true) {
            for (int i = 1; i <= 223; i++) {
                int in = i * i;
                if (N < in) {
                    max = i;
                    break;
                }
                if (N == in) {
                    System.out.println(1);
                    break loop;
                }
                twice[i] = in;
                count[in] = 1;
            }
            for (int i = 1; i < max; i++) {
                for (int j = 1; j < max; j++) {
                    int in = twice[i] + twice[j];
                    if (N < in)
                        break;
                    if (N == in) {
                        System.out.println(2);
                        break loop;
                    }
                    if (count[in] != 1)
                        count[in] = 2;
                }
            }
            for (int i = 1; i <= N; i++) {
                if (count[i] == 2) {
                    for (int j = 1; j < max; j++) {
                        if (N == twice[j] + i) {
                            System.out.println(3);
                            break loop;
                        }
                    }
                }
            }
            System.out.println(4);
            break;
        }
    }
}
