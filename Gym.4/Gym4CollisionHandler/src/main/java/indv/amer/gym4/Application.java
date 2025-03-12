package indv.amer.gym4;

import indv.amer.gym4.handler.*;
import indv.amer.gym4.sprite.Fire;
import indv.amer.gym4.sprite.Hero;
import indv.amer.gym4.sprite.Water;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Application {
    public static void main(String[] args){
        World world = new World(
                30,
                10,
                Arrays.asList(Water.class, Fire.class, Hero.class),
                new HeroAndHeroCollisionHandler(new HeroAndWaterCollisionHandler(new HeroAndFireCollisionHandler(
                        new WaterAndWaterCollisionHandler(new FireAndWaterCollisionHandler(new FireAndFireCollisionHandler(null))))))
        );
        world.start();
    }
}
