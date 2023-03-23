import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak9527 {
    private static long A,B;
    private static int getBinaryLength(long num){
        int length = 0;
        while (num != 0){
            length++;
            num >>= 1;
        }
        return length;
    }
    private static long count(long num){
        if (num < 2)
            return num;
        int numlen = getBinaryLength(num);
        return (1L <<(numlen-2))*(numlen-1) + num - (1L <<(numlen-1)) + 1 + count(num - (1L <<(numlen-1)));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        System.out.println(count(B) - count(A-1));
    }
}
