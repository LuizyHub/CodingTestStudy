import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ5635 {
    private static class Person {
        String name;
        int birth;

        public Person(String name, int birth) {
            this.name = name;
            this.birth = birth;
        }
    }
    private static Person[] people;
    private static char[] birthString;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        people = new Person[n];

        StringTokenizer st;
        birthString = new char[8];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();

            for (int j = 0; j < 8; j++) {
                birthString[j] = '0';
            }
            String s = st.nextToken();

            for (int j = 0; j < s.length(); j++) {
                birthString[7 - s.length() + 1 + j] = s.charAt(j);
            }

            s = st.nextToken();

            for (int j = 0; j < s.length(); j++) {
                birthString[5 - s.length() + 1 + j] = s.charAt(j);
            }

            s = st.nextToken();

            for (int j = 0; j < s.length(); j++) {
                birthString[3 - s.length() + 1 + j] = s.charAt(j);
            }

            people[i] = new Person(name, Integer.parseInt(new String(birthString)));
        }

        Arrays.sort(people, (p1, p2) -> Integer.compare(p1.birth, p2.birth));

        System.out.print(new StringBuilder().append(people[n-1].name).append('\n').append(people[0].name));
    }
}
