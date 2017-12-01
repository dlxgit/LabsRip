package util;

import com.sun.jna.platform.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * logger class
 */
public class FileEventLogger {
  public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  public static final String fileName = "logs.txt";

  /**
   * @param message text to be logged
   */
  public static void logEvent(String message) {
    File file = new File(fileName);
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    file.setWritable(true);

    try {
      Files.write(Paths.get(fileName),
        (dateFormat.format(new Date()) + " : " + message + "\n").getBytes(), StandardOpenOption.APPEND);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
