import java.text.NumberFormat;
import java.util.Locale;

public final class StatementPrinter {

    private static final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public StatementPrinter() {
    }


    // Helper method to make the code more readable
    public static void appendPerformanceLine(StringBuilder builder, Performance performance, NumberFormat formatter) {
        float price = performance.calculatePrice();
        builder.append(String.format("  %s: %s (%s seats)\n", performance.getPlayName(), formatter.format(price), performance.getAudience()));
    }

    // Helper method to make the code more readable
    public static void appendHtmlPerformanceLine(StringBuilder builder, Performance performance, NumberFormat formatter) {
        float price = performance.calculatePrice();
        builder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", performance.getPlayName(), performance.getAudience(), formatter.format(price)));
    }

    public static String toHTML(Invoice invoice) {
        StringBuilder htmlContent = new StringBuilder();

        htmlContent.append("<!DOCTYPE html><html lang=\"en\"><head><title>Statement</title>");
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
        
        htmlContent.append(String.format("<h1>Statement for %s</h1>", invoice.getCustomerName()));
        htmlContent.append("<table><tr><th>Piece</th><th>Seats sold</th><th>Price</th></tr>");
        
        for (Performance performance : invoice.getPerformances()) {
            appendHtmlPerformanceLine(htmlContent, performance, formatter);
        }
        
        htmlContent.append(String.format("<tr class=\"total\"><td colspan=\"2\">Total owed:</td><td>%s</td></tr>", formatter.format(invoice.calculateTotalAmount())));
        htmlContent.append(String.format("<p>You earned %s fidelity points</p>", invoice.calculateTotalVolumeFidelityPoints()));
        htmlContent.append("</table>");
        htmlContent.append("<p class=\"warning\">Payment is required under 30 days. We can siphon your SOUL if you don't do so.</p>");
        htmlContent.append("</body></html>");

        return htmlContent.toString();
    }

    public static String toText(Invoice invoice) {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Statement for %s\n", invoice.getCustomerName()));
        for (Performance performance : invoice.getPerformances()) {
            appendPerformanceLine(result, performance, formatter);
        }

        result.append(String.format("Amount owed is %s\n", formatter.format(invoice.calculateTotalAmount())));
        result.append(String.format("You earned %s fidelity points\n", invoice.calculateTotalVolumeFidelityPoints()));

        return result.toString();
    }
}
