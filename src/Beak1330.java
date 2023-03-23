import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1330 {
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int a = Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken());
        if(a > 0)
            System.out.print(">");
        else if (a == 0)
            System.out.print("==");
        else
            System.out.print("<");
    }
}
