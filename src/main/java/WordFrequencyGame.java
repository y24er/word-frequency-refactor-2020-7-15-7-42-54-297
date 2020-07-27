import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String BLANK_SPACE = " ";

    public String getResult(String sentence) {


        if (sentence.split(SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfos = calculateWordFrequency(sentence);
                wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - secondWordInfo.getWordCount());
                return generateWordFrequencyResult(wordInfos);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private String generateWordFrequencyResult(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordInfo wordInfo : wordInfos) {
            String wordInfoSentence = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
            joiner.add(wordInfoSentence);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfos) {
        Map<String, List<WordInfo>> wordMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfos) {
            if (!wordMap.containsKey(wordInfo.getValue())) {
                ArrayList words = new ArrayList<>();
                words.add(wordInfo);
                wordMap.put(wordInfo.getValue(), words);
            } else {
                wordMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordMap;
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<WordInfo> wordInfos = new ArrayList<>();
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        for (String uniqueWord : new HashSet<>(words)) {
            int count = (int) words.stream().filter(word ->
                    uniqueWord.equals(word)
            ).count();
            wordInfos.add(new WordInfo(uniqueWord, count));
        }
        return wordInfos;
    }
}
