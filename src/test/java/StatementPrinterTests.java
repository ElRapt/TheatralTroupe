import org.junit.jupiter.api.Test;
import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import java.util.Arrays;
import java.util.List;


public class StatementPrinterTests {

    Customer BigCo = new Customer("BigCo", UUID.randomUUID());
    Customer BigCoFidel = new Customer("BigCo", UUID.randomUUID(), 151);
    Customer BigCoWillBeFidel = new Customer("BigCo", UUID.randomUUID(), 131);

    StatementPrinter printer = new StatementPrinter();

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

        String result = StatementPrinter.toText(invoice);
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

        String result = StatementPrinter.toHTML(invoice);
        verify(result);
    }

    @Test
    void testToTextInvoiceWillBeFidelity(){
        Play hamlet = new Tragedy("Hamlet");
        Play asYouLikeIt = new Comedy("As You Like It");
        Play othello = new Tragedy("Othello");

        Invoice invoice = new Invoice(BigCoWillBeFidel, List.of(
                new Performance(hamlet, 55),
                new Performance(asYouLikeIt, 35),
                new Performance(othello, 40)
        ));

        String result = StatementPrinter.toText(invoice);
        verify(result);
    }

    @Test
    void testToHTMLInvoiceWillBeFidelity(){
        Play hamlet = new Tragedy("Hamlet");
        Play asYouLikeIt = new Comedy("As You Like It");
        Play othello = new Tragedy("Othello");

        Invoice invoice = new Invoice(BigCoWillBeFidel, List.of(
                new Performance(hamlet, 55),
                new Performance(asYouLikeIt, 35),
                new Performance(othello, 40)
        ));

        String result = StatementPrinter.toHTML(invoice);
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

        String result = StatementPrinter.toText(invoice);
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

        String result = StatementPrinter.toHTML(invoice);
        verify(result);
    }


    // Unit tests

  @Test
    void testExceptionNegativeAudiencePerformance() {
        Play hamlet = new Tragedy("Hamlet");
        assertThrows(IllegalArgumentException.class, () -> {
            new Performance(hamlet, -55);
        });
    }

    @Test
    void testExceptionNullNameCustomer() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(null, UUID.randomUUID(), 0);
        });
    }

    @Test
    void testExceptionNullClientIdCustomer() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("John", null, 0);
        });
    }

    @Test
    void testExceptionNullCustomerInvoice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Invoice(null, Arrays.asList(new Performance(new Comedy("Funny Comedy"), 20)));
        });
    }

    @Test
    void testExceptionNullPerformancesInvoice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Invoice(new Customer("John", UUID.randomUUID(), 0), null);
        });
    }

    @Test
    void testExceptionEmptyPerformancesInvoice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Invoice(new Customer("John", UUID.randomUUID(), 0), Arrays.asList());
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

    @Test
    void testInvalidFidelityPoints(){
        Customer customer = new Customer("John", UUID.randomUUID(), 0);
        assertThrows(IllegalStateException.class, () -> {
            customer.applyDiscount();
        });
    }

    @Test
    void testCalculateFidelityPointsTragedy(){
        Play hamlet = new Tragedy("Hamlet");
        Performance performance = new Performance(hamlet, 55);
        assertEquals(25, hamlet.calculateFidelityPoints(performance));
    }

    @Test
    void testCalculateFidelityPointsComedy(){
        Play asYouLikeIt = new Comedy("As You Like It");
        Performance performance = new Performance(asYouLikeIt, 35);
        assertEquals(12, asYouLikeIt.calculateFidelityPoints(performance));
    }

    @Test
    void testCalculateAndStoreTotals(){
        Play hamlet = new Tragedy("Hamlet");
        Play asYouLikeIt = new Comedy("As You Like It");
        Play othello = new Tragedy("Othello");

        Invoice invoice = new Invoice(BigCo, List.of(
                new Performance(hamlet, 55),
                new Performance(asYouLikeIt, 35),
                new Performance(othello, 40)
        ));

        invoice.calculateAndStoreTotals();
        assertEquals(650 + 580 + 500, invoice.getAmountToPay());
        assertEquals(25 + 12 + 10, invoice.getEarnedFidelityPoints());
    }
  }

