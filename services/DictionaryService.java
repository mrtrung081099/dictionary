package services;

import java.util.List;
import java.util.Map;

public interface DictionaryService {
    void loadDictionaryFromFile(String filePath);
    String getBySlangWord(String slangWord);
    List<String> getListHistorySearch();
}
