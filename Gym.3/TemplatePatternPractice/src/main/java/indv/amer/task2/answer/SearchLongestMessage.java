package indv.amer.task2.answer;

import org.apache.commons.lang3.StringUtils;

public class SearchLongestMessage extends SearchTemplate<String> {
    @Override
    protected String defaultResult() {
        return null;
    }

    @Override
    String updateResultLogics(int index, String[] strings) {
        if (this.result == null) {
            this.result = strings[index];
        } else {
            if (this.result.length() < strings[index].length()) {
                this.result = strings[index];
            }
        }
        return result;
    }
}
