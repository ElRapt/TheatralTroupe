public class Performance {

    private Play play;
    private int audience;

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

    public float calculatePrice() {
        return this.play.calculatePrice(this);
    }

    public int calculateFidelityPoints() {
        return this.play.calculateFidelityPoints(this);
    }

    public String getPlayName() {
        return this.play.getName();
    }

    public int getAudience() {
        return audience;
    }


}
