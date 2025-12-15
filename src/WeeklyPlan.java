import java.util.Collections;
import java.util.List;

public class WeeklyPlan {

  private final List<Exercise> exercises;
  private final int[][] minutes;

  public WeeklyPlan(List<Exercise> exercises, int[][] minutes) {
    if(exercises == null || minutes == null) {
      throw new IllegalArgumentException("arguments must not be null");
    }
    this.exercises = List.copyOf(exercises);
    this.minutes = minutes;
  }

  public List<Exercise> getExercises() {
    return Collections.unmodifiableList(exercises);
  }

  public int getMinutes(int dayIndex, int exerciseIndex) {
    return minutes[dayIndex][exerciseIndex];
  }

  public int getDays() {
    return 7;
  }

  public int getExerciseCount() {
    return exercises.size();
  }
}
