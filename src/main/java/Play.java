public abstract class Play {

  public String name;

  public Play(String name){
    this.name = name;
  }

  public abstract float calculatePrice(Performance performance); 

  public int calculateCredits(Performance performance)
  {
    return (Math.max(performance.audience - 30, 0));
  } 
}
