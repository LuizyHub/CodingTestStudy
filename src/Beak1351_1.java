import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Beak1351_1 {
    private static int P;
    private static int Q;
    private static HashMap<Long,Long> answers = new HashMap<>();
    private static long solution(long i){
        if (answers.containsKey(i))
            return answers.get(i);
        else {
            long ans = solution(i / P) + solution(i / Q);
            answers.put(i,ans);
            return ans;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        answers.put(0l,1l);
        long N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        System.out.println(solution(N));
    }
}
