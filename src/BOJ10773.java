import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ10773 {
    private static int K, sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        while (K-- > 0) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp == 0)
                sum -= deque.pollLast();
            else {
                sum += tmp;
                deque.addLast(tmp);
            }
        }

        System.out.print(sum);
    }
}
