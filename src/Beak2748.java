import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak2748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long pre = 0, nxt = 1, cnt = 0;
        for (int i = 1; i < N; i++) {
            cnt = pre + nxt;
            pre = nxt;
            nxt = cnt;
        }
        System.out.println(cnt);
    }
}
