import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak5430 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        testcase:
        for (int i = 0; i < T; i++) {
            boolean isReversed = false;
            String func = br.readLine();
            int N = Integer.parseInt(br.readLine());
            ArrayDeque<Integer> arr = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine(),",[]");
            for (int j = 0; j < N; j++) {
                arr.addLast(Integer.parseInt(st.nextToken()));
            }
            for (int j = 0; j < func.length(); j++) {
                char c = func.charAt(j);
                switch (c){
                    case 'R':
                        isReversed = !isReversed;
                        break;
                    case 'D':
                        if (isReversed){
                            if (arr.pollLast() == null){
                                sb.append("error\n");
                                continue testcase;
                            }
                        }
                        else {
                            if (arr.pollFirst() == null){
                                sb.append("error\n");
                                continue testcase;
                            }
                        }
                        break;
                }
            }
            if (arr.isEmpty()){
                sb.append("[]\n");
                continue testcase;
            }
            if (isReversed){
                sb.append('[').append(arr.pollLast());
                while (!arr.isEmpty()){
                    sb.append(',').append(arr.pollLast());
                }
                sb.append("]\n");
            }
            else {
                sb.append('[').append(arr.pollFirst());
                while (!arr.isEmpty()){
                    sb.append(',').append(arr.pollFirst());
                }
                sb.append("]\n");
            }
        }
        System.out.println(sb);
    }
}
