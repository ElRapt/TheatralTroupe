import java.util.UUID;

public class Customer {
    public String name;
    private UUID clientId;
    private int fidelityPoints;

    public Customer(String name, UUID clientId, int fidelityPoints) {
        if (name == null || clientId == null) {
            throw new IllegalArgumentException("Name and clientId cannot be null");
        }
        this.name = name;
        this.clientId = clientId;
        this.fidelityPoints = fidelityPoints;
    }

    // Default fidelity points is 0
    public Customer(String name, UUID clientId) {
        this(name, clientId, 0);  
    }

    public void addFidelityPoints(int points) {
        fidelityPoints += points;
    }

    public void applyDiscount() {
        if (this.fidelityPoints < 150) {
            throw new IllegalStateException("Not enough points for discount");
        }
        fidelityPoints -= 150;
    }

    public int getFidelityPoints() {
        return fidelityPoints;
    }
}

