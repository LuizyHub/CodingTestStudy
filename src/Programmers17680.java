import java.util.ArrayDeque;

public class Programmers17680 {
    public static void main(String[] args) {
        System.out.println(new Programmers17680.Solution().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(new Programmers17680.Solution().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
        System.out.println(new Programmers17680.Solution().solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
    }

    static class Solution {
        public int solution(int cacheSize, String[] cities) {
            int answer = 0;
            ArrayDeque<String> caches = new ArrayDeque<>();
            for (String str : cities) {
                str = str.toLowerCase();
                if (caches.contains(str)){
                    answer += 1;
                    caches.addLast(str);
                    if (caches.size() > cacheSize)
                        caches.remove(str);
                }
                else {
                    answer += 5;
                    caches.addLast(str);
                    if (caches.size() > cacheSize)
                        caches.removeFirst();
                }
            }
            return answer;
        }
    }
}
