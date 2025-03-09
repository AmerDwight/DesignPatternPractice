package indv.amer.task2.answer;

import java.util.Arrays;

public abstract class SearchTemplate<T> {
    protected T result;

    public T search(String[] strings) {
        result = defaultResult();

        for (int idx = 0; idx < strings.length; idx++) {
            result = updateResultLogics(idx, strings);
            System.out.println(strings[idx]);
            if (breakLoopJudgement(idx, strings)) {
                break;
            }

        }
        return result;
    }

    protected T defaultResult() {
        return null;
    }

    protected boolean breakLoopJudgement(int index, String[] strings) {
        return false;
    }

    abstract T updateResultLogics(int index, String[] strings);


}
