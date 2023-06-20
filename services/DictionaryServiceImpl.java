package services;

import dto.SlangWordInfo;
import enums.FilePath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class DictionaryServiceImpl implements DictionaryService{
    private Map<String, String> dictionary;
    private final List<String> searchHistory;
    private final Scanner scanner;
    public DictionaryServiceImpl() {
        dictionary = new HashMap<>();
        searchHistory = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadDictionaryFromFile(FilePath.SLANG.url);
    }

    @Override
    public void loadDictionaryFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            dictionary = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    dictionary.put(key.toUpperCase(), value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBySlangWord(String slangWord) {
        if(isEmpty(slangWord))
            return null;
        searchHistory.add(slangWord);
        return dictionary.get(slangWord.toUpperCase());
    }

    @Override
    public String randomSlang() {
        Random rand = new Random();
        int indexRandom = rand.nextInt(dictionary.size());
        return new ArrayList<>(dictionary.keySet()).get(indexRandom);
    }

    @Override
    public List<String> getListHistorySearch() {
        return searchHistory;
    }

    @Override
    public List<SlangWordInfo> getListByDefinition(String keyDefinition) {
        if(isEmpty(keyDefinition))
            return null;
        List<SlangWordInfo> result = new ArrayList<>();
        for (String slang : dictionary.keySet()) {
            String definition = dictionary.get(slang);
            if (definition.contains(keyDefinition)) {
                result.add(new SlangWordInfo(slang,definition));
            }
        }
        return result;
    }

    @Override
    public void addSlang(String slang, String definition) {
        if (dictionary.containsKey(slang)) {
            System.out.println("Slang word này đã có, bạn muốn ghi đè hay tạo từ mới ?");
            System.out.println("1. Ghi đè");
            System.out.println("2. Tạo từ mới");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1" -> dictionary.put(slang, definition);
                case "2" -> {
                    System.out.println("Nhập Slang word mới :");
                    String newKey = scanner.nextLine();
                    if (!newKey.equalsIgnoreCase(slang) && !dictionary.containsKey(newKey)) {
                        dictionary.put(newKey, definition);
                    } else {
                        System.out.println("Slang word này đã có ! Vui lòng thực hiện lại thao tác !");
                        return;
                    }
                }
            }
        } else {
            dictionary.put(slang, definition);
        }
        System.out.println("Thêm slang word thành công !");
    }

    @Override
    public void delSlang(String slang) {
        if (dictionary.containsKey(slang)) {
            System.out.println("Xác nhận trước khi xóa :");
            System.out.println("1. Xác nhận xóa slang");
            System.out.println("2. Hủy");
            String choose = scanner.nextLine();
            switch (choose){
                case "1":
                    dictionary.remove(slang);
                    System.out.println("Xóa slang word thành công !");
                    break;
                case "2":
                    break;
            }
        } else {
            System.out.println("Slang word không tồn tại !");
        }
    }

    @Override
    public void randomGameFindDefinition() {
        String slangAnswer = randomSlang();
        String definitionAnswer = getBySlangWord(slangAnswer);
        System.out.println("Trong những definition sau đây , đâu là definition của từ '"+ slangAnswer.toUpperCase()+"' :");
        Random rand = new Random();
        int indexAnswer = rand.nextInt(3);
        List<String> question = List.of(new String[]{"A", "B", "C", "D"});
        for (int i = 0 ; i < question.size(); i++){
            if (i == indexAnswer){
                System.out.println(question.get(i)+"."+definitionAnswer);
            }else{
                System.out.println(question.get(i)+"."+getBySlangWord(randomSlang()));
            }
        }
        String choose = scanner.nextLine();
        if (question.get(indexAnswer).equalsIgnoreCase(choose)){
            System.out.println("Chúc mừng ! Câu trả lời hoàn toàn chính xác !");
        }else {
            System.out.println("Rất tiếc ! Sai mất rồi !");
        }
    }

    @Override
    public void randomGameFindSlang() {
        String slangAnswer = randomSlang();
        String definitionAnswer = getBySlangWord(slangAnswer);
        System.out.println("Trong những slang word sau đây , đâu là slang word của câu '"+ definitionAnswer +"' :");
        Random rand = new Random();
        int indexAnswer = rand.nextInt(3);
        List<String> question = List.of(new String[]{"A", "B", "C", "D"});
        for (int i = 0 ; i < question.size(); i++){
            if (i == indexAnswer){
                System.out.println(question.get(i)+"."+slangAnswer);
            }else{
                System.out.println(question.get(i)+"."+randomSlang());
            }
        }
        String choose = scanner.nextLine();
        if (question.get(indexAnswer).equalsIgnoreCase(choose)){
            System.out.println("Chúc mừng ! Câu trả lời hoàn toàn chính xác !");
        }else {
            System.out.println("Rất tiếc ! Sai mất rồi !");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("");
                System.out.println("");
                System.out.println("----- LẬP TRÌNH ỨNG DỤNG JAVA : SLANG WORD -----");
                System.out.println("----- Các chức năng -----");
                System.out.println("1. Tìm kiếm theo slang word.");
                System.out.println("2. Tìm kiếm theo definition.");
                System.out.println("3. Hiển thị history, danh sách các slang word đã tìm kiếm.");
                System.out.println("4. Add 1 slang words mới.");
                System.out.println("5. Edit 1 slang word.");
                System.out.println("6. Delete 1 slang word.");
                System.out.println("7. Reset danh sách slang words gốc.");
                System.out.println("8. Random 1 slang word (On this day slang word.");
                System.out.println("9. Đố vui: Tìm definition cho slang word.");
                System.out.println("10. Đố vui: Tìm slang word cho definition.");
                System.out.println("0. Thoát chương trình.");
                System.out.print("Nhập lựa chọn của bạn: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Nhập Slang word cần tìm kiếm :");
                        String input1 = scanner.nextLine();
                        String result1 = getBySlangWord(input1);
                        if (result1 != null) {
                            System.out.println("Kết quả tìm kiếm : ");
                            System.out.println(input1 + ": " + result1);
                        } else {
                            System.out.println("Slang word này không có .");
                        }
                    }
                    case 2 -> {
                        System.out.println("Nhập definition cần tìm kiếm :");
                        String input2 = scanner.nextLine();
                        List<SlangWordInfo> result2 = getListByDefinition(input2);
                        if (result2 != null && !result2.isEmpty()) {
                            System.out.println("Kết quả tìm kiếm : ");
                            for (SlangWordInfo info : result2) {
                                System.out.println(info.getKey() + ": " + info.getValue());
                            }
                        } else {
                            System.out.println("Không tìm thấy kết quả phù hợp .");
                        }
                    }
                    case 3 -> System.out.println("Danh sách các slang word đã tìm kiếm :" + getListHistorySearch());
                    case 4 -> {
                        System.out.println("Nhập slang word mới :");
                        String slangAdd = scanner.nextLine();
                        System.out.println("Nhập definition :");
                        String definitionAdd = scanner.nextLine();
                        addSlang(slangAdd.toUpperCase(), definitionAdd);
                    }
                    case 5 -> {
                        System.out.println("Nhập slang word muốn chỉnh sửa :");
                        String slangEdit = scanner.nextLine().toUpperCase();
                        if (!dictionary.containsKey(slangEdit)) {
                            System.out.println("Slang word này chưa có, vui lòng thử lại !");
                        } else {
                            System.out.println("Nhập definition :");
                            String definitionEdit = scanner.nextLine();
                            dictionary.put(slangEdit, definitionEdit);
                            System.out.println("Kết quả chỉnh sửa : ");
                            System.out.println(slangEdit + ": " + definitionEdit);
                        }
                    }
                    case 6 -> {
                        System.out.println("Nhập slang word muốn xóa :");
                        String slangDel = scanner.nextLine().toUpperCase();
                        delSlang(slangDel);
                    }
                    case 7 -> {
                        System.out.println("Xác nhận reset danh sách slang words gốc :");
                        System.out.println("1. Reset");
                        System.out.println("2. Hủy");
                        String choose = scanner.nextLine();
                        switch (choose) {
                            case "1":
                                loadDictionaryFromFile(FilePath.SLANG.url);
                                System.out.println("Reset thành công !");
                                break;
                            case "2":
                                break;
                        }
                    }
                    case 8 -> {
                        System.out.println("Slang ngẫu nhiên :");
                        String slangRandom = randomSlang();
                        System.out.println(slangRandom + ": " + getBySlangWord(slangRandom));
                    }
                    case 9 ->{
                        randomGameFindDefinition();
                    }
                    case 10 ->{
                         randomGameFindSlang();
                    }
                }
            }
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

    private boolean isEmpty(String string){
        return string == null || string.isEmpty();
    }
}
