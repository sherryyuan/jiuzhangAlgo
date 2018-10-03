import java.util.*;

public class AmazonOther {

    public String mostCommonWord(String paragraph, String[] banned) {
        //
        //split the string -> words with space
        //create a hashmap, key is the word
        if(paragraph == null) {
            return null;
        }

        String[] words = paragraph.split(" ");
        Map<String, Integer> countsMap = new HashMap<>();

        for(String s : words) {
            int currentCount = countsMap.get(s.toLowerCase());
            currentCount++;
            countsMap.put(s.toLowerCase(), currentCount);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(countsMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        for(String s : banned) {
            result.remove(s.toLowerCase());
        }
        return "";
    }

}
