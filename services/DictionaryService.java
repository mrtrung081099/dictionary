package services;

import java.util.List;

public interface DictionaryService {
    void loadDictionaryFromFile(String filePath);
    String getBySlangWord(String slangWord);
    List<String> getListHistorySearch();
    List<String> getListByDefinition(String keyDefinition);
    void addSlang(String slang, String definition);
    void delSlang(String slang);
}
