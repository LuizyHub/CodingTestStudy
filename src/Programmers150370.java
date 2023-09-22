import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/150370
 */
public class Programmers150370 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        int[] answer = solution.solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
        System.out.println(Arrays.toString(answer));
    }
    static class Solution {
        int dateToInt(String date) {
            int num = Integer.parseInt(date.replace('.', '0'));
            int year = num / 1000000;
            num -= year * 1000000;
            num += year * 12 * 1000;
            return num;
        }
        public int[] solution(String today, String[] terms, String[] privacies) {
            ArrayList<Integer> answerList = new ArrayList<>();
            int todayInt = dateToInt(today);
            int[] termList = new int['Z' - 'A' + 1];
            for (String term : terms) {
                StringTokenizer st = new StringTokenizer(term);
                termList[st.nextToken().charAt(0) - 'A'] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < privacies.length; i++) {
                String privacy = privacies[i];
                String[] strings = privacy.split(" ");
                int day = dateToInt(strings[0]) + termList[strings[1].charAt(0) - 'A'] * 1000;

                if (todayInt - day >= 0) {
                    answerList.add(i + 1);
                }
            }

            return answerList.stream().mapToInt(integer -> integer).toArray();
        }
    }
}
