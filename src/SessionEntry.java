/**
 * Represents a single recorded practice entry within a session
 * 
 * Each entry captures:
 * - the exercise practiced,
 * - the number of minutes spent,
 * - an optional average tempo,
 * - optional written notes.
 * 
 * IS-A: SessionEntry is a domain model object.
 * HAS-A: SessionEntry has an Exercise, time practiced, tempo, and notes.
 */
public class SessionEntry {

  private final Exercise exercise;
  private final int minutesPracticed;
  private final Integer averageTempoBpm;
  private final String notes;

  /**
   * Creates a new SessionEntry describing practice on a specific exercise.
   *
   * @param exercise the exercise performed 
   * @param minutesPracticed total minutes spent practicing 
   * @param averageTempoBpm optional average tempo in BPM; if provided, must be > 0
   * @param notes optional notes or comments about the practice session
   * @throws IllegalArgumentException if exercise is null, minutes ≤ 0,
   *                                  or tempo is present but non-positive
   */
  public SessionEntry(Exercise exercise,
                      int minutesPracticed,
                      Integer averageTempoBpm,
                      String notes) {
    if (exercise == null) {
      throw new IllegalArgumentException("exercise must not be null");
    }
    if (minutesPracticed <= 0) {
      throw new IllegalArgumentException("minutesPracticed must be > 0");
    }
    if (averageTempoBpm != null && averageTempoBpm <= 0) {
      throw new IllegalArgumentException("averageTempoBpm must be > 0 when provided");
    }

    this.exercise = exercise;
    this.minutesPracticed = minutesPracticed;
    this.averageTempoBpm = averageTempoBpm;
    this.notes = notes;
  }




  /**
   * Returns the exercise associated with this entry
   *
   * @return the exercise practiced
   */
  public Exercise getExercise() {
    return exercise;
  }



  /**
   * Returns the number of minutes practiced
   *
   * @return minutes practiced
   */
  public int getMinutesPracticed() {
    return minutesPracticed;
  }



  /**
   * Returns the average tempo in BPM
   *
   * @return average tempo or null if not applicable
   */
  public Integer getAverageTempoBpm() {
    return averageTempoBpm;
  }

  /**
   * Returns any notes attached to this entry
   *
   * @return notes or null
   */
  public String getNotes() {
    return notes;
  }
}
