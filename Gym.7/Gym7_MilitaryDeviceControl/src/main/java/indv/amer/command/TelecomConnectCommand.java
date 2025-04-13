package indv.amer.command;

import indv.amer.device.Telecom;

public class TelecomConnectCommand extends KeyboardCommand<Telecom> {
    public TelecomConnectCommand(Telecom device, KeyboardCommand<?> nextCommand) {
        super(device, nextCommand);
    }

    public TelecomConnectCommand(Telecom _device) {
        super(_device);
    }

    @Override
    protected void executeThisCommand() {
        this.device.connect();
    }

    @Override
    protected void undoThisCommand() {
        this.device.disconnect();
    }
}
