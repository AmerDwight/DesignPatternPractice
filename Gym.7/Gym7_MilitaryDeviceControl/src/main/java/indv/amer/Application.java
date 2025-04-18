package indv.amer;

import indv.amer.command.KeyboardCommand;
import indv.amer.command.*;
import indv.amer.controller.Keyboard;
import indv.amer.device.Tank;
import indv.amer.device.Telecom;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class Application {
    public static void main(String[] args) {
        // inti devices
        Keyboard keyboard = new Keyboard();
        Tank tank = new Tank();
        Telecom telecom = new Telecom();

        // Command
        keyboard.bindingControl("z", new ResetKeyboardCommand(keyboard));
        keyboard.bindingControl("a", new TankMoveForwardCommand(tank));
        keyboard.bindingControl("b", new TankMoveBackwardCommand(tank));
        keyboard.bindingControl("c", new TelecomConnectCommand(telecom));
        keyboard.bindingControl("d", new TelecomDisconnectCommand(telecom));

        // Macro Command
        // tank △▽▽▽△△
        KeyboardCommand<?> macroTankDance =
                new TankMoveForwardCommand(tank,
                        new TankMoveBackwardCommand(tank,
                                new TankMoveBackwardCommand(tank,
                                        new TankMoveBackwardCommand(tank,
                                                new TankMoveForwardCommand(tank,
                                                        new TankMoveForwardCommand(tank))))));
        // tank + telecom
        KeyboardCommand<?> macroReadyToFight =
                new TankMoveForwardCommand(tank,
                        new TelecomConnectCommand(telecom));

        keyboard.bindingControl("e", macroTankDance);
        keyboard.bindingControl("f", macroReadyToFight);

        keyboard.execute("a");
        keyboard.execute("b");
        keyboard.execute("c");
        keyboard.execute("d");
        keyboard.execute("e");
        keyboard.execute("f");
        keyboard.execute("z");

        // try undo
        IntStream.range(0,7).forEach(
                i -> {
                    keyboard.undo();
                }
        );

        // try redo
        IntStream.range(0,7).forEach(
                i -> {
                    keyboard.redo();
                }
        );
    }
}