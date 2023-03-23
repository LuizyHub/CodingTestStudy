import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1208 {
    private static int N, S;
    private static long count = 0;


    private static ArrayList<Long> getAvailList(ArrayList<Long> list, int start, long beforesum, long size){
        if (size == 1){
            return new ArrayList<Long>();
        }
        ArrayList<Long> returnList = new ArrayList<>();
        for (int i = start; i < list.size(); i++) {
            long num = list.get(i) + beforesum;
            if (num < 0)
                returnList.add(num*100 - size + 1);
            else
                returnList.add(num*100 + size - 1);
            returnList.addAll(getAvailList(list, i+1, num,size -1));
        }
        return returnList;
    }
    private static long com(long a, long b){
        b = Math.min(b, a - b);
        if (a >= 30)
            if (b > 0)
                return com(a-1, b) + com(a-1, b-1);
            else
                return com(a-1, b);
        long ans = 1;
        for (int i = 0; i < b; i++) {
            ans *= (a - i);
        }
        for (int i = 1; i <= b; i++) {
            ans /= i;
        }
        return ans;
    }
    private static ArrayList<Long> arr;

    private static void solution(long size, long target, ArrayList<Long> list){


        if (list.get(0) == list.get(list.size() - 1) && list.get(0)*size == target){
            count += com(list.size(),size);
            return;
        }
        if (size == 1){
            if (Collections.binarySearch(list, (long)target) >= 0)
                count++;
            return;
        }

        double mean = 1.0 *target / size;
        ArrayList<Long> small = new ArrayList<>();
        ArrayList<Long> big = new ArrayList<>();
        for (Long l : list) {
            if (l > mean)
                big.add(l);
            else
                small.add(l);
        }
        if (big.size() == 0 || small.size() == 0)
            return;
        if (big.size() < small.size()){
            for (Long l : getAvailList(big, 0, 0, size)) {
                if (Math.abs(l%100) <= small.size())
                    solution(Math.abs(l%100), target - l/100, small);
            }
        }
        else {
            for (Long l : getAvailList(small, 0, 0, size)) {
                if (Math.abs(l%100) <= big.size())
                    solution(Math.abs(l%100), target - l/100, big);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr.add(Long.parseLong(st.nextToken()));
        }
        Collections.sort(arr);
//        System.out.println(arr);
//        System.out.println(getAvailList(arr, 0, 0, 5));
//        for (Long aLong : getAvailList(arr,0,0,5)) {
//            System.out.println(aLong%100 + " " + aLong/100);
//        }
//        System.out.println(-696%100);
//        System.out.println((-696)/100);

        for (int i = 1; i <= N; i++) {
            solution(i, S, arr);
        }


        System.out.print(count);
    }
}

/*
5 0
0 1 2 4 8

 */

/*
0 5 4
8 5
-8 3 2
10 53
-10 1 0
10 531
-8 3 1
11 532
-8 3 0
11 5321
0 5 3
12 54
-12 2 1
13 542
-12 2 0
13 5421
0 5 2
14 543
-14 1 0
14 5431
0 5 1
15 5432
0 5 0
15 54321
 */