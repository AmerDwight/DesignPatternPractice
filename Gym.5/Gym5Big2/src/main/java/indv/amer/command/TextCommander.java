package indv.amer.command;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextCommander implements CommandReader {
    private final List<String> commands;
    private int currentCommandIndex;
    private String selectedFilePath;

    public TextCommander() {
        commands = new ArrayList<>();
        currentCommandIndex = 0;

        // 選擇文件
        SwingUtilities.invokeLater(() -> {
            try {
                selectTextFile();
                loadCommands();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "讀取文件時發生錯誤: " + e.getMessage(),
                        "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public String getNextCommand() {
        // 如果沒有更多指令，返回空字符串
        if (commands.isEmpty() || currentCommandIndex >= commands.size()) {
            throw new IllegalArgumentException("No more command...");
        }
        return commands.get(currentCommandIndex++);
    }

    private void selectTextFile() {
        JFileChooser fileChooser = getJFileChooser();

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFilePath = selectedFile.getAbsolutePath();
        } else {
            // 用戶取消了選擇，显示警告
            JOptionPane.showMessageDialog(null,
                    "未選擇文件，程序可能無法正常工作。",
                    "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static JFileChooser getJFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("選擇指令文件");

        // 只接受txt和in文件
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                String fileName = file.getName().toLowerCase();
                return fileName.endsWith(".txt") || fileName.endsWith(".in");
            }

            @Override
            public String getDescription() {
                return "文本文件 (*.txt, *.in)";
            }
        });
        return fileChooser;
    }

    private void loadCommands() throws IOException {
        if (selectedFilePath == null || selectedFilePath.isEmpty()) {
            return;
        }

        // 檢查文件是否存在
        File file = new File(selectedFilePath);
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在: " + selectedFilePath);
        }

        // 檢查文件是否可讀
        if (!file.canRead()) {
            throw new IOException("無法讀取文件: " + selectedFilePath);
        }

        // 讀取文件內容
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                commands.add(line);
            }
        }

        if (commands.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "文件不包含指令。",
                    "警告", JOptionPane.WARNING_MESSAGE);
        }
    }
}