```C#
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

public class SpellChecker
{
    private readonly List<string> _dictionary;

    public SpellChecker(List<string> dictionary)
    {
        _dictionary = dictionary;
    }

    public List<string> GetSuggestions(string word)
    {
        return _dictionary.OrderBy(x => LevenshteinDistance(x, word)).Take(5).ToList();
    }

    private int LevenshteinDistance(string a, string b)
    {
        var matrix = new int[a.Length + 1, b.Length + 1];

        for (var i = 0; i <= a.Length; i++)
        {
            matrix[i, 0] = i;
        }

        for (var j = 0; j <= b.Length; j++)
        {
            matrix[0, j] = j;
        }

        for (var i = 1; i <= a.Length; i++)
        {
            for (var j = 1; j <= b.Length; j++)
            {
                var cost = (a[i - 1] == b[j - 1]) ? 0 : 1;

                matrix[i, j] = Math.Min(Math.Min(matrix[i - 1, j] + 1, matrix[i, j - 1] + 1), matrix[i - 1, j - 1] + cost);
            }
        }

        return matrix[a.Length, b.Length];
    }
}
```