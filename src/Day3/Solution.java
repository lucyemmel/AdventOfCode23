package src.Day3;

import java.util.List;
import src.Utils.Utils;

public class Solution {

  public static void solvePartOne() {
    List<String> lines = Utils.readFileLines("src/Day3/input.txt");
    // add extra dots around schematics to make checks later easier
    char[][] schematics = new char[lines.size() + 2][lines.get(0).length() + 2];
    for (int i = 0; i < lines.get(0).length() + 1; i++) {
      schematics[0][i] = '.';
      schematics[lines.size() + 1][i] = '.';
    }
    for (int i = 0; i < lines.size() + 2; i++) {
      schematics[i][0] = '.';
      schematics[i][schematics[i].length - 1] = '.';
    }

    for (int i = 1; i < lines.size() + 1; i++) {
      for (int j = 1; j < lines.get(i - 1).length() + 1; j++) {
        schematics[i][j] = lines.get(i - 1).charAt(j - 1);
      }
    }

    int res = 0;
    boolean isValidNumber = false;
    StringBuilder currentNumber = new StringBuilder();
    for (int i = 1; i < schematics.length - 1; i++) {
      for (int j = 1; j < schematics[i].length - 1; j++) {

        if (Character.isDigit(schematics[i][j])) {
          currentNumber.append(schematics[i][j]);
          if (isValidNumber) {
            continue;
          }

          if (isSymbol(schematics[i + 1][j]) || isSymbol(schematics[i - 1][j]) || isSymbol(
              schematics[i][j + 1]) || isSymbol(schematics[i][j - 1]) || isSymbol(
              schematics[i + 1][j + 1]) || isSymbol(schematics[i - 1][j - 1]) || isSymbol(
              schematics[i + 1][j - 1]) || isSymbol(schematics[i - 1][j + 1])) {
            isValidNumber = true;
          }

        } else if (!currentNumber.isEmpty()) {
          if (isValidNumber) {
            res += Integer.parseInt(currentNumber.toString());
          }
          currentNumber.setLength(0);
          isValidNumber = false;
        }
      }
    }

    System.out.println("Result of part 1: " + res);

  }

  public static void solvePartTwo() {

  }

  private static boolean isSymbol(char c) {
    return c != '.' && !Character.isDigit(c);
  }

}
