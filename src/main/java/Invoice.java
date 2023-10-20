import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Invoice {

    public String customer;
    public List<Performance> performances;

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    private void calculateRevenueAndCredits(StringBuilder stringBuilder, NumberFormat formatter) {
        float totalAmount = 0;
        int volumeCredits = 0;

        for (Performance performance : performances) {
            Play play = performance.play;
            float price = 0;
            price = play.calculatePrice(performance);

            volumeCredits += play.calculateCredits(performance);


            stringBuilder.append(String.format("  %s: %s (%s seats)\n", play.name, formatter.format(price), performance.audience));
            totalAmount += price;
        }

        stringBuilder.append(String.format("Amount owed is %s\n", formatter.format(totalAmount)));
        stringBuilder.append(String.format("You earned %s credits\n", volumeCredits));
    }

    public String toText() {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", customer));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        calculateRevenueAndCredits(result, formatter);
        return result.toString();
    }

    public String toHTML() {
        StringBuilder htmlContent = new StringBuilder("<html><head><title>Statement</title></head><body>");
        htmlContent.append(String.format("<h1>Statement for %s</h1>", customer));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        calculateRevenueAndCredits(htmlContent, formatter);
        htmlContent.append("</body></html>");
        return htmlContent.toString();
    }
}
