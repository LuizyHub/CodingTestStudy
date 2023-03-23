import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Beak17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        Stack<Integer> NGE = new Stack<>(); // index
        NGE.push(0);
        for (int i = 1; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i-1] < arr[i]){
                while (!NGE.isEmpty() && arr[NGE.peek()] < arr[i]){
                    arr[NGE.pop()] = arr[i];
                }
            }
            NGE.push(i);
        }
        while (!NGE.isEmpty()){
            arr[NGE.pop()] = -1;
        }
        bw.write(Integer.toString(arr[0]));
        for (int i = 1; i < N; i++) {
            bw.write(" " + arr[i]);
        }
        bw.flush();
    }
}
