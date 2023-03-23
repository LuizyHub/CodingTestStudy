import java.util.ArrayDeque;

public class Beak1463 {
    private static int N, M;
    private static int[] track;
    private static ArrayDeque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        N =read();
        track = new int[N+1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(N);

        while (!deque.isEmpty()){
            int now = deque.pollFirst();
            int count = track[now] + 1;
            if (now == 1){
                System.out.println(count - 1);
                break;
            }

            int next = now - 1;
            if (track[next] == 0){
                track[next] = count;
                deque.addLast(next);
            }
            if (now % 3 == 0 && track[next = now / 3] == 0){
                track[next] = count;
                deque.addLast(next);
            }
            if (now % 2 ==0 && track[next = now / 2] == 0){
                track[next] = count;
                deque.addLast(next);
            }

        }
    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        return n;

    }
}
