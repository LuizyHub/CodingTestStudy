import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[] yos = new boolean[n];
        int index = k-1;
        yos[index] = true;
        sb.append("<").append(k);
        for (int i = 1; i <n; i++) {
            for (int j = 0; j < k; j++) {
                do {
                    index++;
                    if (index==n)
                        index = 0;
                }while (yos[index]);
            }
            sb.append(", ").append(index+1);
            yos[index] = true;
        }
        sb.append(">");
        System.out.println(sb);
    }
}
