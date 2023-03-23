import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1991 {
    private static int N;
    private static int[][] tree;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new int[N][2];

        for (int i = 0; i < N; i++) {// -19 == '.' - 'A'
            StringTokenizer st = new StringTokenizer(br.readLine());
            int root = st.nextToken().charAt(0) - 'A';
            int left = st.nextToken().charAt(0) - 'A';
            int right = st.nextToken().charAt(0) - 'A';
            tree[root][0] = left;
            tree[root][1] = right;
        }
        preorder(0);
        sb.append('\n');
        inorder(0);
        sb.append('\n');
        postorder(0);

        System.out.println(sb);
    }

    private static void postorder(int i) {
        if (i == -19)
            return;
        postorder(tree[i][0]);
        postorder(tree[i][1]);
        sb.append((char)(i+'A'));

    }

    private static void inorder(int i) {
        if (i == -19)
            return;
        inorder(tree[i][0]);
        sb.append((char)(i+'A'));
        inorder(tree[i][1]);
    }

    private static void preorder(int i) {
        if (i == -19)
            return;
        sb.append((char)(i+'A'));
        preorder(tree[i][0]);
        preorder(tree[i][1]);
    }
}
