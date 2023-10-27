public class Performance {

    public Play play;
    public int audience;

    public Performance(Play play, int audience) {
        if (play == null) {
            throw new IllegalArgumentException("Play cannot be null");
        }
        if (audience < 0) {
            throw new IllegalArgumentException("Audience cannot be negative");
        }
        this.play = play;
        this.audience = audience;
    }
}
