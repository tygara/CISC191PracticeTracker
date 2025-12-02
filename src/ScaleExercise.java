/**
 * Represents a scale exercise.
 * 
 * IS-A: ScaleExercise is an Exercise.
 * HAS-A: ScaleExercise has a scale name, key, and target tempo.
 */
public class ScaleExercise extends Exercise {

  private final String scaleName;
  private final String key;
  private final int targetTempoBpm;

  /**
   * Creates a new ScaleExercise.
   *
   * @param name the name of the exercise
   * @param targetMinutesPerDay daily practice target in minutes
   * @param scaleName the type of scale 
   * @param key the musical key of the scale 
   * @param targetTempoBpm the target tempo in beats per minute
   * @throws IllegalArgumentException if scaleName/key are blank or tempo is not positive
   */
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

  /**
   * Returns the scale type for this exercise.
   *
   * @return the scale name 
   */
  public String getScaleName() {
    return scaleName;
  }

  /**
   * Returns the musical key of the scale.
   *
   * @return the key 
   */
  public String getKey() {
    return key;
  }

  /**
   * Returns the target tempo in BPM.
   *
   * @return target tempo in beats per minute
   */
  public int getTargetTempoBpm() {
    return targetTempoBpm;
  }

  /**
   * Returns the exercise category.
   *
   * @return "Scale"
   */
  @Override
  public String getCategory() {
    return "Scale";
  }
}

