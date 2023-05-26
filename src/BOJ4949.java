import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;

        loop :
        while (!(line = br.readLine()).equals(".")) {
            int size = line.length();
            if (line.charAt(size - 1) != '.') {
                sb.append("no\n");
                continue loop;
            }
            int small = 0, big = 0;
            boolean[] isSmall = new boolean[100];
            int idx = -1;
//            boolean isSmall = false;
            for (int i = 0; i < size - 1; i++) {
                char c = line.charAt(i);

                switch (c) {
                    case '(' :
                        idx++;
                        isSmall[idx] =  true;
                        small++;
                        break;
                    case '[' :
                        idx++;
                        isSmall[idx] = false;
                        big++;
                        break;
                    case ')' :
                        if (small == 0 || !isSmall[idx]) {
                            sb.append("no\n");
                            continue loop;
                        }
                        idx--;
                        small--;
                        break;
                    case ']' :
                        if (big == 0 || isSmall[idx]) {
                            sb.append("no\n");
                            continue loop;
                        }
                        idx--;
                        big--;
                        break;
                }
            }
            if (big != 0 || small != 0){
                sb.append("no\n");
            }
            else {
                sb.append("yes\n");
            }
        }

        System.out.print(sb);
    }
}