package indv.amer;

import indv.amer.command.KeyboardCommand;
import indv.amer.command.*;
import indv.amer.controller.Keyboard;
import indv.amer.device.Tank;
import indv.amer.device.Telecom;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Scanner;

@Slf4j
public class SoliderCLI {
    private final Keyboard keyboard;
    private final Tank tank;
    private final Telecom telecom;
    private final Scanner scanner;

    public SoliderCLI() {
        // 初始化設備
        keyboard = new Keyboard();
        tank = new Tank();
        telecom = new Telecom();
        scanner = new Scanner(System.in);

        // 綁定基本命令
        setupDefaultCommands();
    }

    private void setupDefaultCommands() {
        keyboard.bindingControl("z", new ResetKeyboardCommand(keyboard));
        keyboard.bindingControl("a", new TankMoveForwardCommand(tank));
        keyboard.bindingControl("b", new TankMoveBackwardCommand(tank));
        keyboard.bindingControl("c", new TelecomConnectCommand(telecom));
        keyboard.bindingControl("d", new TelecomDisconnectCommand(telecom));
        log.info("基本命令已綁定: z=重置, a=坦克前進, b=坦克後退, c=通訊連接, d=通訊斷開");
    }

    public void start() {
        log.info("It's CLI for solider.");
        printHelp();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                log.info("Exit system.");
                break;
            }

            processCommand(input);
        }

        scanner.close();
    }

    private void printHelp() {
        log.info("Usages:");
        log.info("  exec <token> - 執行綁定的命令");
        log.info("  bind <token> <command> - 綁定命令到按鍵");
        log.info("  macro <token> <commands> - 創建並綁定 Macro 指令");
        log.info("  undo - 撤銷上一個操作");
        log.info("  redo - 重做上一個撤銷的操作");
        log.info("  reset - 重置鍵盤");
        log.info("  help - 顯示幫助");
        log.info("  exit/quit - 退出系統");
    }

    private void processCommand(String input) {
        String[] parts = input.split("\\s+");
        String command = parts[0].toLowerCase();

        try {
            switch (command) {
                case "help":
                    printHelp();
                    break;
                case "exec":
                    executeCommand(parts);
                    break;
                case "bind":
                    bindCommand(parts);
                    break;
                case "macro":
                    createMacro(parts);
                    break;
                case "undo":
                    keyboard.undo();
                    log.info("Undo success.");
                    break;
                case "redo":
                    keyboard.redo();
                    log.info("Redo success.");
                    break;
                case "reset":
                    keyboard.execute("z");
                    log.info("Keyboard reset finished.");
                    break;
                default:
                    log.warn("Unparsable cli command: {}", command);
                    break;
            }
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
    }

    private void executeCommand(String[] parts) {
        if (parts.length < 2) {
            log.warn("Hint: exec <token>");
            return;
        }

        String token = parts[1];
        keyboard.execute(token);
        log.info("Execute: {}", token);
    }

    private void bindCommand(String[] parts) {
        if (parts.length < 3) {
            log.warn("Hint: bind <token> <command>");
            return;
        }

        String token = parts[1];
        String commandType = parts[2];

        KeyboardCommand<?> command = createCommand(commandType);
        if (command != null) {
            keyboard.bindingControl(token, command);
            log.info("Binding command {} to {}", commandType, token);
        } else {
            log.warn("Unparsable command: {}", commandType);
        }
    }

    private KeyboardCommand<?> createCommand(String commandType) {
        return switch (commandType.toLowerCase()) {
            case "tank-forward" -> new TankMoveForwardCommand(tank);
            case "tank-backward" -> new TankMoveBackwardCommand(tank);
            case "telecom-connect" -> new TelecomConnectCommand(telecom);
            case "telecom-disconnect" -> new TelecomDisconnectCommand(telecom);
            case "reset" -> new ResetKeyboardCommand(keyboard);
            default -> null;
        };
    }

    private void createMacro(String[] parts) {
        if (parts.length < 3) {
            log.warn("用法: macro <token> <command1> <command2> ...");
            return;
        }

        String key = parts[1];
        KeyboardCommand<?> lastCommand = null;
        KeyboardCommand<?> firstCommand = null;

        // 從後往前建立命令鏈
        for (int i = parts.length - 1; i >= 2; i--) {
            String commandType = parts[i];
            KeyboardCommand<?> newCommand = createCommand(commandType);

            if (newCommand == null) {
                log.warn("Unparsable command: {}, skip.", commandType);
                continue;
            }

            if (lastCommand == null) {
                lastCommand = newCommand;
                firstCommand = newCommand;
            } else {
                try {
                    // 獲取命令類的構造函數
                    Constructor<?> constructor = newCommand.getClass().getConstructor(
                            newCommand.getDevice().getClass(), KeyboardCommand.class);

                    // 創建新的命令實例，連接到之前的命令
                    firstCommand = (KeyboardCommand<?>) constructor.newInstance(
                            newCommand.getDevice(), firstCommand);
                } catch (Exception e) {
                    log.error("Error occurs while building macro : {}", e.getMessage());
                    return;
                }
            }
        }

        if (firstCommand != null) {
            keyboard.bindingControl(key, firstCommand);
            log.info("Macro command has bound to: {}", key);
        }
    }

    public static void main(String[] args) {
        SoliderCLI cli = new SoliderCLI();
        cli.start();

        // try macro example:
        // macro m tank-forward tank-backward tank-forward
        // exec m
        // undo
        // redo
        // reset
        // exec m
        // undo
        // exec m
    }
}
