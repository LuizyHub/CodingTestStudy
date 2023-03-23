import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Beak11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        int n;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(1);
            switch (c){
                case 'd': //add
                    n = Integer.parseInt(st.nextToken());
                    set.add(n);
                    break;
                case 'e': //remove
                    n = Integer.parseInt(st.nextToken());
                    set.remove(n);
                    break;
                case 'h': //check
                    n = Integer.parseInt(st.nextToken());
                    if (set.contains(n))
                        sb.append(1).append('\n');
                    else
                        sb.append(0).append('\n');
                    break;
                case 'o': //toggle
                    n = Integer.parseInt(st.nextToken());
                    if (set.contains(n))
                        set.remove(n);
                    else
                        set.add(n);
                    break;
                case 'l': //all
                    for (int j = 1; j <= 20; j++)
                        set.add(j);
                    break;
                case 'm': //empty
                    set.clear();
                    break;
            }
        }
        System.out.println(sb);
    }
}
