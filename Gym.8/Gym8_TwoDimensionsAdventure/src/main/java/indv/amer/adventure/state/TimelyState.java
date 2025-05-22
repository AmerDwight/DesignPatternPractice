package indv.amer.adventure.state;

import indv.amer.adventure.creature.Creature;

public abstract class TimelyState extends State {

    public TimelyState(Creature creature, int DURATION) {
        super(creature);
        this.DURATION = DURATION;
    }

    public final int DURATION;
    public int remaining = 0;

    @Override
    public void checkState() {
        this.doEffect();
        this.remaining += 1;
        if (DURATION == remaining) {
            exitState();
        }
    }

    public abstract void doEffect();
}
