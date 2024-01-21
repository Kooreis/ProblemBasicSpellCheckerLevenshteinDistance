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