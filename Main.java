import enums.FilePath;
import services.DictionaryServiceImpl;

public class Main {
    public static void main(String[] args) {
        DictionaryServiceImpl dictionaryService =new DictionaryServiceImpl();
        dictionaryService.run();
    }
}
