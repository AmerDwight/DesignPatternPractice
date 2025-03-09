package indv.amer.task2.answer;

import org.apache.commons.lang3.StringUtils;

public class CountNumberOfWaterballs extends SearchTemplate<Integer> {

    private final String ON_SEARCHING = "Waterball";

    @Override
    protected Integer defaultResult() {
        return 0;
    }

    @Override
    Integer updateResultLogics(int index, String[] strings) {
        if (StringUtils.isNoneEmpty(strings[index])) {
            if (StringUtils.contains(strings[index], ON_SEARCHING)) {
                this.result += 1;
            }
        }
        return result;
    }
}
