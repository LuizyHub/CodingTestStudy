import java.util.*;

public class Programmers42586 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(s.solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }
    static class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            int min = getLen(progresses[0],speeds[0]);
            ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
            int count = 1;
            for (int i = 1; i < progresses.length; i++) {
                int len = getLen(progresses[i], speeds[i]);

                if (len<=min)
                    count++;
                else {
                    arrayDeque.addLast(count);
                    min = len;
                    count = 1;
                }
            }
            arrayDeque.addLast(count);

            return arrayDeque.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }
        int getLen(int progress, int speed){
            if(speed!=1)
                return (100 - progress + 1) / speed + 1;
            else
                return 100 - progress;
        }
    }
}
