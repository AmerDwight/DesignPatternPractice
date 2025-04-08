package indv.amer.subscriber;

import indv.amer.Video;

public class WaterBall extends ChannelSubscriber {
    public WaterBall(String name) {
        super(name);
    }

    @Override
    public void reactToNewVideo(Video video) {
        long waterBallStandard = 3 * 60 * 1000;
        if (video.getLengthInMilliSeconds() >= waterBallStandard) {
            video.smashALikeButton(this.getName());
        }
    }
}
