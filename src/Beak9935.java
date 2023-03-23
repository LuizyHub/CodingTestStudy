import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class Beak9935 {
    private static class Node{
        int idx;
        char c;

        public Node(int idx, char c) {
            this.idx = idx;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());
        StringBuilder ans = new StringBuilder();
//        String bomb = br.readLine();
        char[] bomb = br.readLine().toCharArray();
        HashSet<Character> bombChars = new HashSet<>();
        for (char c : bomb) {
            bombChars.add(c);
        }
        int bombSize = bomb.length;
        if (bombSize == 1){
            char c = bomb[0];
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) != c)
                    ans.append(sb.charAt(i));
            }
            if (ans.length() == 0)
                System.out.println("FRULA");
            else
                System.out.println(ans);
            System.exit(0);
        }
        ArrayDeque<Node> bombCheck = new ArrayDeque<>();
        ArrayList<Integer> removeList = new ArrayList<>();
        int idx = bombSize;
        for (int i = sb.length()-1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (!bombChars.contains(c)){
                bombCheck.clear();
                continue;
            }
            if (c == bomb[bombSize-1]) {
                bombCheck.addLast(new Node(i, c));
                idx = bombSize-2;
            }
            else if (!bombCheck.isEmpty() && c == bomb[idx]) {
                if (c == bomb[0]){
                    removeList.add(i);
                    for (int j = 1; j < bombSize; j++) {
                        bombCheck.pollLast();
                    }
                }
                else{
                    idx--;
                    bombCheck.addLast(new Node(i,c));
                }
            }
            else
                bombCheck.clear();
        }
        idx = 0;
        removeList.sort((o1, o2) -> Integer.compare(o1, o2));
        removeList.add(sb.length() + bombSize);
        System.out.println(removeList);
        char c;
        for (int i = 0; i < sb.length(); i++) {
            if (removeList.get(idx) == i){
                idx++;
                i += bombSize-1;
                while (removeList.get(idx) <= i){
                    i += bombSize;
                    idx++;
                }
            }
            else{
                if (i < sb.length())
                    ans.append(sb.charAt(i));
            }
        }
        if (ans.length() == 0)
            System.out.println("FRULA");
        else
            System.out.println(ans);
    }
}
