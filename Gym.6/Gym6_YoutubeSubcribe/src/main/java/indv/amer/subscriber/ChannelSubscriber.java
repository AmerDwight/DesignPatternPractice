package indv.amer.subscriber;

import indv.amer.Video;
import indv.amer.YoutubeMember;

public abstract class ChannelSubscriber extends YoutubeMember {
    public ChannelSubscriber(String name) {
        super(name);
    }
    abstract public void reactToNewVideo(Video video);
}
