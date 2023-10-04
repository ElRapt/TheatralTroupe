import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {

        HashMap<String, Play> plays = new HashMap<>();
        try {
            plays.put("hamlet",  new Play("Hamlet", Play.PlayType.TRAGEDY));
            plays.put("as-like",  new Play("As You Like It", Play.PlayType.COMEDY));
            plays.put("othello",  new Play("Othello", Play.PlayType.TRAGEDY));
        } catch(Play.InvalidPlayTypeException e)
        {
            e.printStackTrace();
        } 

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {

        HashMap<String, Play> plays = new HashMap<>();
        try {

            plays.put("henry-v",  new Play("Henry V", Play.PlayType.HISTORY));
            plays.put("as-like",  new Play("As You Like It", Play.PlayType.PASTORAL));
        } catch (Play.InvalidPlayTypeException e)
        {
            e.printStackTrace();
        } 
            
            Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("henry-v", 53),
                new Performance("as-like", 55)));

        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertThrows(Error.class, () -> {
            statementPrinter.print(invoice, plays);
        });
    }

    @Test
    void throwsInvalidPlayType() {
      assertThrows(Play.InvalidPlayTypeException.class, () -> {
        new Play("Hamlet", null);
      });
    }
  }


