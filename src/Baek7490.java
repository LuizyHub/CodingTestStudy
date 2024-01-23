import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Baek7490 {
    private static final int MAX_N = 9;
    private static int T;
    private static StringBuilder sb;
    private static ArrayList<Map<Integer, ArrayList<String>>> expressions;
    private static final ArrayList<String> LIST_ONE = new ArrayList<>();
    private static final ArrayList<String> LIST_EMPTY = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        expressions = new ArrayList<>(MAX_N + 1);
        for (int i = 0; i <= MAX_N; i++) {
            expressions.add(new HashMap<>());
        }
        LIST_ONE.add("1");

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            for (String s : getExpressions(N, 0)) {
                sb.append(s).append('\n');
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

    private static ArrayList<String> getExpressions(int N, int M) {
        if (N <= 1) {
            if (N == 1 &&M == 1) {
                return LIST_ONE;
            }
            else {
                return LIST_EMPTY;
            }
        }

        ArrayList<String> list = expressions.get(N).get(M);
        if (list != null) {
            return list;
        }

        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            int target = 0;
            for (int j = 0; j <= i; j++) {
                target += (N - j) * (int)Math.pow(10, j);
            }
            StringBuilder targetSb = new StringBuilder();
            for (char c : Integer.toString(target).toCharArray()) {
                targetSb.append(c).append(' ');
            }
            String targetStr = targetSb.toString().trim();

            if (i == N) {
                if (target == M) {
                    list.add(targetStr);
                }
                break;
            }

            for (String s : getExpressions(N - 1 - i, M - target)) {
                list.add(s + '+' + targetStr);
            }
            for (String s : getExpressions(N - 1 - i, M + target)) {
                list.add(s + '-' + targetStr);
            }
        }

        list.sort(String::compareTo);
        expressions.get(N).put(M, list);
        return list;
    }
}
