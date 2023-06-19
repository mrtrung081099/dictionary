package services;

import dto.SlangWordInfo;

import java.util.List;

public interface DictionaryService {
    void loadDictionaryFromFile(String filePath);
    String getBySlangWord(String slangWord);
    String randomSlang();
    List<String> getListHistorySearch();
    List<SlangWordInfo> getListByDefinition(String keyDefinition);
    void addSlang(String slang, String definition);
    void delSlang(String slang);
    void run();
}
