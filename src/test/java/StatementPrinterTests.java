import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        var result = invoice.printRevenue(plays);

        verify(result);
    }

    @Test
    void exampleHtmlStatement() {
        HashMap<String, Play> plays = new HashMap<>();
        try {
            plays.put("hamlet", new Play("Hamlet", Play.PlayType.TRAGEDY));
            plays.put("as-like", new Play("As You Like It", Play.PlayType.COMEDY));
            plays.put("othello", new Play("Othello", Play.PlayType.TRAGEDY));
        } catch (Play.InvalidPlayTypeException e) {
            e.printStackTrace();
        }

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        String filePath = "output.html";
        invoice.toHTML(plays, filePath);

        assertTrue(Files.exists(Path.of(filePath)));
    }

    @Test
    void throwsInvalidPlayType() {
      assertThrows(Play.InvalidPlayTypeException.class, () -> {
        new Play("Hamlet", null);
      });
    }
  }


