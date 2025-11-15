import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionEntryTest {

  @Test
  void constructorStoresFieldsCorrectly() {
    Exercise exercise = new ScaleExercise(
        "Major Scales",
        20,
        "Major",
        "G",
        100);

    SessionEntry entry = new SessionEntry(
        exercise,
        25,
        90,
        "Clean tone at 90 bpm");

    assertSame(exercise, entry.getExercise());
    assertEquals(25, entry.getMinutesPracticed());
    assertEquals(90, entry.getAverageTempoBpm());
    assertEquals("Clean tone at 90 bpm", entry.getNotes());
  }

  @Test
  void constructorAllowsNullTempoAndNotes() {
    Exercise exercise = new SongExercise(
        "Warmup Song",
        10,
        "Autumn Leaves",
        "Joseph Kosma");

    SessionEntry entry = new SessionEntry(
        exercise,
        10,
        null,
        null);

    assertNull(entry.getAverageTempoBpm());
    assertNull(entry.getNotes());
  }

  @Test
  void constructorRejectsInvalidArguments() {
    Exercise exercise = new ArpeggioExercise(
        "ii–V–I Arpeggios",
        15,
        "Dm7–G7–Cmaj7",
        120);

    // null exercise
    assertThrows(IllegalArgumentException.class, () -> new SessionEntry(null, 10, 80, "notes"));

    // minutes <= 0
    assertThrows(IllegalArgumentException.class, () -> new SessionEntry(exercise, 0, 80, "notes"));

    // tempo <= 0 when provided
    assertThrows(IllegalArgumentException.class, () -> new SessionEntry(exercise, 10, 0, "notes"));
  }
}
