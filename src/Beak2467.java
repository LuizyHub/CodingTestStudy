import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Beak2467 {
    private static int N, minMinus, minPlus, minSum = Integer.MAX_VALUE;
    private static ArrayList<Integer> minus, plus;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int i = 0, num = 0;
        minus = new ArrayList<>();
        for (i = 0; i < N; i++) {
            num = Integer.parseInt(st.nextToken());
            if (num > 0)
                break;
            minus.add(num);
        }

        plus = new ArrayList<>();
        if (i < N)
            plus.add(num);
        i++;
        for (; i < N; i++) {
            num = Integer.parseInt(st.nextToken());
            plus.add(num);
        }

        if (minus.size() < plus.size()){
            for (Integer minusNum : minus) {
                int idx = Collections.binarySearch(plus, -minusNum);
                if (idx >= 0){
                    minMinus = minusNum;
                    minPlus = plus.get(idx);
                    minSum = 0;
                    break;
                }
                else {
                    int plusNum;
                    if (Math.abs(minusNum + plus.get(Math.min(-idx-1,plus.size()-1))) < Math.abs(minusNum + plus.get(Math.max(-idx-2,0))))
                        plusNum = plus.get(Math.min(-idx-1,plus.size()-1));
                    else
                        plusNum = plus.get(Math.max(-idx-2,0));
                    int sum = Math.abs(minusNum + plusNum);
                    if (sum < minSum){
                        minSum = sum;
                        minMinus = minusNum;
                        minPlus = plusNum;
                    }
                }
            }
        }
        else {
            for (Integer plusNum : plus) {
                int idx = Collections.binarySearch(minus, -plusNum);
                if (idx >= 0){
                    minMinus = minus.get(idx);
                    minPlus = plusNum;
                    minSum = 0;
                    break;
                }
                else {
                    int minusNum;
                    if (Math.abs(plusNum + minus.get(Math.min(-idx-1,minus.size()-1))) < Math.abs (plusNum + minus.get(Math.max(-idx-2,0))))
                        minusNum = minus.get(Math.min(-idx-1,minus.size()-1));
                    else
                        minusNum = minus.get(Math.max(-idx-2,0));
                    int sum = Math.abs(minusNum + plusNum);
                    if (sum < minSum){
                        minSum = sum;
                        minMinus = minusNum;
                        minPlus = plusNum;
                    }
                }
            }
        }
        if (minSum > 3){
            if (minus.size() > 1){
                int size = minus.size();
                if ((minus.get(size-1) + minus.get(size-2)) > -minSum){
                    minMinus = minus.get(size-2);
                    minPlus = minus.get(size-1);
                    minSum = -minMinus - minPlus;
                }
            }
            if (plus.size() > 1){
                if ((plus.get(0) + plus.get(1)) < minSum){
                    minMinus = plus.get(0);
                    minPlus = plus.get(1);
                }
            }
        }

        sb.append(minMinus).append(' ').append(minPlus);

        System.out.println(sb);
    }
}
