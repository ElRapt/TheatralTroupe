public class Comedy extends Play {
    private static final int BASE_PRICE = 300;
    private static final int AUDIENCE_THRESHOLD = 20;
    private static final int EXTRA_COST_PER_AUDIENCE = 5;

    public Comedy(String name) {
        super(name);
    }

    @Override
    public float calculatePrice(Performance performance) {
        int price = BASE_PRICE;
        if (performance.getAudience() > AUDIENCE_THRESHOLD) {
            price += 100 + EXTRA_COST_PER_AUDIENCE * (performance.getAudience() - AUDIENCE_THRESHOLD);
        }
        price += 3 * performance.getAudience();
        return price;
    }

    @Override
    public int calculateFidelityPoints(Performance performance) {
        int volumeCredits = super.calculateFidelityPoints(performance);
        return volumeCredits + (int) Math.floor(performance.getAudience() / 5);
    }
}