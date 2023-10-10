import org.junit.jupiter.api.Test;
import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class StatementPrinterTests {

  @Test
  void exampleStatement() throws Play.InvalidPlayTypeException {
      Play hamlet = new Play("Hamlet", Play.PlayType.TRAGEDY);
      Play asYouLikeIt = new Play("As You Like It", Play.PlayType.COMEDY);
      Play othello = new Play("Othello", Play.PlayType.TRAGEDY);

      Invoice invoice = new Invoice("BigCo", List.of(
              new Performance(hamlet, 55),
              new Performance(asYouLikeIt, 35),
              new Performance(othello, 40)
      ));

      String result = invoice.toText();
      verify(result);
  }

  @Test
  void exampleHtmlStatement() throws Play.InvalidPlayTypeException {
      Play hamlet = new Play("Hamlet", Play.PlayType.TRAGEDY);
      Play asYouLikeIt = new Play("As You Like It", Play.PlayType.COMEDY);
      Play othello = new Play("Othello", Play.PlayType.TRAGEDY);

      Invoice invoice = new Invoice("BigCo", List.of(
              new Performance(hamlet, 55),
              new Performance(asYouLikeIt, 35),
              new Performance(othello, 40)
      ));

      String result = invoice.toHTML();
      verify(result);
  }


    @Test
    void throwsInvalidPlayType() {
      assertThrows(Play.InvalidPlayTypeException.class, () -> {
        new Play("Hamlet", null);
      });
    }
  }


