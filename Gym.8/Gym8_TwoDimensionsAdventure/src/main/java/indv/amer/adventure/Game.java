package indv.amer.adventure;

import indv.amer.adventure.creature.Creature;
import indv.amer.adventure.creature.character.Character;
import indv.amer.adventure.creature.monster.Monster;
import indv.amer.adventure.map.AdventureMap;
import indv.amer.adventure.treasure.Treasure;
import indv.amer.adventure.treasure.TreasureFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
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
        List<Creature> allCreatures = new ArrayList<>(map.getExistCreatures());
        allCreatures.sort((c1, c2) -> {
            boolean c1IsHero = c1 instanceof Character;
            boolean c2IsHero = c2 instanceof Character;

            if (c1IsHero && !c2IsHero) return -1;
            if (!c1IsHero && c2IsHero) return 1;
            return 0;
        });

        // Game Start
        while (true) {
            map.printMap();
            for(Creature creature: allCreatures){
                if(creature != null && creature.isAlive()){
                    creature.action();
                }
                if(!character.isAlive()){
                    log.info("Wasted, Character is dead...");
                    return ;
                }else if(map.getExistCreatures().size()==1){
                    log.info("Did show itch tall terry, Character defeat monsters...");
                    return ;
                }
            }
        }
    }


}