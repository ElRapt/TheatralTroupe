import org.junit.jupiter.api.Test;
import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

import java.util.List;


public class StatementPrinterTests {

    Customer BigCo = new Customer("BigCo", UUID.randomUUID());
    Customer BigCoFidel = new Customer("BigCo", UUID.randomUUID(), 151);

    // Acceptation tests

    @Test
    void testToTextInvoiceNoFidelity(){
        Play hamlet = new Tragedy("Hamlet");
        Play asYouLikeIt = new Comedy("As You Like It");
        Play othello = new Tragedy("Othello");

        Invoice invoice = new Invoice(BigCo, List.of(
                new Performance(hamlet, 55),
                new Performance(asYouLikeIt, 35),
                new Performance(othello, 40)
        ));

        String result = invoice.toText();
        verify(result);
    }

    @Test
    void testToHTMLInvoiceNoFidelity(){
        Play hamlet = new Tragedy("Hamlet");
        Play asYouLikeIt = new Comedy("As You Like It");
        Play othello = new Tragedy("Othello");

        Invoice invoice = new Invoice(BigCo, List.of(
                new Performance(hamlet, 55),
                new Performance(asYouLikeIt, 35),
                new Performance(othello, 40)
        ));

        String result = invoice.toHTML();
        verify(result);
    }


    @Test
    void testToTextInvoiceFidelity(){
        Play hamlet = new Tragedy("Hamlet");
        Play asYouLikeIt = new Comedy("As You Like It");
        Play othello = new Tragedy("Othello");

        Invoice invoice = new Invoice(BigCoFidel, List.of(
                new Performance(hamlet, 55),
                new Performance(asYouLikeIt, 35),
                new Performance(othello, 40)
        ));

        String result = invoice.toText();
        verify(result);
    }

    @Test
    void testToHTMLInvoiceFidelity(){
        Play hamlet = new Tragedy("Hamlet");
        Play asYouLikeIt = new Comedy("As You Like It");
        Play othello = new Tragedy("Othello");

        Invoice invoice = new Invoice(BigCoFidel, List.of(
                new Performance(hamlet, 55),
                new Performance(asYouLikeIt, 35),
                new Performance(othello, 40)
        ));

        String result = invoice.toHTML();
        verify(result);
    }


    // Unit tests

    @Test
    void testExceptionNegativeAudience(){
        Play hamlet = new Tragedy("Hamlet");
        assertThrows(IllegalArgumentException.class, () -> {
            new Performance(hamlet, -55);
        });
    }
    
    @Test
    void testCalculatePriceTragedy(){
        Play hamlet = new Tragedy("Hamlet");
        Performance performance = new Performance(hamlet, 55);
        assertEquals(650, hamlet.calculatePrice(performance));
    }

    @Test
    void testCalculatePriceComedy(){
        Play asYouLikeIt = new Comedy("As You Like It");
        Performance performance = new Performance(asYouLikeIt, 35);
        assertEquals(580, asYouLikeIt.calculatePrice(performance));
    }


  }

