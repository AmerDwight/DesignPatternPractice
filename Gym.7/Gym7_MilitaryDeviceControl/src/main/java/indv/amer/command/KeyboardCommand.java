package indv.amer.command;

import indv.amer.device.Device;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public abstract class KeyboardCommand<T extends Device> {
    public KeyboardCommand(T _device) {
        this.device = _device;
    }

    protected KeyboardCommand<?> nextCommand;
    protected T device;

    public void execute() {
        this.executeThisCommand();
        if (nextCommand != null) {
            nextCommand.execute();
        }
    }

    public void undo() {
        if (nextCommand != null) {
            nextCommand.undo();
        }
        this.undoThisCommand();
    }

    protected abstract void executeThisCommand();

    protected abstract void undoThisCommand();
}
