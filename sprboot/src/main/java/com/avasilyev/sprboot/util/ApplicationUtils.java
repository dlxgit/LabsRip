package com.avasilyev.sprboot.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Util class for date format
 */

public class ApplicationUtils {
  /**
   * date-format for date-objects
   */
  public static final DateFormat DATE_FORMAT =
      new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);
  /**
   * date-format for database
   */
  public static final DateFormat DB_FORMAT =
      new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

  public static final String formatDate(Date date) {
    return DATE_FORMAT.format(date);
  }

  /**
   * @param date string value of date
   * @return Date object
   * @throws ParseException if parsing failed
   */
  public static final Date fromDatabase(String date) throws ParseException {
    return DB_FORMAT.parse(date);
  }
}
