import java.text.ParseException;
import java.text.SimpleDateFormat;

public class test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf2.format(sdf.parse("22082018")));
    }
}
