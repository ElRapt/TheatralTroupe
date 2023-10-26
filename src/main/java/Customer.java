import java.util.UUID;

public class Customer {
    public String name;
    private UUID clientId;
    private int fidelityPoints;

    public Customer(String name, UUID clientId, int fidelityPoints) {
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
        fidelityPoints -= 150;
    }

    public int getFidelityPoints() {
        return fidelityPoints;
    }
}

