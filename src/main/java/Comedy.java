public class Comedy extends Play{
    public Comedy(String name){
        super(name);
      }

    public float calculatePrice(Performance performance)
    {
        int price = 300;
        if (performance.getAudience() > 20) {
            price += 100 + 5 * (performance.getAudience() - 20);
        }
        price += 3 * performance.getAudience();
        return price;
    } 

   @Override
   public int calculateFidelityPoints(Performance performance)
   {
    int volumeCredits = (Math.max(performance.getAudience() - 30, 0));
    return volumeCredits  + (int)(Math.floor(performance.getAudience() / 5));
}
   }  

