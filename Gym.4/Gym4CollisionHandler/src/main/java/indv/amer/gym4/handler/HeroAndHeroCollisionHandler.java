package indv.amer.gym4.handler;

import indv.amer.gym4.MapSpace;
import indv.amer.gym4.sprite.Hero;
import indv.amer.gym4.sprite.Water;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeroAndHeroCollisionHandler extends CollisionHandler<Hero, Hero> {
    public HeroAndHeroCollisionHandler(CollisionHandler<?, ?> collisionHandler) {
        super(collisionHandler, Hero.class, Hero.class);
    }

    @Override
    protected boolean processCollision(MapSpace fromPosition, MapSpace toPosition) {
        log.info("Another Hero at destination.");
        return false;
    }
}
