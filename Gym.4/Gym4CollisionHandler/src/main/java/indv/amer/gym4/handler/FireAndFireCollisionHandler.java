package indv.amer.gym4.handler;

import indv.amer.gym4.MapSpace;
import indv.amer.gym4.sprite.Fire;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FireAndFireCollisionHandler extends CollisionHandler<Fire, Fire> {
    public FireAndFireCollisionHandler(CollisionHandler<?, ?> collisionHandler) {
        super(collisionHandler, Fire.class, Fire.class);
    }

    @Override
    protected boolean processCollision(MapSpace fromPosition, MapSpace toPosition) {
        log.info("Another Fire at destination.");
        return false;
    }
}
