package indv.amer.command;

import indv.amer.device.Tank;

public class TankMoveBackwardCommand extends KeyboardCommand<Tank> {
    public TankMoveBackwardCommand(Tank device, KeyboardCommand<?> nextCommand) {
        super(device, nextCommand);
    }

    public TankMoveBackwardCommand(Tank _device) {
        super(_device);
    }

    @Override
    protected void executeThisCommand() {
        this.device.moveBackward();
    }

    @Override
    protected void undoThisCommand() {
        this.device.moveForward();
    }
}
