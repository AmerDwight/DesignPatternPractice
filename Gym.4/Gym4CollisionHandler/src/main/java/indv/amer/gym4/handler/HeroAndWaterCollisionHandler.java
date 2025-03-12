package indv.amer.gym4.handler;

import indv.amer.gym4.MapSpace;
import indv.amer.gym4.sprite.Hero;
import indv.amer.gym4.sprite.Water;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeroAndWaterCollisionHandler extends CollisionHandler<Hero, Water> {
    public HeroAndWaterCollisionHandler(CollisionHandler<?, ?> collisionHandler) {
        super(collisionHandler, Hero.class, Water.class);
    }

    @Override
    protected boolean processCollision(MapSpace fromPosition, MapSpace toPosition) {
        Hero hero = fromPosition.getSprite() instanceof Hero?
                (Hero)fromPosition.getSprite():(Hero) toPosition.getSprite();
        log.info("Hero get healed 10 HP!");
        hero.getHeal(10);
        fromPosition.setSprite(null);
        toPosition.setSprite(hero);
        return true;
    }
}
