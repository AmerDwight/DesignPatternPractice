package indv.amer;

import indv.amer.creature.Creature;
import indv.amer.creature.character.Character;
import indv.amer.creature.character.CharacterDirection;
import indv.amer.creature.monster.Monster;
import indv.amer.treasure.Treasure;
import indv.amer.treasure.TreasureFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.stream.IntStream;

@Slf4j
public class Game {
    private AdventureMap map;

    public Game(int mapLength, int mapWidth, int monsterQuantity, int treasureQuantity) {
        if (mapLength <= 0 || mapWidth <= 0 || monsterQuantity <= 0 || treasureQuantity < 0) {
            throw new IllegalArgumentException("Init parameter is illegal.");
        }
        if ((monsterQuantity + treasureQuantity) > (mapLength * mapWidth - 1)) {
            throw new IllegalArgumentException("Too many objects while initializing");
        }
        map = new AdventureMap(mapLength, mapWidth);
        IntStream.range(0, monsterQuantity).forEach(
                i -> {
                    Monster monster = new Monster(map);
                    map.randomlyPutObject(monster);
                }
        );
        TreasureFactory factory = new TreasureFactory();
        IntStream.range(0, treasureQuantity).forEach(
                i -> {
                    Treasure treasure = factory.createRandomTreasure();
                    map.randomlyPutObject(treasure);
                }
        );
    }

    public void start() {
        // Initializing
        Character character = new Character(300, map, new CommandReader());
        map.randomlyPutObject(character);

        // Game Start
        while(true){

            map.printMap();
            character.action();

            List<Creature> surviveCreatures = map.getExistCreatures();
            if(character.isAlive()) map.getExistCreatures()){

            }
        }
    }



}