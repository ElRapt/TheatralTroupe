public class Play {


  public enum PlayType {
    TRAGEDY,
    COMEDY,
    HISTORY,
    PASTORAL
  }

  public String name;
  public PlayType type;

  public Play(String name, PlayType type) {
    this.name = name;
    this.type = type;
  }
}
