package services;

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
        if(isEmpty(slangWord))
            return null;
        searchHistory.add(slangWord);
        return dictionary.get(slangWord);
    }

    @Override
    public List<String> getListHistorySearch() {
        return searchHistory;
    }

    @Override
    public List<String> getListByDefinition(String keyDefinition) {
        if(isEmpty(keyDefinition))
            return null;
        List<String> result = new ArrayList<>();
        for (String slang : dictionary.keySet()) {
            String definition = dictionary.get(slang);
            if (definition.contains(keyDefinition)) {
                result.add(slang);
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
            switch (choose){
                case "1":
                    dictionary.put(slang, definition);
                    break;
                case "2":
                    System.out.println("Nhập Slang word mới :");
                    String newKey = scanner.nextLine();
                    if(!newKey.equalsIgnoreCase(slang) && !dictionary.containsKey(newKey)){
                        dictionary.put(newKey, definition);
                    }else {
                        System.out.println("Slang word này đã có ! Vui lòng thực hiện lại thao tác !");
                        return;
                    }
                    break;
            }
        } else {
            dictionary.put(slang, definition);
        }
        System.out.println("Thêm slang word thành công !");
    }

    @Override
    public void delSlang(String slang) {
        if (dictionary.containsKey(slang)) {
            dictionary.remove(slang);
            System.out.println("Xóa slang word thành công !");
        } else {
            System.out.println("Slang word không tồn tại !");
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("-----  LẬP TRÌNH ỨNG DỤNG JAVA : SLANG WORD  -----");
            System.out.println("-----  Các chức năng  -----");
            System.out.println("1. Tìm kiếm theo slang word.");
            System.out.println("2. Tìm kiếm theo definition, hiển thị ra tất cả các slang words mà trong defintion có chứa keyword gõ vào.");
            System.out.println("3. Hiển thị history, danh sách các slang word đã tìm kiếm.");
            System.out.println("4. Add 1 slang words mới.");
            System.out.println("5. Edit 1 slang word.");
            System.out.println("6. Delete 1 slang word. Confirm trước khi xoá.");
            System.out.println("7. Reset danh sách slang words gốc.");
            System.out.println("8. Random 1 slang word (On this day slang word.");
            System.out.println("9. Đố vui: Tìm definition cho slang word.");
            System.out.println("10. Đố vui: Tìm slang word cho definition.");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {

            }
        }
    }

    private boolean isEmpty(String string){
        return string == null || string.isEmpty();
    }
}
