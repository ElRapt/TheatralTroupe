import java.util.List;

public class Invoice {
    private Customer customer;
    private List<Performance> performances;
    private float amountToPay;
    private int earnedFidelityPoints;

    public Invoice(Customer customer, List<Performance> performances) {
        if (customer == null || performances == null || performances.isEmpty()) {
            throw new IllegalArgumentException("Customer and performances cannot be null or empty");
        }
        this.customer = customer;
        this.performances = performances;
    }
    
    // Allows us to calculate the total amount and volume fidelity points before the discount is applied
    public void calculateAndStoreTotals() {
        this.amountToPay = calculateTotalAmount();
        this.earnedFidelityPoints = calculateTotalVolumeFidelityPoints();
        this.customer.addFidelityPoints(earnedFidelityPoints);
        if (customer.getFidelityPoints() >= 150) {
            applyDiscount();
        }
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


    public float getAmountToPay() {
        return amountToPay;
    }

    public int getEarnedFidelityPoints() {
        return earnedFidelityPoints;
    }

    public String getCustomerName() {
        return customer.name;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void applyDiscount() {
        this.customer.applyDiscount();
        this.amountToPay -= 15;
    }



}