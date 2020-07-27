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

                String[] words = sentence.split(SPACE_PATTERN);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfos.add(wordInfo);
                }

                Map<String, List<WordInfo>> wordMap = getListMap(wordInfos);

                List<WordInfo> tempWordInfo = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : wordMap.entrySet()) {
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    tempWordInfo.add(input);
                }
                wordInfos = tempWordInfo;

                wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - secondWordInfo.getWordCount());

                StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
                for (WordInfo wordInfo : wordInfos) {
                    String wordInfoSentence = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
                    joiner.add(wordInfoSentence);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
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
}
