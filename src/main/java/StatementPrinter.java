import java.text.NumberFormat;
import java.util.Locale;

public final class StatementPrinter {

    private static final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public StatementPrinter() {
    }

    private static void appendPerformanceLine(StringBuilder builder, Performance performance, NumberFormat formatter) {
        float price = performance.calculatePrice();
        builder.append(String.format("  %s: %s (%s seats)\n", performance.getPlayName(), formatter.format(price), performance.getAudience()));
    }

    private static void appendHtmlPerformanceLine(StringBuilder builder, Performance performance, NumberFormat formatter) {
        float price = performance.calculatePrice();
        builder.append(String.format("    <tr><td>%s</td><td>%s</td><td>%s</td></tr>\n", performance.getPlayName(), performance.getAudience(), formatter.format(price)));
    }

    private static void appendHtmlCss(StringBuilder htmlContent) {
        htmlContent.append("    body { font-family: Arial, sans-serif; width: 60%; margin: auto; border: 1px solid #000; padding: 20px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }\n");
        htmlContent.append("    h1 { text-align: center; }\n");
        htmlContent.append("    table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n");
        htmlContent.append("    th, td { border: 1px solid #000; padding: 10px; }\n");
        htmlContent.append("    td { text-align: center; }\n");
        htmlContent.append("    .total { font-weight: bold; }\n");
        htmlContent.append("    .warning { margin-top: 30px; color: red; font-weight: bold; font-style: italic; }\n");
    }

    private static void appendHtmlHeader(StringBuilder htmlContent) {
        htmlContent.append("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n  <title>Statement</title>\n  <style>\n");
        appendHtmlCss(htmlContent);
        htmlContent.append("  </style>\n</head>\n<body>\n");
    }

    private static void appendHtmlFooter(StringBuilder htmlContent) {
        htmlContent.append("</body>\n</html>\n");
    }

    public static String toHTML(Invoice invoice) {
        invoice.calculateAndStoreTotals();
        StringBuilder htmlContent = new StringBuilder();
        appendHtmlHeader(htmlContent);
        htmlContent.append(String.format("  <h1>Statement for %s</h1>\n", invoice.getCustomerName()));
        htmlContent.append("  <table>\n    <tr><th>Piece</th><th>Seats sold</th><th>Price</th></tr>\n");

        for (Performance performance : invoice.getPerformances()) {
            appendHtmlPerformanceLine(htmlContent, performance, formatter);
        }

        htmlContent.append(String.format("    <tr class=\"total\"><td colspan=\"2\">Total owed:</td><td>%s</td></tr>\n", formatter.format(invoice.getAmountToPay())));
        htmlContent.append(String.format("  <p>You earned %s fidelity points</p>\n", invoice.getEarnedFidelityPoints()));
        htmlContent.append("  </table>\n");
        htmlContent.append("  <p class=\"warning\">Payment is required under 30 days. We can siphon your SOUL if you don't do so.</p>\n");
        appendHtmlFooter(htmlContent);

        return htmlContent.toString();
    }

    public static String toText(Invoice invoice) {
        invoice.calculateAndStoreTotals();
        StringBuilder result = new StringBuilder();
        result.append(String.format("Statement for %s\n", invoice.getCustomerName()));

        for (Performance performance : invoice.getPerformances()) {
            appendPerformanceLine(result, performance, formatter);
        }

        result.append(String.format("Amount owed is %s\n", formatter.format(invoice.getAmountToPay())));
        result.append(String.format("You earned %s fidelity points\n", invoice.getEarnedFidelityPoints()));

        return result.toString();
    }
}
