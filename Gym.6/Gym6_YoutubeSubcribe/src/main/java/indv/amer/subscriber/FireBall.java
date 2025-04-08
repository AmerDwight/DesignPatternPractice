package indv.amer.subscriber;

import indv.amer.Video;

public class FireBall extends ChannelSubscriber {
    public FireBall(String name) {
        super(name);
    }

    @Override
    public void reactToNewVideo(Video video) {
        long fireBallStandard = 60 * 1000;
        if (video.getLengthInMilliSeconds() <= fireBallStandard ) {
            video.getChannel().removeSubscriber(this);
        }
    }
}
