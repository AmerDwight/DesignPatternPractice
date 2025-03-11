package handler;

public abstract class CollisionHandler {

    CollisionHandler collisionHandler;

    public CollisionHandler(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }


    abstract boolean handle();
}
