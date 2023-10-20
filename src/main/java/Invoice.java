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

    private void calculateRevenueAndCredits(StringBuilder stringBuilder, NumberFormat formatter, boolean isHTML) {
        float totalAmount = 0;
        int volumeCredits = 0;

        for (Performance performance : performances) {
            Play play = performance.play;
            float price = play.calculatePrice(performance);
            volumeCredits += play.calculateCredits(performance);

            if (isHTML) {
                stringBuilder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                                                    play.name, performance.audience, formatter.format(price)));
            } else {
                stringBuilder.append(String.format("  %s: %s (%s seats)\n", play.name, formatter.format(price), performance.audience));
            }

            totalAmount += price;
        }

        if (isHTML) {
            stringBuilder.append(String.format("<tr class=\"total\"><td colspan=\"2\">Total owed:</td><td>%s</td></tr>", formatter.format(totalAmount)));
            stringBuilder.append(String.format("<p>You earned %s credits</p>", volumeCredits));
        } else {
            stringBuilder.append(String.format("Amount owed is %s\n", formatter.format(totalAmount)));
            stringBuilder.append(String.format("You earned %s credits\n", volumeCredits));
        }
    }

    public String toText() {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", customer));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        calculateRevenueAndCredits(result, formatter, false);
        return result.toString();
    }

    public String toHTML() {
        StringBuilder htmlContent = new StringBuilder("<!DOCTYPE html><html lang=\"en\"><head><title>Statement</title>");
        htmlContent.append("<style>");
        htmlContent.append("body { font-family: Arial, sans-serif; width: 60%; margin: auto; border: 1px solid #000; padding: 20px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }");
        htmlContent.append("h1 { text-align: center; }");
        htmlContent.append("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        htmlContent.append("th, td { border: 1px solid #000; padding: 10px; }");
        htmlContent.append("td { text-align: center; }");
        htmlContent.append(".total { font-weight: bold; }");
        htmlContent.append(".warning { margin-top: 30px; color: red; font-weight: bold; font-style: italic; }");
        htmlContent.append("</style>");
        htmlContent.append("</head><body>");

        htmlContent.append(String.format("<h1>Statement for %s</h1>", customer));
        htmlContent.append("<table><tr><th>Piece</th><th>Seats sold</th><th>Price</th></tr>");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        calculateRevenueAndCredits(htmlContent, formatter, true);
        htmlContent.append("</table>");
        htmlContent.append("<p class=\"warning\">Payment is required under 30 days. We can siphon your SOUL if you don't do so.</p>");
        htmlContent.append("</body></html>");
        return htmlContent.toString();
    }
    }

