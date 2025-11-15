import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArpeggioExerciseTest {

  @Test
  void constructorStoresFieldsCorrectly() {
    ArpeggioExercise exercise = new ArpeggioExercise(
        "ii–V–I Arpeggios",
        20,
        "Dm7–G7–Cmaj7",
        100
    );

    assertEquals("ii–V–I Arpeggios", exercise.getName());
    assertEquals(20, exercise.getTargetMinutesPerDay());
    assertEquals("Dm7–G7–Cmaj7", exercise.getChordSymbol());
    assertEquals(100, exercise.getTargetTempoBpm());
    assertEquals("Arpeggio", exercise.getCategory());
  }

  @Test
  void constructorRejectsInvalidArguments() {
    // name blank
    assertThrows(IllegalArgumentException.class, () ->
        new ArpeggioExercise(
            "",
            20,
            "Dm7–G7–Cmaj7",
            100
        ));

    // targetMinutesPerDay <= 0
    assertThrows(IllegalArgumentException.class, () ->
        new ArpeggioExercise(
            "Valid Name",
            0,
            "Dm7–G7–Cmaj7",
            100
        ));

    // chordSymbol blank
    assertThrows(IllegalArgumentException.class, () ->
        new ArpeggioExercise(
            "Valid Name",
            20,
            "",
            100
        ));

    // targetTempoBpm <= 0
    assertThrows(IllegalArgumentException.class, () ->
        new ArpeggioExercise(
            "Valid Name",
            20,
            "Dm7–G7–Cmaj7",
            0
        ));
  }
}

