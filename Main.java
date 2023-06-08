import enums.FilePath;
import services.DictionaryService;
import services.DictionaryServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DictionaryServiceImpl dictionaryService =new DictionaryServiceImpl();
        dictionaryService.loadDictionaryFromFile(FilePath.SLANG.url);
    }
}
