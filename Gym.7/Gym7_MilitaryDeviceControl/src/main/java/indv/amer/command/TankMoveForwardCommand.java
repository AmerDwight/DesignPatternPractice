package indv.amer.command;

import indv.amer.device.Tank;

public class TankMoveForwardCommand extends KeyboardCommand<Tank>{
    public TankMoveForwardCommand(KeyboardCommand<?> nextCommand, Tank device) {
        super(nextCommand, device);
    }

    public TankMoveForwardCommand(Tank _device) {
        super(_device);
    }

    @Override
    protected void executeThisCommand() {
        this.device.moveForward();
    }

    @Override
    protected void undoThisCommand() {
        this.device.moveBackward();
    }
}
