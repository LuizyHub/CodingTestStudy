public class Ain {
    public static void main(String[] args) {
        int i = 0b10001000;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i & -i));
        System.out.println((int) '0');
        System.out.println(-5 / 2);
        System.out.println(-5 % 2);
        System.out.println(-11 / 4);
        System.out.println(-11 % 4);
    }
}
