import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1863 {
    private static int n, min = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(0);

        for (int i = 0; i < n; i++) {
            int y = Integer.parseInt(br.readLine().split(" ")[1]);

            while (deque.peekLast() > y) {
                deque.pollLast();
                min++;
            }

            if (deque.peekLast() != y)
                deque.addLast(y);
        }

        min += (deque.size() - 1);

        System.out.print(min);
    }
}
