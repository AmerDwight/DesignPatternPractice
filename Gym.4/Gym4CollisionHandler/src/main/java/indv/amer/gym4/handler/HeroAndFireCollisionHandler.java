package indv.amer.gym4.handler;

import indv.amer.gym4.MapSpace;
import indv.amer.gym4.sprite.Fire;
import indv.amer.gym4.sprite.Hero;
import indv.amer.gym4.sprite.Water;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeroAndFireCollisionHandler extends CollisionHandler<Hero, Fire> {
    public HeroAndFireCollisionHandler(CollisionHandler<?, ?> collisionHandler) {
        super(collisionHandler, Hero.class, Fire.class);
    }

    @Override
    protected boolean processCollision(MapSpace fromPosition, MapSpace toPosition) {
        Hero hero = fromPosition.getSprite() instanceof Hero ?
                (Hero) fromPosition.getSprite() : (Hero) toPosition.getSprite();
        log.info("Hero got burned 10 HP!");
        hero.getHurt(10);
        fromPosition.setSprite(null);
        if (hero.isStillAlive()) {
            toPosition.setSprite(hero);

        } else {
            log.info("Hero died...");
            toPosition.setSprite(null);
        }
        return true;
    }
}
