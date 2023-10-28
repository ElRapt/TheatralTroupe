import java.util.List;

public class Invoice {
    public Customer customer;
    public List<Performance> performances;

    public Invoice(Customer customer, List<Performance> performances) {
        if (customer == null || performances == null || performances.isEmpty()) {
            throw new IllegalArgumentException("Customer and performances cannot be null or empty");
        }
        this.customer = customer;
        this.performances = performances;
    }

    public float calculateTotalAmount() {
        float totalAmount = 0;
        for (Performance performance : performances) {
            totalAmount += performance.calculatePrice();
        }
        if(customer.getFidelityPoints() >= 150)
        {
            customer.applyDiscount();
            totalAmount -= 15;
        }
        return totalAmount;
    }

    public int calculateTotalVolumeFidelityPoints() {
        int volumeCredits = 0;
        for (Performance performance : performances) {
            volumeCredits += performance.calculateFidelityPoints();
        }
        return volumeCredits;
    }

}