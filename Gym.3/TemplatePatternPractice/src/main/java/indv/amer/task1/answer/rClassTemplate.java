package indv.amer.task1.answer;

public abstract class rClassTemplate {
    abstract boolean getJudgement(int first, int last);

    public void procedure(int[] ints) {
        int n = ints.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (this.getJudgement(ints[j], ints[j + 1])) {
                    int mak = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = mak;
                }
            }
        }

    }
}
