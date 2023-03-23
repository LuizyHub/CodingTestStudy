import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak17202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int[] arr = new int[17];
        for (int i = 0; i < 8; i++) {
            arr[i*2] = A.charAt(i) - '0';
            arr[i*2+1] = B.charAt(i) - '0';
        }
        for (int i = 15; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                arr[j] = (arr[j] + arr[j+1]) % 10;
            }
        }
        System.out.printf("%02d",arr[0]*10 + arr[1]);
    }
}
