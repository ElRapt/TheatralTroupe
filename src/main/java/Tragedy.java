public class Tragedy extends Play{
    public Tragedy(String name){
        super(name);
      }

    public float calculatePrice(Performance performance)
    {
        int price = 400;
        if (performance.getAudience() > 30) {
            price += 10 * (performance.getAudience() - 30);
        }
        return price;
    } 
}
