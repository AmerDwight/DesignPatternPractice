package indv.amer.gym4.handler;

import indv.amer.gym4.MapSpace;
import indv.amer.gym4.sprite.Fire;
import indv.amer.gym4.sprite.Water;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaterAndWaterCollisionHandler extends CollisionHandler<Water, Water> {
    public WaterAndWaterCollisionHandler(CollisionHandler<?, ?> collisionHandler) {
        super(collisionHandler, Water.class, Water.class);
    }

    @Override
    protected boolean processCollision(MapSpace fromPosition, MapSpace toPosition) {
        log.info("Another Water at destination.");
        return false;
    }
}
