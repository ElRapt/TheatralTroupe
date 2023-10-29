public class Tragedy extends Play {
    private static final int BASE_PRICE = 400;
    private static final int AUDIENCE_THRESHOLD = 30;
    private static final int EXTRA_COST_PER_AUDIENCE = 10;

    public Tragedy(String name) {
        super(name);
    }

    @Override
    public float calculatePrice(Performance performance) {
        int price = BASE_PRICE;
        if (performance.getAudience() > AUDIENCE_THRESHOLD) {
            price += EXTRA_COST_PER_AUDIENCE * (performance.getAudience() - AUDIENCE_THRESHOLD);
        }
        return price;
    }
}