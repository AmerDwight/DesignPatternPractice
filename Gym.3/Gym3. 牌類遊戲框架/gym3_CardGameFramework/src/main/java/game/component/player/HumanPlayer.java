package game.component.player;

import game.component.card.Card;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class HumanPlayer<T extends Card> extends Player<T> {

    @Override
    public int pickCardStrategy(int handSize) {
        int choice = (new Scanner(System.in)).nextInt();
        while (choice < 1 || choice > handSize) {
            System.out.println("Please Pick up a Card by order number within 0~" + handSize);
            choice = (new Scanner(System.in)).nextInt();
        }
        return choice;
    }

    protected String insertName() {
        System.out.println("Please input a name!");
        String onInsertName = (new Scanner(System.in)).nextLine();
        while (StringUtils.isBlank(onInsertName)) {
            System.out.println("Please input a available name!");
            onInsertName = (new Scanner(System.in)).nextLine();
        }
        return onInsertName;
    }
}
