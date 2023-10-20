import org.junit.jupiter.api.Test;
import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class StatementPrinterTests {

  @Test
  void exampleStatement(){
      Play hamlet = new Tragedy("Hamlet");
      Play asYouLikeIt = new Comedy("As You Like It");
      Play othello = new Tragedy("Othello");

      Invoice invoice = new Invoice("BigCo", List.of(
              new Performance(hamlet, 55),
              new Performance(asYouLikeIt, 35),
              new Performance(othello, 40)
      ));

      String result = invoice.toText();
      verify(result);
  }

  @Test
  void exampleHtmlStatement(){
      Play hamlet = new Tragedy("Hamlet");
      Play asYouLikeIt = new Comedy("As You Like It");
      Play othello = new Tragedy("Othello");

      Invoice invoice = new Invoice("BigCo", List.of(
              new Performance(hamlet, 55),
              new Performance(asYouLikeIt, 35),
              new Performance(othello, 40)
      ));

      String result = invoice.toHTML();
      verify(result);
  }

  }


