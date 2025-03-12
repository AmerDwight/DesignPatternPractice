package indv.amer.gym4.handler;

import indv.amer.gym4.MapSpace;
import indv.amer.gym4.sprite.Fire;
import indv.amer.gym4.sprite.Hero;
import indv.amer.gym4.sprite.Water;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FireAndWaterCollisionHandler extends CollisionHandler<Fire, Water> {
    public FireAndWaterCollisionHandler(CollisionHandler<?, ?> collisionHandler) {
        super(collisionHandler, Fire.class, Water.class);
    }

    @Override
    protected boolean processCollision(MapSpace fromPosition, MapSpace toPosition) {
        log.info("Fire Meets Water, They are all gone!");
        fromPosition.setSprite(null);
        toPosition.setSprite(null);
        return true;
    }
}
