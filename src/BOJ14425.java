import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ14425 {
    private static int N, M, count;
    private static HashSet<String> hashSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        hashSet = new HashSet<>(N);

        for (int i = 0; i < N; i++)
            hashSet.add(br.readLine());

        for (int i = 0; i < M; i++)
            if (hashSet.contains(br.readLine()))
                count++;

        System.out.print(count);
    }
}
