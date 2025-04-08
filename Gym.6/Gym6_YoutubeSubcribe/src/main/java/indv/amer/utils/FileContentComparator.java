package indv.amer.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * FileContentComparator 類別用於比較兩個檔案的內容是否完全一致，
 * 忽略最後一行的換行符號差異
 */
public class FileContentComparator {

    private final String firstFilePath;
    private final String secondFilePath;

    /**
     * 建構子
     * @param firstFilePath 第一個檔案的路徑
     * @param secondFilePath 第二個檔案的路徑
     */
    public FileContentComparator(String firstFilePath, String secondFilePath) {
        this.firstFilePath = firstFilePath;
        this.secondFilePath = secondFilePath;
    }

    /**
     * 比較兩個檔案的內容是否一致，忽略最後一行的換行符號差異
     * @return 如果內容一致則回傳 true，否則回傳 false
     * @throws IOException 當檔案讀取發生錯誤時
     */
    public boolean areContentsEqual() throws IOException {
        // 檢查檔案是否存在
        Path path1 = Paths.get(firstFilePath);
        Path path2 = Paths.get(secondFilePath);

        if (!Files.exists(path1)) {
            throw new IOException("檔案不存在: " + firstFilePath);
        }

        if (!Files.exists(path2)) {
            throw new IOException("檔案不存在: " + secondFilePath);
        }

        // 讀取兩個檔案的所有內容
        List<String> lines1 = readAllLines(firstFilePath);
        List<String> lines2 = readAllLines(secondFilePath);

        // 比較非空行數量
        if (lines1.size() != lines2.size()) {
            return false;
        }

        // 逐行比較內容
        for (int i = 0; i < lines1.size(); i++) {
            if (!lines1.get(i).equals(lines2.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 讀取檔案的所有有效行，去除空行
     * @param filePath 檔案路徑
     * @return 包含所有非空行的列表
     * @throws IOException 當檔案讀取發生錯誤時
     */
    private List<String> readAllLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    /**
     * 主方法用於測試
     */
    public static void main(String[] args) {
        // 創建比較器實例，比較當前目錄下的 application.log 和 result.txt
        FileContentComparator comparator = new FileContentComparator("./application.log", "./result.txt");

        try {
            boolean areEqual = comparator.areContentsEqual();
            if (areEqual) {
                System.out.println("檔案內容一致！");
            } else {
                System.out.println("檔案內容不一致！");
            }
        } catch (IOException e) {
            System.err.println("比較檔案時發生錯誤: " + e.getMessage());
        }
    }
}