/**
 * Represents the base class for all subclasses of this exercise
 *
 * IS-A: Exercise is its own abstract practice class
 * HAS-A: name & daily practice goal in minutes
 */
public abstract class Exercise {

  private final String name;
  private final int targetMinutesPerDay;



/**
 * Constructs an Exercise with a name and daily pracitce goal in minutes
 *
 * @param name the exercise name
 * @param targetMinutesPerDay number of minutes intended to practice each day
 * @throws IllegalArgumentException if name is null/blank or targetMinutesPerDay is not positive
 */
    // Note that protected is used here as an access modifier to control who is allowed
    // to make calls to this constructor. This prevents anyone from trying to create an 
    // Exercise directly while still allowing ScaleExercise, SongExercise, and ArpeggioExercise
    // to initialize the shared fields.
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

  /**
   *  Returns the name of the exercise.
   *  @return the exercise name
   */
  public String getName() {
    return name;
  }

  /**
   *  Returns the intended number of minutes to practice 
   *  @return target number of minutes to practice
   */
  public int getTargetMinutesPerDay() {
    return targetMinutesPerDay;
  }

  /**
   *  Returns the exercise category
   *  Subclasses are required to implement this
   *  @return the category name
   */
  public abstract String getCategory();
}
