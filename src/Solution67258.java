import java.util.Arrays;
import java.util.HashMap;

public class Solution67258 {
    public int[] solution(String[] gems) {
        int[] answer = {1, gems.length};
        HashMap<String, Integer> map = new HashMap<>();

        for (String gem : gems) {
            map.put(gem, 0);
        }

        int max = map.size();

        int left = 0;
        int right = 0;

        int min = gems.length;

        map.clear();

        while (right < gems.length) {

            String curGem = gems[right];
            Integer count = map.get(curGem);
            if (count != null) {
                map.put(curGem, count + 1);
            } else {
                map.put(curGem, 1);
            }

            right++;

            while (map.size() == max) {
                curGem = gems[left++];
                count = map.get(curGem) - 1;
                if (count == 0){
                    map.remove(curGem);

                    if (right - left < min){
                        min = right - left;
                        answer[0] = left;
                        answer[1] = right;
                    }

                }
                else {
                    map.put(curGem, count);
                }
            }
        }
        return answer;
    }


    public static void main(String[] args) {
        Solution67258 s = new Solution67258();
        System.out.println(Arrays.toString(s.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
        System.out.println(Arrays.toString(s.solution(new String[]{"AA", "AB", "AC", "AA", "AC"})));
        System.out.println(Arrays.toString(s.solution(new String[]{"XYZ", "XYZ", "XYZ"})));
        System.out.println(Arrays.toString(s.solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
    }
}
