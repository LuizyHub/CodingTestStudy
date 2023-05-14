import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Baek1181 {
    private static int N;
    private static String[] words;
    private static HashSet<String> wordsMap;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        wordsMap = new HashSet<>();
        for (int i = 0; i < N; i++) {
            wordsMap.add(br.readLine());
        }
        words = wordsMap.toArray(String[]::new);

        Arrays.sort(words,
                (o1, o2) -> Integer.compare(o1.length(), o2.length()) == 0 ?
                        o1.compareTo(o2) : Integer.compare(o1.length(), o2.length()));

        for (String word : words) {
            sb.append(word).append('\n');
        }

        System.out.print(sb);
    }
}