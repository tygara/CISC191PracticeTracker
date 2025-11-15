import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScaleExerciseTest {

  @Test
  void constructorStoresFieldsCorrectly() {
    ScaleExercise scale = new ScaleExercise(
        "Major Scales",
        15,
        "Major",
        "C",
        80
    );

    assertEquals("Major Scales", scale.getName());
    assertEquals(15, scale.getTargetMinutesPerDay());
    assertEquals("Major", scale.getScaleName());
    assertEquals("C", scale.getKey());
    assertEquals(80, scale.getTargetTempoBpm());
    assertEquals("Scale", scale.getCategory());
  }

  @Test
  void constructorRejectsInvalidArguments() {
    // name blank
    assertThrows(IllegalArgumentException.class, () ->
        new ScaleExercise(
            "",
            15,
            "Major",
            "C",
            80
        ));

    // minutes <= 0
    assertThrows(IllegalArgumentException.class, () ->
        new ScaleExercise(
            "Name",
            0,
            "Major",
            "C",
            80
        ));

    // scaleName blank
    assertThrows(IllegalArgumentException.class, () ->
        new ScaleExercise(
            "Name",
            15,
            "",
            "C",
            80
        ));

    // key blank
    assertThrows(IllegalArgumentException.class, () ->
        new ScaleExercise(
            "Name",
            15,
            "Major",
            "",
            80
        ));

    // tempo <= 0
    assertThrows(IllegalArgumentException.class, () ->
        new ScaleExercise(
            "Name",
            15,
            "Major",
            "C",
            0
        ));
  }
}

