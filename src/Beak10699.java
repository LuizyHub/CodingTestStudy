import java.util.Calendar;
public class Beak10699 {
    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();
        System.out.printf("%d-%02d-%02d",date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DATE));
    }
}
