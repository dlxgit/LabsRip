package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ApplicationUtils {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);
    public static final DateFormat DB_FORMAT = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

    public static final String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static final Date fromDatabase(String date) throws ParseException {
        return DB_FORMAT.parse(date);
    }
}
