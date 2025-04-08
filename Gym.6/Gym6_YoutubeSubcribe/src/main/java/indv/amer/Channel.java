package indv.amer;

import indv.amer.subscriber.ChannelSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Channel extends YoutubeMember {
    List<ChannelSubscriber> subscribers = new ArrayList<>();

    public Channel(String name) {
        super(name);
    }

    public void addSubscriber(ChannelSubscriber newbie) {
        log.info("{} 訂閱了 {}。", newbie.getName(), this.getName());
        this.subscribers.add(newbie);
    }

    public void removeSubscriber(ChannelSubscriber hater) {
        log.info("{} 解除訂閱了 {}。", hater.getName(), this.getName());
        this.subscribers.remove(hater);
    }

    public void upload(Video newVideo) {
        newVideo.setChannel(this);
        log.info("頻道 {} 上架了一則新影片 \"{}\"。", this.name, newVideo.getTitle());
        if (CollectionUtils.isNotEmpty(this.subscribers)) {
            List<ChannelSubscriber> subscribersCopy = new ArrayList<>(subscribers);
            for (ChannelSubscriber subscriber : subscribersCopy) {
                subscriber.reactToNewVideo(newVideo);
            }
        }
    }
}
