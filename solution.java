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