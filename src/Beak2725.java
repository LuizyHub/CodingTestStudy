import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak2725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        for (int i = 0; i < c; i++) {
            int k = Integer.parseInt(br.readLine());
            boolean[][] point = new boolean[k+1][];
            for (int j = 1; j <= k; j++) {
                point[j] = new boolean[j];
            }
            for (int j = 1; j < k; j++) {
                for (int l = j + 1; l <= k; l++) {
                    if (point[l][j])
                        continue;
                    for (int m = j*2, n = l*2; n <= k; m+=j, n+=l) {
                        point[n][m] = true;
                    }
                }
            }
            int ans = 0;
            for (int j = 1; j < k; j++) {
                for (int l = j+1; l <= k; l++) {
                    if (!point[l][j])
                        ans++;
                }
            }
            System.out.println(ans*2 +3);
        }
    }
}
