public abstract class Play {

  private String name;

  public Play(String name){
    this.name = name;
  }

  public abstract float calculatePrice(Performance performance); 

  public int calculateFidelityPoints(Performance performance)
  {
    return (Math.max(performance.getAudience() - 30, 0));
  } 

  public String getName() {
    return name;
  }
  
}
