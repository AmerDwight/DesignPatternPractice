package sprite;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class Hero implements Sprite {
    private int hp = 10;

    @Override
    public String getSymbol() {
        return "H";
    }

    public void getHeal(int addHp) {
        this.hp += addHp;
    }

    public void getHurt(int damage) {
        this.hp -= damage;
    }

    public boolean isStillAlive() {
        return this.hp > 0;
    }
}
