package src.Utils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Utils {

  public static List<String> readFileLines(String filePath) {
    try {
      return Files.readAllLines(Path.of(filePath));
    } catch (IOException ex) {
      throw new RuntimeException("Error during file read: " + ex);
    }
  }
}
