import java.util.Scanner;

//TODO make multithreaded search

public class Solver {

  public static void main(String[] args) {
    if (args.length == 0) {
      new Solver("words_alpha.txt");
    } else {
      new Solver(args[0]);
    }
  }

  private Solver(String fileName) {
    Words words = new WordSet();

    while (true) {
      String input = getInput();
      if (input.equals("Q")) {
        return;
      }
      if (input.equals("C")) {
        // Just adds breaks to the display
        for (int i = 0; i < 20; i++) {
          System.out.println();
        }
      } else if (Character.isDigit(input.charAt(0))) {
        new Numbers(input);
      } else {
        words.init(fileName);
        new Letters(input, words);
      }
    }
  }

  private static void displayInputFormat() {
    System.out.println("For numbers: n1 n2 n3 n4 n5 n6 target");
    System.out.println("For letters: abcdefghi");
    System.out.println("\"C\" for new lines");
    System.out.println("\"Q\" to stop the program running");
  }

  private static String getInput() {
    displayInputFormat();
    System.out.println("Input: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
