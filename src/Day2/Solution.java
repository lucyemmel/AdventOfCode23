package src.Day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import src.Utils.Utils;

public class Solution {

  public static void solvePartOne() {
    List<List<Map<Color, Integer>>> configs = new ArrayList<>();
    for (String line : Utils.readFileLines("src/Day2/input.txt")) {
      configs.add(parseConfigurationForGame(line));
    }

  for(var config: configs) {
    // search possible configs
  }
  }

  public static void solvePartTwo() {

  }

  private static List<Map<Color, Integer>> parseConfigurationForGame(String line) {
    List<Map<Color, Integer>> config = new ArrayList<>();
    String[] showings = line.split(": ")[1].split("; ");

    for (String showing : showings) {
      for (String colorAmount : showing.split(", ")) {

        //System.out.println(colorAmount.split(" ")[1] + " " + colorAmount.split(" ")[0]);

        config.add(Map.of(Color.valueOf(colorAmount.split(" ")[1]),
            Integer.valueOf(colorAmount.split(" ")[0]))
        );
      }
    }
    return config;
  }

}
