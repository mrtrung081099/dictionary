import enums.FilePath;
import services.DictionaryServiceImpl;

public class Main {
    public static void main(String[] args) {
        DictionaryServiceImpl dictionaryService =new DictionaryServiceImpl();
        dictionaryService.loadDictionaryFromFile(FilePath.SLANG.url);
        System.out.println(dictionaryService.getBySlangWord("Slag"));
        System.out.println(dictionaryService.getBySlangWord("#1"));
        System.out.println(dictionaryService.getListHistorySearch());
        System.out.println(dictionaryService.getListByDefinition("W"));
    }
}
