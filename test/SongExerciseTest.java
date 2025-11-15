import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SongExerciseTest {

  @Test
  void constructorStoresFieldsCorrectly() {
    SongExercise song = new SongExercise(
        "Ballad Practice",
        20,
        "Misty",
        "Erroll Garner");

    assertEquals("Ballad Practice", song.getName());
    assertEquals(20, song.getTargetMinutesPerDay());
    assertEquals("Misty", song.getSongTitle());
    assertEquals("Erroll Garner", song.getArtist());
    assertEquals("Song", song.getCategory());
  }

  @Test
  void constructorRejectsInvalidArguments() {
    // name blank
    assertThrows(IllegalArgumentException.class, () -> new SongExercise(
        "",
        20,
        "Misty",
        "Erroll Garner"));

    // minutes <= 0
    assertThrows(IllegalArgumentException.class, () -> new SongExercise(
        "Valid Name",
        0,
        "Misty",
        "Erroll Garner"));

    // songTitle blank
    assertThrows(IllegalArgumentException.class, () -> new SongExercise(
        "Valid Name",
        20,
        "",
        "Erroll Garner"));

    // artist blank
    assertThrows(IllegalArgumentException.class, () -> new SongExercise(
        "Valid Name",
        20,
        "Misty",
        ""));
  }
}
