package indv.amer.gym4.handler;

import indv.amer.gym4.MapSpace;
import lombok.extern.slf4j.Slf4j;
import indv.amer.gym4.sprite.Sprite;

@Slf4j
public abstract class CollisionHandler<T extends Sprite, U extends Sprite> {

    CollisionHandler<?, ?> collisionHandler;
    Class<T> typeT;
    Class<U> typeU;

    public CollisionHandler(CollisionHandler<?, ?> collisionHandler, Class<T> _typeT, Class<U> _typeU) {
        this.collisionHandler = collisionHandler;
        this.typeT = _typeT;
        this.typeU = _typeU;
    }

    protected abstract boolean processCollision(MapSpace fromPosition, MapSpace toPosition);

    public boolean handle(MapSpace fromPosition, MapSpace toPosition) {
        // 首先檢查物件類型
        // 可以處理就進行處理
        if (isThisHandlerProcess(fromPosition.getSprite(), toPosition.getSprite())) {
            return this.processCollision(fromPosition, toPosition);
        } else if (this.collisionHandler != null) {                     // 不行處理就交棒給下一位Handler
            return collisionHandler.handle(fromPosition, toPosition);
        } else {                                                        // 沒有Handler可以處理就取消移動
            log.error("No handler can handle this collision. Move Failed.");
            return false;
        }
    }

    private boolean isThisHandlerProcess(Sprite a, Sprite b) {
        return (typeT.isInstance(a) && typeU.isInstance(b)) ||
                (typeT.isInstance(b) && typeU.isInstance(a));
    }
}
