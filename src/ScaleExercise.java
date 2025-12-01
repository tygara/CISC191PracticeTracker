public class ScaleExercise extends Exercise {

  private final String scaleName;
  private final String key;
  private final int targetTempoBpm;

  public ScaleExercise(String name,
      int targetMinutesPerDay,
      String scaleName,
      String key,
      int targetTempoBpm) {
    super(name, targetMinutesPerDay);

    if (scaleName == null || scaleName.isBlank()) {
      throw new IllegalArgumentException("scaleName must not be blank");
    }
    if (key == null || key.isBlank()) {
      throw new IllegalArgumentException("key must not be blank");
    }
    if (targetTempoBpm <= 0) {
      throw new IllegalArgumentException("targetTempoBpm must be > 0");
    }

    this.scaleName = scaleName;
    this.key = key;
    this.targetTempoBpm = targetTempoBpm;
  }

  public String getScaleName() {
    return scaleName;
  }

  public String getKey() {
    return key;
  }

  public int getTargetTempoBpm() {
    return targetTempoBpm;
  }

  @Override
  public String getCategory() {
    return "Scale";
  }
}
