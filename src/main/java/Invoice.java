import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Invoice {
    public Customer customer;
    public List<Performance> performances;

    public Invoice(Customer customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    private float calculateTotalAmount() {
        float totalAmount = 0;
        for (Performance performance : performances) {
            totalAmount += performance.play.calculatePrice(performance);
        }
        if(customer.getFidelityPoints() >= 150)
        {
            customer.applyDiscount();
            totalAmount -= 15;
        }
        return totalAmount;
    }

    private int calculateTotalVolumeFidelityPoints() {
        int volumeCredits = 0;
        for (Performance performance : performances) {
            volumeCredits += performance.play.calculateFidelityPoints(performance);
        }
        return volumeCredits;
    }

    private void appendPerformanceLine(StringBuilder builder, Performance performance, NumberFormat formatter) {
        float price = performance.play.calculatePrice(performance);
        builder.append(String.format("  %s: %s (%s seats)\n", performance.play.name, formatter.format(price), performance.audience));
    }


    private void appendHtmlPerformanceLine(StringBuilder builder, Performance performance, NumberFormat formatter) {
        float price = performance.play.calculatePrice(performance);
        builder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", performance.play.name, performance.audience, formatter.format(price)));
    }

    public String toText() {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", customer.name));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        for (Performance performance : performances) {
            appendPerformanceLine(result, performance, formatter);
        }
        result.append(String.format("Amount owed is %s\n", formatter.format(calculateTotalAmount())));
        result.append(String.format("You earned %s fidelity points\n", calculateTotalVolumeFidelityPoints()));
        this.customer.addFidelityPoints(calculateTotalVolumeFidelityPoints());
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
        
        htmlContent.append(String.format("<h1>Statement for %s</h1>", customer.name));
        htmlContent.append("<table><tr><th>Piece</th><th>Seats sold</th><th>Price</th></tr>");
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        for (Performance performance : performances) {
            appendHtmlPerformanceLine(htmlContent, performance, formatter);
        }
        
        htmlContent.append(String.format("<tr class=\"total\"><td colspan=\"2\">Total owed:</td><td>%s</td></tr>", formatter.format(calculateTotalAmount())));
        htmlContent.append(String.format("<p>You earned %s fidelity points</p>", calculateTotalVolumeFidelityPoints()));
        htmlContent.append("</table>");
        htmlContent.append("<p class=\"warning\">Payment is required under 30 days. We can siphon your SOUL if you don't do so.</p>");
        htmlContent.append("</body></html>");

        return htmlContent.toString();
    }
}
