package indv.amer;

import indv.amer.subscriber.ChannelSubscriber;
import indv.amer.subscriber.FireBall;
import indv.amer.subscriber.WaterBall;
import indv.amer.utils.FileContentComparator;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Youtube {
    public static void main(String[] args) {
        WaterBall waterBall = new WaterBall("水球");
        FireBall fireBall = new FireBall("火球");
        Channel pewDiePie = new Channel("PewDiePie");
        Channel waterBallSA = new Channel("水球軟體學院");

        // Subscribe
        // 水球訂閱 PewDiePie 和 水球軟體學院
        // 火球訂閱 PewDiePie 和 水球軟體學院
        waterBallSA.addSubscriber(waterBall);
        pewDiePie.addSubscriber(waterBall);
        waterBallSA.addSubscriber(fireBall);
        pewDiePie.addSubscriber(fireBall);

        // Upload
        // 水球軟體學院上傳一部影片：標題：”C1M1S2”、敘述：”這個世界正是物件導向的呢！”、影片長度：4 分鐘。
        waterBallSA.upload(
                Video.builder()
                        .title("C1M1S2")
                        .description("這個世界正是物件導向的呢！")
                        .lengthInMilliSeconds(4 * 60 * 1000)
                        .build()
        );
        // PewDiePie 上傳一部影片：標題：”Hello guys”、敘述：”Clickbait”、影片長度：30 秒。
        pewDiePie.upload(
                Video.builder()
                        .title("Hello guys")
                        .description("Clickbait")
                        .lengthInMilliSeconds(30 * 1000)
                        .build()
        );
        // 水球軟體學院上傳一部影片：標題：”C1M1S3”、敘述：”物件 vs. 類別”、影片長度：1 分鐘。
        waterBallSA.upload(
                Video.builder()
                        .title("C1M1S3")
                        .description("物件 vs. 類別")
                        .lengthInMilliSeconds(60 * 1000)
                        .build()
        );
        // PewDiePie 上傳一部影片：標題：”Minecraft”、敘述：”Let’s play Minecraft”、影片長度：30 分鐘。
        pewDiePie.upload(
                Video.builder()
                        .title("Minecraft")
                        .description("Let’s play Minecraft")
                        .lengthInMilliSeconds(30 * 60 * 1000)
                        .build()
        );


        // 創建比較器實例，比較當前目錄下的 application.log 和 result.txt
        FileContentComparator comparator = new FileContentComparator("./application.log", "./result.txt");

        try {
            boolean areEqual = comparator.areContentsEqual();
            if (areEqual) {
                System.out.println("檔案內容完全一致！");
            } else {
                System.out.println("檔案內容不一致！");
            }
        } catch (IOException e) {
            System.err.println("比較檔案時發生錯誤: " + e.getMessage());
        }
    }
}