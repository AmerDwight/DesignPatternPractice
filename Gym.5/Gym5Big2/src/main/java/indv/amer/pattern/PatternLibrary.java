package indv.amer.pattern;

public enum PatternLibrary {
    Single("單張"),
    Pair("對子"),
    Straight("順子"),
    FullHouse("葫蘆");

    private final String chineseName;

    PatternLibrary(String _chineseName) {
        this.chineseName = _chineseName;
    }

    public String getChinese() {
        return chineseName;
    }
}
