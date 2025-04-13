package indv.amer.command;

import indv.amer.controller.Keyboard;

public class ResetKeyboardCommand extends KeyboardCommand<Keyboard> {
    public ResetKeyboardCommand(Keyboard device, KeyboardCommand<?> nextCommand) {
        super(device, nextCommand);
    }

    public ResetKeyboardCommand(Keyboard _device) {
        super(_device);
    }

    @Override
    protected void executeThisCommand() {
        this.device.resetControlBinding();
    }

    @Override
    protected void undoThisCommand() {
        this.device.recoverResetControlBinding();
    }
}
