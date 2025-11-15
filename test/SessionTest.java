import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

  @Test
  void addEntryAndGetEntriesWork() {
    Session session = new Session(LocalDate.of(2025, 1, 1));

    Exercise exercise = new ScaleExercise(
        "Major Scales",
        20,
        "Major",
        "C",
        80);

    SessionEntry entry = new SessionEntry(exercise, 20, 80, "Good tone");
    session.addEntry(entry);

    List<SessionEntry> entries = session.getEntries();
    assertEquals(1, entries.size());
    assertSame(entry, entries.get(0));
    assertFalse(session.isEmpty());
  }

  @Test
  void getEntriesIsUnmodifiable() {
    Session session = new Session(LocalDate.now());

    Exercise ex = new SongExercise(
        "Warmup Song",
        10,
        "Blue Bossa",
        "Joe Henderson");
    SessionEntry entry = new SessionEntry(ex, 10, null, null);

    session.addEntry(entry);

    List<SessionEntry> entries = session.getEntries();
    assertThrows(UnsupportedOperationException.class, () -> entries.add(entry));
  }

  @Test
  void totalMinutesAccumulatesCorrectly() {
    Session session = new Session(LocalDate.now());

    Exercise ex = new ArpeggioExercise(
        "ii–V–I",
        15,
        "Dm7–G7–Cmaj7",
        120);

    session.addEntry(new SessionEntry(ex, 15, 120, null));
    session.addEntry(new SessionEntry(ex, 10, 100, null));

    assertEquals(25, session.getTotalMinutes());
  }

  @Test
  void constructorRejectsNullDate() {
    assertThrows(IllegalArgumentException.class, () -> new Session(null));
  }
}
