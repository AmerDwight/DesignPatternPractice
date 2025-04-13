package indv.amer.command;

import indv.amer.device.Tank;

public class TankMoveBackwardCommand extends KeyboardCommand<Tank> {
    public TankMoveBackwardCommand(KeyboardCommand<?> nextCommand, Tank device) {
        super(nextCommand, device);
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
