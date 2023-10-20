public class Tragedy extends Play{
    public Tragedy(String name){
        super(name);
      }

    public float calculatePrice(Performance performance)
    {
        int price = 400;
        if (performance.audience > 30) {
            price += 10 * (performance.audience - 30);
        }
        return price;
    } 
}
