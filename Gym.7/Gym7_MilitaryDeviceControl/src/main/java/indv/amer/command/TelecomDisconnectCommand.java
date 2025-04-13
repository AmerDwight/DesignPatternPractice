package indv.amer.command;

import indv.amer.device.Telecom;

public class TelecomDisconnectCommand extends KeyboardCommand<Telecom> {
    public TelecomDisconnectCommand(Telecom device, KeyboardCommand<?> nextCommand) {
        super(device, nextCommand);
    }

    public TelecomDisconnectCommand(Telecom _device) {
        super(_device);
    }

    @Override
    protected void executeThisCommand() {
        this.device.disconnect();
    }

    @Override
    protected void undoThisCommand() {
        this.device.connect();
    }
}
