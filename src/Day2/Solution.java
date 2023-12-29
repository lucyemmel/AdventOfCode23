package src.Day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import src.Utils.Utils;

public class Solution {

  public static void solvePartOne() {
    Map<Color, Integer> totalCubes = Map.of(Color.red, 12, Color.green, 13, Color.blue, 14);
    List<List<Map<Color, Integer>>> configs = new ArrayList<>();
    for (String line : Utils.readFileLines("src/Day2/input.txt")) {
      configs.add(parseConfigurationForGame(line));
    }

    int possibleGamesIdSum = 0;
    for (int i = 0; i < configs.size(); i++) {
      // search possible configs
      var gameConfig = configs.get(i);
      boolean isGamePossible = true;
      outerGameLoop:
      for (var observedCubes : gameConfig) {
        for (var cubeShowing : observedCubes.entrySet()) {
          Integer currentCubeForColor = cubeShowing.getValue();
          if (currentCubeForColor > totalCubes.get(cubeShowing.getKey())) {
            isGamePossible = false;
            break outerGameLoop;
          }
        }
      }

      if (isGamePossible) {
        possibleGamesIdSum += i + 1;
      }
    }

    System.out.println("Result of part 1: " + possibleGamesIdSum);
  }

  public static void solvePartTwo() {
    List<List<Map<Color, Integer>>> configs = new ArrayList<>();
    for (String line : Utils.readFileLines("src/Day2/input.txt")) {
      configs.add(parseConfigurationForGame(line));
    }

    int res = 0;

    for (var gameConfig : configs) {
      Map<Color, Integer> maxCubesPerGame = new HashMap<>(
          Map.of(Color.blue, 0, Color.red, 0, Color.green, 0));
      for (var observedCubes : gameConfig) {
        for (var cubeShowing : observedCubes.entrySet()) {
          if (maxCubesPerGame.get(cubeShowing.getKey()) < cubeShowing.getValue()) {
            maxCubesPerGame.put(cubeShowing.getKey(), cubeShowing.getValue());
          }
        }
      }

      res += maxCubesPerGame.values().stream().reduce(1 , (currentSum, currentElem) -> currentSum * currentElem);
    }

    System.out.println("Result of part 2: " + res);
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
