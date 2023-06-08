package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class DictionaryServiceImpl implements DictionaryService{
    private final Map<String, String> dictionary;
    private final List<String> searchHistory;

    public DictionaryServiceImpl() {
        dictionary = new HashMap<>();
        searchHistory = new ArrayList<>();
    }

    @Override
    public void loadDictionaryFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    dictionary.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBySlangWord(String slangWord) {
        if(slangWord == null || slangWord.isEmpty())
            return null;
        searchHistory.add(slangWord);
        return dictionary.get(slangWord);
    }

    @Override
    public List<String> getListHistorySearch() {
        return searchHistory;
    }

}
