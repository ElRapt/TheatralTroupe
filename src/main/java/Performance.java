public class Performance {

    public Play play;
    public int audience;

    public Performance(Play play, int audience) {
        if (audience < 0) {
            throw new IllegalArgumentException("Audience cannot be negative");
        }

        this.play = play;
        this.audience = audience;
    }
}
