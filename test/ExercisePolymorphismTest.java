import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExercisePolymorphismTest {

  @Test
  void listOfExerciseHoldsDifferentSubtypes() {
    List<Exercise> exercises = new ArrayList<>();

    exercises.add(new ScaleExercise(
        "C Major Scale",
        10,
        "Major",
        "C",
        80
    ));

    exercises.add(new SongExercise(
        "Ballad Practice",
        20,
        "Misty",
        "Erroll Garner"
    ));

    exercises.add(new ArpeggioExercise(
        "ii–V–I Arpeggios",
        15,
        "Dm7–G7–Cmaj7",
        100
    ));

    int totalTargetMinutes = 0;
    for (Exercise e : exercises) {
      // polymorphic calls
      assertNotNull(e.getName());
      assertTrue(e.getTargetMinutesPerDay() > 0);
      assertNotNull(e.getCategory());
      totalTargetMinutes += e.getTargetMinutesPerDay();
    }

    assertEquals(10 + 20 + 15, totalTargetMinutes);
  }
}

