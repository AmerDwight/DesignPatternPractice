package indv.amer.gym4;

import indv.amer.gym4.handler.CollisionHandler;
import indv.amer.gym4.sprite.Sprite;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class World {
    private final Integer width;
    private final Integer initSpriteSize;
    private final List<Class<? extends Sprite>> spiteClasses;
    private List<MapSpace> worldMap = new ArrayList<>();
    private final CollisionHandler collisionHandler;

    public World(int _width, int _initSpriteSize, List<Class<? extends Sprite>> _spiteClasses, CollisionHandler _collisionHandler) {
        this.width = _width;
        this.initSpriteSize = _initSpriteSize;
        this.spiteClasses = _spiteClasses;
        this.collisionHandler = _collisionHandler;
    }

    public void start() {
        this.randomInit();
        while (stillHasSprite()) {
            this.printRealTimeMap();
            Integer[] fromAndTo = this.readTwoNumbers();
            moveSprite(fromAndTo[0], fromAndTo[1]);
        }
    }

    private boolean stillHasSprite() {
        return this.worldMap.stream().filter(mapSpace -> mapSpace.getSprite() != null).collect(Collectors.toList()).size() > 0;
    }

    public void randomInit() {
        List<Integer> spritePositions = this.generateUniqueRandomNumbers(width, initSpriteSize);
        List<Sprite> randomInitSprite = this.generateRandomSprites(spiteClasses, initSpriteSize);

        worldMap = new ArrayList<>();
        IntStream.range(0, width).forEach(
                i -> worldMap.add(MapSpace.builder().estateNumber(i).build())
        );
        IntStream.range(0, spritePositions.size()).forEach(
                i -> {
                    worldMap.get(spritePositions.get(i)).setSprite(randomInitSprite.get(i));
                }
        );
    }

    private void moveSprite(int fromIndex, int toIndex) {
        if (isPositionUnavailable(fromIndex) || isPositionUnavailable(toIndex)) {
            log.error("Position is illegal.");
        }
        MapSpace fromPosition = worldMap.get(fromIndex);
        MapSpace toPosition = worldMap.get(toIndex);

        // 若來源端無生物，直接結束
        if (fromPosition.getSprite() == null) {
            log.info("No Sprite at position: {} ", fromIndex);
            return;
        }
        // 如果來源端有生物，目的地端沒有生物，那麼移動來源端生物過去
        if (toPosition.getSprite() == null) {
            toPosition.setSprite(fromPosition.getSprite());
            fromPosition.setSprite(null);
            return;
        }
        // 兩邊都有生物，交由CollisionHandler來進行
        if (collisionHandler.handle(fromPosition, toPosition)) {
            log.info("From: {} To {}, Move Success !", fromIndex, toIndex);
        } else {
            log.info("From: {} To {}, Move Failed !", fromIndex, toIndex);
        }
    }

    private boolean isPositionUnavailable(int someIndex) {
        return someIndex < 0 || someIndex > this.width - 1;
    }

    private void printRealTimeMap() {
        log.info("\n=========================================================================");

        log.info("RealTimeMap:");
        StringBuilder horizon = new StringBuilder("|");
        IntStream.range(0, worldMap.size()).forEach(
                i -> horizon.append("-")
        );
        horizon.append("|");
        StringBuilder spriteMarker = new StringBuilder(" ");
        worldMap.forEach(
                mapSpace -> {
                    if (mapSpace.getSprite() != null) {
                        spriteMarker.append(mapSpace.getSprite().getSymbol());
                    } else {
                        spriteMarker.append(" ");
                    }
                }
        );
        log.info(spriteMarker.toString());
        log.info(horizon.toString());

        log.info("\n=========================================================================");
    }

    private List<Integer> generateUniqueRandomNumbers(int width, int count) {
        if (count > width) {
            throw new IllegalArgumentException("請求的數量不能大於有效範圍大小");
        }
        List<Integer> numbers = new ArrayList<>(width);
        for (int i = 0; i < width; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random());
        return numbers.subList(0, count);
    }

    public List<Sprite> generateRandomSprites(List<Class<? extends Sprite>> spiteClasses, int count) {
        List<Sprite> spites = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            // 隨機選擇一個類型
            int randomIndex = random.nextInt(spiteClasses.size());
            Class<? extends Sprite> selectedClass = spiteClasses.get(randomIndex);

            try {
                // 使用反射創建實例
                Sprite spite = selectedClass.getDeclaredConstructor().newInstance();
                spites.add(spite);
            } catch (Exception e) {
                System.err.println("無法創建生物: " + e.getMessage());
            }
        }

        return spites;
    }

    public Integer[] readTwoNumbers() {
        Scanner scanner = new Scanner(System.in);
        Integer[] result = new Integer[2];

        System.out.println("請輸入兩個數字（以空白隔開）作為 From 與 To 的位置:");
        String input = scanner.nextLine();

        String[] numbers = input.trim().split("\\s+");
        if (numbers.length >= 2) {
            try {
                result[0] = Integer.parseInt(numbers[0]); // x1
                result[1] = Integer.parseInt(numbers[1]); // x2
            } catch (NumberFormatException e) {
                System.out.println("輸入格式錯誤，請確保輸入的是數字。");
                return readTwoNumbers();
            }
        } else {
            System.out.println("請確保輸入兩個數字，並用空白隔開。");
            return readTwoNumbers();
        }

        return result;
    }
}
