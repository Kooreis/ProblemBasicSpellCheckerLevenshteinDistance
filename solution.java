Here is a simple console application in Java that implements a basic spell checker using Levenshtein distance:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word:");
        String word = scanner.nextLine();
        System.out.println("Enter dictionary words:");
        String line = scanner.nextLine();
        String[] dictionary = line.split(" ");
        SpellChecker spellChecker = new SpellChecker(dictionary);
        System.out.println("Suggestions: " + spellChecker.suggest(word));
    }
}

class SpellChecker {
    private String[] dictionary;

    public SpellChecker(String[] dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> suggest(String word) {
        List<String> suggestions = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        for (String dictWord : dictionary) {
            int distance = levenshteinDistance(word, dictWord);
            if (distance < minDistance) {
                minDistance = distance;
                suggestions.clear();
                suggestions.add(dictWord);
            } else if (distance == minDistance) {
                suggestions.add(dictWord);
            }
        }
        return suggestions;
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)), dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    private int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}
```

This program first takes a word from the user, then takes a line of text as the dictionary. It splits the line into words and uses them as the dictionary for the spell checker. The spell checker suggests corrections based on the Levenshtein distance between the input word and the words in the dictionary. The Levenshtein distance is the minimum number of single-character edits (insertions, deletions or substitutions) required to change one word into the other.