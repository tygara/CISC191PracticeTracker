import java.util.List;

public class PlanGenerator {

  public static final String[] DAYS = {
    "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
  };

  /**
   *  Builds a 7 x N weekly plan grid.
   *  Each cell = planned minutes for that exercise on that day.
   */

  public WeeklyPlan generate(List<Exercise> exercises) {
    if (exercises == null) {
      throw new IllegalArgumentException("exercises must not be null");
    }

    int rows = 7;
    int cols = exercises.size();
    int[][] minutes = new int[rows][cols];

    for(int day = 0; day < rows; day++) {
      for(int ex = 0; ex < cols; ex++) {
        minutes[day][ex] = exercises.get(ex).getTargetMinutesPerDay();
      }
    }

    return new WeeklyPlan(exercises, minutes);
  }
}


