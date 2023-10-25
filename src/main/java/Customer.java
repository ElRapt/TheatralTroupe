import java.util.UUID;

public class Customer {
    public String name;
    private UUID clientId;
    private int fidelityPoints;

    public Customer(String name, UUID clientId) {
        this.name = name;
        this.clientId = clientId;
    }
    public void addFidelityPoints(int points) {
        fidelityPoints += points;
    }

    public void applyDiscount() {
        fidelityPoints -= 150;
    }
}

