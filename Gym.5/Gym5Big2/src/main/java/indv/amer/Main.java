package indv.amer;

import indv.amer.command.CommandReader;
import indv.amer.command.TextCommander;
import indv.amer.pattern.FullHousePattern;
import indv.amer.pattern.PairPattern;
import indv.amer.pattern.SinglePattern;
import indv.amer.pattern.StraightPattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        CommandReader reader = new TextCommander();

        try {
            if (reader instanceof TextCommander) {
                TextCommander textCommander = (TextCommander) reader;
                textCommander.waitForInitialization();
            }
            Big2 big2 = new Big2(reader,
                    new SinglePattern(new PairPattern(new StraightPattern(new FullHousePattern(null)))));
            big2.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // 顯示比對結果
        ComparisonResult result = compareWithLogFile();
        result.showResultDialog();


    }

    private static ComparisonResult compareWithLogFile() {
        // 建立檔案選擇器
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("選擇要與 application.log 比較的檔案");
        // 設定檔案過濾器，只顯示 .txt 和 .out 檔案
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) return true;
                String name = file.getName().toLowerCase();
                return name.endsWith(".txt") || name.endsWith(".out");
            }

            @Override
            public String getDescription() {
                return "文字檔案 (*.txt, *.out)";
            }
        });

        // 顯示檔案選擇對話框
        int result = fileChooser.showOpenDialog(null);

        // 如果使用者沒有選擇檔案，則返回不相符的結果
        if (result != JFileChooser.APPROVE_OPTION) {
            return new ComparisonResult(false, "未選擇檔案進行比較");
        }

        File selectedFile = fileChooser.getSelectedFile();
        File logFile = new File("application.log");

        // 檢查 application.log 是否存在
        if (!logFile.exists()) {
            return new ComparisonResult(false, "application.log 檔案不存在");
        }

        try {
            // 讀取兩個檔案的內容
            List<String> logLines = readLines(logFile);
            List<String> selectedLines = readLines(selectedFile);

            // 比較檔案行數
            if (logLines.size() != selectedLines.size()) {
                return new ComparisonResult(false,
                        String.format("檔案行數不同: application.log (%d行), %s (%d行)",
                                logLines.size(), selectedFile.getName(), selectedLines.size()));
            }

            // 逐行比較內容
            for (int i = 0; i < logLines.size(); i++) {
                if (!StringUtils.equals(logLines.get(i), selectedLines.get(i))) {
                    return new ComparisonResult(false,
                            String.format("第 %d 行內容不同:\napplication.log: %s\n%s: %s",
                                    (i + 1), logLines.get(i), selectedFile.getName(), selectedLines.get(i)));
                }
            }

            // 所有行都相同
            return new ComparisonResult(true, "檔案內容完全相同");

        } catch (IOException e) {
            return new ComparisonResult(false, "比較檔案時出錯: " + e.getMessage());
        }
    }

    /**
     * 讀取檔案內容，返回行列表
     */
    private static List<String> readLines(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
class ComparisonResult {
    private final boolean areEqual;
    @Getter
    private final String message;

    public ComparisonResult(boolean areEqual, String message) {
        this.areEqual = areEqual;
        this.message = message;
    }

    public boolean areEqual() {
        return areEqual;
    }

    public void showResultDialog() {
        String title = areEqual ? "比較結果: 相符" : "比較結果: 不相符";
        JOptionPane.showMessageDialog(null, message, title,
                areEqual ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
    }
}