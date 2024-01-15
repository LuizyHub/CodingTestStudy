import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1963 {
    private static int T;
    private static boolean[][][][] isPrime = new boolean[10][10][10][10];
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        setPrimes();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int toThousand = to / 1000;
            int toHundred = (to / 100) % 10;
            int toTen = (to / 10) % 10;
            int toOne = to % 10;


            int count = -1;
            ArrayDeque<Struct> deque = new ArrayDeque<>();
            boolean[][][][] visited = new boolean[10][10][10][10];
            deque.addLast(new Struct(from / 1000, (from / 100) % 10, (from / 10) % 10, from % 10, 0));
            visited[from / 1000][(from / 100) % 10][(from / 10) % 10][from % 10] = true;

            while (!deque.isEmpty()) {
                Struct cur = deque.pollFirst();

                if (
                    cur.thousand == toThousand &&
                    cur.hundred == toHundred &&
                    cur.ten == toTen &&
                    cur.one == toOne
                ) {
                    count = cur.count;
                    break;
                }

                for (int i = 0; i < 10; i++) {
                    if (isPrime[cur.thousand][cur.hundred][cur.ten][i] && !visited[cur.thousand][cur.hundred][cur.ten][i]) {
                        visited[cur.thousand][cur.hundred][cur.ten][i] = true;
                        deque.addLast(new Struct(cur.thousand, cur.hundred, cur.ten, i, cur.count + 1));
                    }
                    if (isPrime[cur.thousand][cur.hundred][i][cur.one] && !visited[cur.thousand][cur.hundred][i][cur.one]) {
                        visited[cur.thousand][cur.hundred][i][cur.one] = true;
                        deque.addLast(new Struct(cur.thousand, cur.hundred, i, cur.one, cur.count + 1));
                    }
                    if (isPrime[cur.thousand][i][cur.ten][cur.one] && !visited[cur.thousand][i][cur.ten][cur.one]) {
                        visited[cur.thousand][i][cur.ten][cur.one] = true;
                        deque.addLast(new Struct(cur.thousand, i, cur.ten, cur.one, cur.count + 1));
                    }
                    if (i == 0) continue;
                    if (isPrime[i][cur.hundred][cur.ten][cur.one] && !visited[i][cur.hundred][cur.ten][cur.one]) {
                        visited[i][cur.hundred][cur.ten][cur.one] = true;
                        deque.addLast(new Struct(i, cur.hundred, cur.ten, cur.one, cur.count + 1));
                    }
                }
            }

            sb.append(count).append('\n');
        }

        System.out.print(sb);
    }

    private static class Struct {
        public int thousand, hundred, ten, one, count;

        public Struct(int thousand, int hundred, int ten, int one, int count) {
            this.thousand = thousand;
            this.hundred = hundred;
            this.ten = ten;
            this.one = one;
            this.count = count;
        }
    }

    private static void setPrimes() {
        boolean[] booleans = new boolean[10000];
        Arrays.fill(booleans, true);
        booleans[0] = false;
        booleans[1] = false;

        for (int i = 2; i <= 100; i++) {
            if (!booleans[i]) continue;

            for (int j = i + i; j < 10000; j += i) {
                booleans[j] = false;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        isPrime[i][j][k][l] = booleans[i * 1000 + j * 100 + k * 10 + l];
                    }
                }
            }
        }
    }
}
