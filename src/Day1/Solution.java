package src.Day1;

import java.util.Map;
import src.Utils.Utils;

public class Solution {

  public static void solvePartOne() {
    int res = 0;
    for (String line : Utils.readFileLines("src/Day1/input.txt")) {
      res += getFirstAndLastNumberConcatenatedFromLine(line);
    }
    System.out.println("Solution for part 1: " + res);

  }

  public static void solvePartTwo() {
    int res = 0;
    for (String line : Utils.readFileLines("src/Day1/input.txt")) {
      res += getFirstAndLastNumberConcatenatedFromLineWithExtraParsing(line);
    }
    System.out.println("Solution for part 2: " + res);

  }

  private static int getFirstAndLastNumberConcatenatedFromLine(String line) {
    int firstNum = 0;
    int secondNum = 0;
    for (int i = 0; i < line.length(); i++) {
      char current = line.charAt(i);
      // ascii range check for 0 to 9
      if (current >= 48 && current <= 57) {
        firstNum = Character.getNumericValue(current);
        break;
      }
    }

    for (int i = line.length() - 1; i >= 0; i--) {
      char current = line.charAt(i);
      // ascii range check for 0 to 9
      if (current >= 48 && current <= 57) {
        secondNum = Character.getNumericValue(current);
        break;
      }
    }
    return 10 * firstNum + secondNum;
  }

  private static int getFirstAndLastNumberConcatenatedFromLineWithExtraParsing(String line) {
    int firstNum = 0;
    int secondNum = 0;
    Map<String, Integer> numbers = Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5,
        "six", 6, "seven", 7, "eight", 8, "nine", 9);

    String currentReadString = "";
    for (int i = 0; i < line.length(); i++) {
      char current = line.charAt(i);
      // ascii range check for 0 to 9
      if (current >= 48 && current <= 57) {
        firstNum = Character.getNumericValue(current);
        break;
      }

      currentReadString += current;
      String finalCurrentReadString = currentReadString;
      if (numbers.keySet().stream().anyMatch(finalCurrentReadString::contains)) {
        firstNum = numbers.entrySet().stream()
            .filter(numberMapping -> finalCurrentReadString.contains(numberMapping.getKey()))
            .findFirst().get().getValue();
        break;
      }
    }

    for (int i = line.length() - 1; i >= 0; i--) {
      char current = line.charAt(i);
      // ascii range check for 0 to 9
      if (current >= 48 && current <= 57) {
        secondNum = Character.getNumericValue(current);
        break;
      }

      currentReadString += current;
      String finalCurrentReadStringCorrectedOrder = new StringBuilder(currentReadString).reverse()
          .toString();
      if (numbers.keySet().stream().anyMatch(finalCurrentReadStringCorrectedOrder::contains)) {
        secondNum = numbers.entrySet().stream()
            .filter(numberMapping -> finalCurrentReadStringCorrectedOrder.contains(
                numberMapping.getKey()))
            .findFirst().get().getValue();
        break;
      }
    }
    System.out.println("Current line: " + line);
    System.out.println("Result for current line: " + (10 * firstNum + secondNum));
    System.out.println();
    return 10 * firstNum + secondNum;
  }
}
