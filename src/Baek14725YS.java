import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Baek14725YS {

    public static int n;
    public static StringBuilder sb = new StringBuilder();

    static class TrieNode {
        public Map<String, TrieNode> childNodes = new TreeMap<>();
        public boolean isLast;
    }

    static class Trie {
        public TrieNode rootNode;

        Trie() {
            rootNode = new TrieNode();
        }

        void insert(ArrayList<String> words) {
            TrieNode node = this.rootNode;

            for (String word : words) {
                node = node.childNodes.computeIfAbsent(word, c -> new TrieNode());
            }
            node.isLast = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            ArrayList<String> words = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                words.add(st.nextToken());
            }
            trie.insert(words);
        }

        TrieNode node = trie.rootNode;
        for (String key : node.childNodes.keySet()) {
            sb.append(key).append("\n");
            print(node.childNodes.get(key), 1);
        }

        System.out.println(sb);
    }

    public static void print(TrieNode cur, int depth) {
        for (String key : cur.childNodes.keySet()) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(key).append("\n");
            print(cur.childNodes.get(key), depth + 1);
        }
    }
}
