import java.io.IOException;

public class Ain {
    public static void main(String[] args) throws IOException {
        String s = "[{'id': 18, 'name': 'Drama'}, {'id': 14, 'name': 'Fantasy'}, {'id': 878, 'name': 'Science Fiction'}, {'id': 53, 'name': 'Thriller'}]";

        int idx = 0;
        while ((idx = s.indexOf("'name': ", idx)) != -1) {

            System.out.println(s.charAt(idx + 6));
            int from = s.indexOf('\'' , idx + 6) + 1;
            int to = s.indexOf('\'' , from);

            System.out.println("from = " + from);
            System.out.println("to = " + to);

            System.out.println(s.substring(from, to));
            System.out.println(s.charAt(idx));
            idx = to;
            System.out.println(idx);
        }
    }
}
