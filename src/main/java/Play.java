public class Play {

  public static class InvalidPlayTypeException extends Exception {
    public InvalidPlayTypeException(String message) {
      super(message);
    }
  }

  public enum PlayType {
    TRAGEDY,
    COMEDY,
    HISTORY,
    PASTORAL
  }

  public String name;
  public PlayType type;

  public Play(String name, PlayType type) throws InvalidPlayTypeException {
    if (type == null) {
      throw new InvalidPlayTypeException("Invalid or null PlayType provided");
    }
    this.name = name;
    this.type = type;
  }
}
