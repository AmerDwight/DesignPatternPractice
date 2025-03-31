package indv.amer.command;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class CliCommander implements CommandReader {
    @Override
    public String getNextCommand() {
        String nextInput = new Scanner(System.in).next();
        if (StringUtils.isBlank(nextInput)) {
            return this.getNextCommand();
        } else {
            return nextInput;
        }
    }
}
