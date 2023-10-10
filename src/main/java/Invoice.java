import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

public class Invoice {

  public String customer;
  public List<Performance> performances;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public String printRevenue(HashMap<String, Play> plays) {
    
  float totalAmount = 0;
  int volumeCredits = 0;
  StringBuffer result = new StringBuffer(String.format("Statement for %s\n", this.customer));
  // StringBuffer prevents creation of a new string object each concatenation

  NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

  for (Performance perf : this.performances) {
    Play play = plays.get(perf.playID);
    float priceToPay = 0;

    switch (play.type) { //TODO : Polymormphism en fiat
      case TRAGEDY:
        priceToPay = 400;
        if (perf.audience > 30) {
          priceToPay += 10 * (perf.audience - 30);
        }
        break;
      case COMEDY:
        priceToPay = 300;
        if (perf.audience > 20) {
          priceToPay += 100 + 5 * (perf.audience - 20);
        }
        priceToPay += 3 * perf.audience;
        break;
      default:
        throw new Error("unknown type: ${play.type}");
    }

    // add volume credits
    volumeCredits += Math.max(perf.audience - 30, 0);
    // add extra credit for every ten comedy attendees
    if (Play.PlayType.COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

    // print line for this order
    result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(priceToPay), perf.audience));
    totalAmount += priceToPay;
  }
  result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
  result.append( String.format("You earned %s credits\n", volumeCredits));
  return result.toString();
}

  public void toHTML(HashMap<String, Play> plays, String filePath) {
    float totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder htmlContent = new StringBuilder("<html><head><title>Statement</title></head><body>");
    htmlContent.append(String.format("<h1>Statement for %s</h1>", this.customer));
    
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    
    for (Performance perf : this.performances) {
      Play play = plays.get(perf.playID);
      float priceToPay = 0;

      switch (play.type) { 
        case TRAGEDY:
          priceToPay = 400;
          if (perf.audience > 30) {
            priceToPay += 10 * (perf.audience - 30);
          }
          break;
        case COMEDY:
          priceToPay = 300;
          if (perf.audience > 20) {
            priceToPay += 100 + 5 * (perf.audience - 20);
          }
          priceToPay += 3 * perf.audience;
          break;
        default:
          throw new Error("unknown type: " + play.type);
      }
      
      // Add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      if (Play.PlayType.COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      htmlContent.append(String.format("<p>%s: %s (%s seats)</p>", play.name, frmt.format(priceToPay), perf.audience));
      totalAmount += priceToPay;
    }

    htmlContent.append(String.format("<p>Amount owed is %s</p>", frmt.format(totalAmount)));
    htmlContent.append(String.format("<p>You earned %s credits</p>", volumeCredits));
    htmlContent.append("</body></html>");

    try (FileWriter fileWriter = new FileWriter(filePath)) {
      fileWriter.write(htmlContent.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

