public class SessionEntry {

  private final Exercise exercise;
  private final int minutesPracticed;
  private final Integer averageTempoBpm;
  private final String notes;

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

  public Exercise getExercise() {
    return exercise;
  }

  public int getMinutesPracticed() {
    return minutesPracticed;
  }

  public Integer getAverageTempoBpm() {
    return averageTempoBpm;
  }

  public String getNotes() {
    return notes;
  }
}
