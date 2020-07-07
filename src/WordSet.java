import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class WordSet implements Words {

  private Set<String> words;
  private boolean initialised = false;

  @Override
  public void init(String fileName) {
    if (initialised) {
      return;
    }

    words = new HashSet<>();
    try {
      Files.lines(new File(fileName).toPath())
          .map(s -> s.trim())
          .filter(s -> s.length() <= Letters.MAX_WORD_LENGTH)
          .forEach(s -> words.add(s));
    } catch (IOException e) {
      e.printStackTrace();
    }
    initialised = true;
  }

  @Override
  public boolean checkWord(String word) {
    return words.contains(word);
  }
}
