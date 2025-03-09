package indv.amer.task2.answer;

import org.apache.commons.lang3.StringUtils;

public class SearchEmptyMessageIndex extends SearchTemplate<Integer> {
    @Override
    protected Integer defaultResult() {
        return -1;
    }

    @Override
    Integer updateResultLogics(int index, String[] strings) {
        if (this.result < 0 && StringUtils.isBlank(strings[index])) {
            this.result = index;
        }
        return index;
    }
}
