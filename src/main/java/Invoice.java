import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Invoice {
    public String customer;
    public List<Performance> performances;

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    private void calculateRevenueAndCredits(HashMap<String, Play> plays, StringBuilder stringBuilder, NumberFormat formatter) {
        float totalAmount = 0;
        int volumeCredits = 0;

        for (Performance performance : performances) {
            Play play = plays.get(performance.playID);
            float price = 0;

            switch (play.type) {
                case TRAGEDY:
                    price = 400;
                    if (performance.audience > 30) {
                        price += 10 * (performance.audience - 30);
                    }
                    break;
                case COMEDY:
                    price = 300;
                    if (performance.audience > 20) {
                        price += 100 + 5 * (performance.audience - 20);
                    }
                    price += 3 * performance.audience;
                    break;
                default:
                    throw new Error("Unknown type: " + play.type);
            }

            volumeCredits += Math.max(performance.audience - 30, 0);
            if (Play.PlayType.COMEDY.equals(play.type)) {
                volumeCredits += Math.floor(performance.audience / 5);
            }

            stringBuilder.append(String.format("  %s: %s (%s seats)\n", play.name, formatter.format(price), performance.audience));
            totalAmount += price;
        }

        stringBuilder.append(String.format("Amount owed is %s\n", formatter.format(totalAmount)));
        stringBuilder.append(String.format("You earned %s credits\n", volumeCredits));
    }

    public String printRevenue(HashMap<String, Play> plays) {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", customer));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        calculateRevenueAndCredits(plays, result, formatter);
        return result.toString();
    }

    public String toHTML(HashMap<String, Play> plays, String filePath) {
        StringBuilder htmlContent = new StringBuilder("<html><head><title>Statement</title></head><body>");
        htmlContent.append(String.format("<h1>Statement for %s</h1>", customer));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        calculateRevenueAndCredits(plays, htmlContent, formatter);
        htmlContent.append("</body></html>");
        return htmlContent.toString();
    }
}
