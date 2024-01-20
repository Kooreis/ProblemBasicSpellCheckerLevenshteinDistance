using System;
using System.Collections.Generic;
using System.Linq;

public class Program
{
    static void Main(string[] args)
    {
        var dictionary = new List<string> { "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew" };
        var spellChecker = new SpellChecker(dictionary);

        Console.Write("Enter a word: ");
        var word = Console.ReadLine();

        var suggestions = spellChecker.GetSuggestions(word);

        Console.WriteLine("Did you mean:");
        foreach (var suggestion in suggestions)
        {
            Console.WriteLine(suggestion);
        }
    }
}