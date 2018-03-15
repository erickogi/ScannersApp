package apps.kelvin.makau.scannerapp.Utills;

import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HP-HP on 19-07-2016.
 */
public class DateTimeUtils {

    public static String parseDateTime(String dateString, String originalFormat, String outputFromat) {

        SimpleDateFormat formatter = new SimpleDateFormat(originalFormat, Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);

            SimpleDateFormat dateFormat = new SimpleDateFormat(outputFromat, new Locale("US"));

            return dateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Period calcDiff(Date startDate, Date endDate) {
        DateTime START_DT = (startDate == null) ? null : new DateTime(startDate);
        DateTime END_DT = (endDate == null) ? null : new DateTime(endDate);

        Period period = new Period(START_DT, END_DT);

        return period;

    }

    public static Date conver2Date(String mydate, String formatFrom) {
        SimpleDateFormat format1 = new SimpleDateFormat(formatFrom);

        Date date = null;
        try {
            date = format1.parse(mydate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public static String getRelativeTimeSpan(String dateString, String originalFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(originalFormat, Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);

            return DateUtils.getRelativeTimeSpanString(date.getTime()).toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getToday() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public static String conver2String(Date lastModDate) {
        // String date="Mar 10, 2016 6:30:00 PM";
        SimpleDateFormat spf = new SimpleDateFormat(Constants.SDD_MM_YYYY);
        // Date newDate=spf.parse(date);
        spf = new SimpleDateFormat(Constants.HDD_MM_YYYY);
        return spf.format(lastModDate);
        // System.out.println(date);
    }
}
