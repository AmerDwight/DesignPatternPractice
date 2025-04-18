package indv.amer.controller;

import indv.amer.command.KeyboardCommand;
import indv.amer.device.Device;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Slf4j
@NoArgsConstructor
public class Keyboard implements Device {
    private Map<String, KeyboardCommand<?>> commandMap = new HashMap<>();
    // Caching Memories for undo-reset
    private Map<String, KeyboardCommand<?>> lastCommandMap = new HashMap<>();
    private final Stack<KeyboardCommand<?>> doStack = new Stack<>();
    private final Stack<KeyboardCommand<?>> undoStack = new Stack<>();

    public void bindingControl(String commandToken, KeyboardCommand<?> onBindCommand) {
        // Verify
        if (StringUtils.isBlank(commandToken)) {
            throw new IllegalArgumentException("Blank token is no accept.");
        }
        if (onBindCommand == null) {
            throw new IllegalArgumentException("On Bind Command is illegal.");
        }
        if (this.commandMap.containsKey(commandToken) || !isValidCommandToken(commandToken)) {
            throw new IllegalArgumentException("The command token is already used.");
        }

        // Binding Command
        this.commandMap.put(commandToken, onBindCommand);
        log.info("Binding control on : Token {} for Command {}", commandToken, onBindCommand.getClass().getSimpleName());
    }

    public void unbindingControl(String commandToken) {
        // Verify
        if (StringUtils.isBlank(commandToken) || !this.commandMap.containsKey(commandToken)) {
            throw new IllegalArgumentException("Blank token is no accept.");
        }
        KeyboardCommand<?> bindedCommand = this.commandMap.get(commandToken);
        this.commandMap.remove(commandToken);
        log.info("Unbinding control on : Token {} for Command {}", commandToken, bindedCommand.getClass().getSimpleName());
    }

    public void execute(String commandToken) {
        // Verify
        if (StringUtils.isBlank(commandToken) || !this.commandMap.containsKey(commandToken)) {
            throw new IllegalArgumentException("Blank token is no accept.");
        }

        KeyboardCommand<?> onDoCommand = this.commandMap.get(commandToken);
        onDoCommand.execute();
        this.doStack.push(onDoCommand);
        this.undoStack.clear();
    }

    public void redo() {
        // Verify
        if (CollectionUtils.isEmpty(undoStack)) {
            throw new IllegalArgumentException("It's latest state now, no action could redo.");
        }
        KeyboardCommand<?> redoCommand = undoStack.pop();
        redoCommand.execute();
        doStack.push(redoCommand);
    }

    public void undo() {
        // Verify
        if (CollectionUtils.isEmpty(doStack)) {
            throw new IllegalArgumentException("It's origin state now, no action could undo.");
        }
        KeyboardCommand<?> undoCommand = doStack.pop();
        undoCommand.undo();
        undoStack.push(undoCommand);
    }

    private boolean isValidCommandToken(String commandToken){
        // Check if the command token is a single lowercase letter (a-z)
        return commandToken != null && commandToken.length() == 1 &&
                commandToken.charAt(0) >= 'a' && commandToken.charAt(0) <= 'z';
    }

    public void resetControlBinding(){
        if(MapUtils.isEmpty(this.commandMap)){
            log.info("No command bindings is exists.");
        }else{
            this.lastCommandMap = Map.copyOf(this.commandMap);
            this.commandMap = new HashMap<>();
            log.info("Command Binding is clear now.");
        }
    }

    public void recoverResetControlBinding(){
        if(MapUtils.isEmpty(this.lastCommandMap)){
            log.info("No command bindings could be recovered.");
        }else{
            this.commandMap = Map.copyOf(this.lastCommandMap);
        }
    }

}
