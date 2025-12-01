public abstract class Exercise {

  private final String name;
  private final int targetMinutesPerDay;

  protected Exercise(String name, int targetMinutesPerDay) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("name must not be blank");
    }
    if (targetMinutesPerDay <= 0) {
      throw new IllegalArgumentException("targetMinutesPerDay must be > 0");
    }

    this.name = name;
    this.targetMinutesPerDay = targetMinutesPerDay;
  }

  public String getName() {
    return name;
  }

  public int getTargetMinutesPerDay() {
    return targetMinutesPerDay;
  }

  // Must be overridden by subclasses (Scale, Song, Arpeggio, etc.)
  public abstract String getCategory();
}

