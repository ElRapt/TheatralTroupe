public class Comedy extends Play{
    public Comedy(String name){
        super(name);
      }

    public float calculatePrice(Performance performance)
    {
        int price = 300;
        if (performance.audience > 20) {
            price += 100 + 5 * (performance.audience - 20);
        }
        price += 3 * performance.audience;
        return price;
    } 

   @Override
   public int calculateCredits(Performance performance)
   {
    int volumeCredits = (Math.max(performance.audience - 30, 0));
    return volumeCredits  + (int)(Math.floor(performance.audience / 5));
}
   }  

