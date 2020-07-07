import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Letters {

  public static final int MAX_WORD_LENGTH = 9;

  private final Words words;
  private List<String> foundWords;

  public Letters(String input, Words words) {
    this.words = words;
    foundWords = new ArrayList<>();
    input = input.toLowerCase();
    List<Character> letters = input.chars().mapToObj(l -> (char) l)
        .collect(Collectors.toList());
    findWords("", letters);
    outputWords();
  }

  private void outputWords() {
    foundWords.stream().distinct()
        .sorted(new Comparator<String>() {
          @Override
          public int compare(String o1, String o2) {
            return o1.length() - o2.length();
          }
        }).forEach(s -> System.out.println("Length: " + s.length() + " Word: " + s));
    System.out.println("No. Words: " + foundWords.stream().distinct().count());
  }

  private void findWords(String currentWord, List<Character> remainingLetters) {
    for (int i = 0; i < remainingLetters.size(); i++) {
      currentWord += remainingLetters.remove(i);
      if (words.checkWord(currentWord)) {
        foundWords.add(currentWord);
      }
      findWords(currentWord, remainingLetters);
      remainingLetters.add(i, currentWord.charAt(currentWord.length() - 1));
      currentWord = currentWord.substring(0, currentWord.length() - 1);
    }
  }
}
