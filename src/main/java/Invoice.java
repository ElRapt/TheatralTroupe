import java.io.FileWriter;
import java.io.IOException;
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

    // Extracted method for shared calculation logic
    private float calculatePriceToPay(Play play, Performance performance) {
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
        return price;
    }

    public String printRevenue(HashMap<String, Play> plays) {
        float totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", this.customer));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : this.performances) {
            Play play = plays.get(performance.playID);
            float priceToPay = calculatePriceToPay(play, performance);

            volumeCredits += Math.max(performance.audience - 30, 0);
            if (Play.PlayType.COMEDY.equals(play.type)) volumeCredits += Math.floor(performance.audience / 5);

            result.append(String.format("  %s: %s (%s seats)\n", play.name, formatter.format(priceToPay), performance.audience));
            totalAmount += priceToPay;
        }
        result.append(String.format("Amount owed is %s\n", formatter.format(totalAmount)));
        result.append(String.format("You earned %s credits\n", volumeCredits));
        return result.toString();
    }

    public String toHTML(HashMap<String, Play> plays, String filePath) {
        float totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder htmlContent = new StringBuilder("<html><head><title>Statement</title></head><body>");
        htmlContent.append(String.format("<h1>Statement for %s</h1>", this.customer));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : this.performances) {
            Play play = plays.get(performance.playID);
            float priceToPay = calculatePriceToPay(play, performance);

            volumeCredits += Math.max(performance.audience - 30, 0);
            if (Play.PlayType.COMEDY.equals(play.type)) volumeCredits += Math.floor(performance.audience / 5);

            htmlContent.append(String.format("<p>%s: %s (%s seats)</p>", play.name, formatter.format(priceToPay), performance.audience));
            totalAmount += priceToPay;
        }
        htmlContent.append(String.format("<p>Amount owed is %s</p>", formatter.format(totalAmount)));
        htmlContent.append(String.format("<p>You earned %s credits</p>", volumeCredits));
        htmlContent.append("</body></html>");

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(htmlContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlContent.toString();
    }
}
