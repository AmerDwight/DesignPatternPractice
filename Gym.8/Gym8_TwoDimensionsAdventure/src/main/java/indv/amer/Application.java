package indv.amer;

import indv.amer.creature.character.Character;
import indv.amer.creature.character.CharacterDirection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
    public static void main(String[] args) {
        Character character = new Character(CharacterDirection.getRandomDirection(),300,)
        AdventureMap adventureMap = new AdventureMap(10,10);
        adventureMap.init();
        //一開始生成地圖，隨機生成特定數量、位置的怪物和寶藏，寶藏的內容物依照各內容物的生成機率隨機指定，然後生成主角的位置以及隨機決定主角初始面向的方向。
    }
}