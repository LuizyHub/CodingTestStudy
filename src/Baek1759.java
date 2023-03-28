import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1759 {
    private static int N, M;
    private static char[] answer, chars;
    private static StringBuilder sb = new StringBuilder();

    private static void dfs(int idx, int len){
        if (len == N){
            int v = 0, n = 0;
            for (char c : answer) {
                switch (c){
                    case 'a': case 'e': case 'i': case 'o': case 'u':
                        v++;
                        break;
                    default :
                        n++;
                }
                if (v >= 1 && n >=2){
                    sb.append(answer).append('\n');
                    return;
                }
            }
            return;
        }
        for (int i = idx; i < M; i++) {
            answer[len] = chars[i];
            dfs(i + 1, len + 1);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        answer = new char[N];
        M = Integer.parseInt(st.nextToken());
        chars = new char[M];
        String s = br.readLine();
        for (int i = 0; i < M*2; i+=2) {
            chars[i/2] = s.charAt(i);
        }
        Arrays.sort(chars);
        dfs(0,0);
        System.out.println(sb);
    }
}
