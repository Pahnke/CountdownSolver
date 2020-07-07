import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Numbers {

  public static final int RADIUS = 10;

  private List<Path> foundPaths;

  public Numbers(String input) {
    List<Integer> numbers = new ArrayList<>();
    int target = processInput(input, numbers);
    foundPaths = new ArrayList<>();
    List<PathNode> currentPath = new ArrayList<>();

    findTarget(numbers, target, currentPath);
    processAndDisplayPaths(target);
  }

  private void processAndDisplayPaths(int target) {
    // Counts the No. paths and outputs tally
    // And 1 example of the method

    int possibleResults = 2 * RADIUS + 1;
    int[] firstMethod = new int[possibleResults];
    int[] count = new int[possibleResults];
    for (int i = 0; i < foundPaths.size(); i++) {
      int position = foundPaths.get(i).getResult() - target + RADIUS;
      if (count[position] == 0) {
        firstMethod[position] = i;
      }
      count[position]++;
    }

    for (int i = 0; i < RADIUS; i++) {
      outputIfNotEmpty(i, count, firstMethod, target);
      outputIfNotEmpty(possibleResults - 1 - i, count, firstMethod, target);
    }
    outputIfNotEmpty(RADIUS, count, firstMethod, target);

  }

  private void outputIfNotEmpty(int i, int[] count, int[] firstMethod, int target) {
    if (count[i] != 0) {
      outputPathStats(count[i], target, foundPaths.get(firstMethod[i]));
    }
  }

  private void outputPathStats(int count, int target, Path path) {
    System.out.println();
    System.out.println("Distance to Target: " + path.distFromTarget(target));
    System.out.println("Result: " + path.getResult());
    System.out.println("No. Methods: " + count);
    System.out.println("Example Method: ");
    System.out.println(path);
    System.out.println();
  }

  // Now redundant
  /*
  private List<Path> sortPaths(List<Path> paths, int target) {
    // Sorts paths based of distance to target
    return paths.stream().sorted(
        Comparator.comparing(a -> a.invertedDistFromTarget(target)))
        .collect(Collectors.toList());
  }
  */

  private void findTarget(List<Integer> numbers, int target, List<PathNode> currentPath) {

    BinOp[] binOps = BinOps.generateBinOps();

    for (int i = 0; i < numbers.size(); i++) {
      Integer a = numbers.remove(i);

      for (int j = 0; j < numbers.size(); j++) {
        Integer b = numbers.remove(j);

        for (int k = 0; k < binOps.length; k++) {
          Integer newNum = binOps[k].apply(a, b);
          // Using 0 won't get closer to the goal
          // So 0 is used if a/b is not an int
          if (newNum == 0) {
            continue;
          }

          // Stores route to result
          currentPath.add(new PathNode(a, b, newNum, binOps[k]));
          checkNumber(newNum, target, currentPath);

          // Continues recursive search
          numbers.add(newNum);
          findTarget(numbers, target, currentPath);

          // Removing recently added values
          numbers.remove(newNum);
          currentPath.remove(currentPath.size() - 1);
        }
        numbers.add(j, b);
      }
      numbers.add(i, a);
    }
  }

  private void checkNumber(Integer attempt, int target, List<PathNode> currentPath) {
    if (Math.abs(attempt - target) <= RADIUS) {
      foundPaths.add(new Path(new ArrayList<>(currentPath)));
    }

  }

  // Assumes List is non-null
  // Returns the target value
  private int processInput(String input, List<Integer> numbers) {
    Arrays.stream(input.split("[\\s]")).mapToInt(Integer::parseInt).forEach(numbers::add);
    return numbers.remove(numbers.size() - 1);
  }
}
