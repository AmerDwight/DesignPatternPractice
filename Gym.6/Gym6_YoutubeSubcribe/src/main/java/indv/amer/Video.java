package indv.amer;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class Video {
    private Channel channel;
    private String title;
    private String description;
    private long lengthInMilliSeconds;

    public void smashALikeButton(String fanName) {
        log.info("{} 對影片 \"{}\" 按讚。", fanName, this.title);
    }
}
