public class Baek1152 {
    public static void main(String[] a) {
        String s = new java.util.Scanner(System.in).nextLine().trim();
        if (s.equals(""))
            System.out.print(0);
        else
            System.out.print(s.split(" ").length);
    }
}