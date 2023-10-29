public abstract class Play {
  
  private static final int AUDIENCE_THRESHOLD = 30;
  private String name;

  public Play(String name){
    this.name = name;
  }

  public abstract float calculatePrice(Performance performance); 

  public int calculateFidelityPoints(Performance performance)
  {
    return (Math.max(performance.getAudience() - AUDIENCE_THRESHOLD, 0));
  } 

  public String getName() {
    return name;
  }

}
