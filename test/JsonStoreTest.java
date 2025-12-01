import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonStoreTest {

  @Test
  void saveAndLoadRoundTrip() throws Exception {
    // Arrange: make a session with one entry
    Session session = new Session(LocalDate.of(2025, 1, 1));
    Exercise exercise = new ScaleExercise(
              "Major Scales",
              15,
              "Major",
              "C",
              90
    );
    SessionEntry entry = new SessionEntry(exercise, 20, 90, "Good tone");
    session.addEntry(entry);

    JsonStore store = new JsonStore();
    Path file = Files.createTempFile("session-", ".json");
    file.toFile().deleteOnExit();

    // Save then load
    store.save(session, file);
    Session loaded = store.load(file);

    // Assert: Basic fields survived
    assertEquals(session.getDate(), loaded.getDate());

    List<SessionEntry> loadedEntries = loaded.getEntries();

    assertEquals(1, loadedEntries.size());
    assertEquals(20, loadedEntries.get(0).getMinutesPracticed());
    assertEquals("Good tone", loadedEntries.get(0).getNotes());
  }

  @Test
  void loadOnMissingFileThrowsIOException() {
    JsonStore store = new JsonStore();
    Path missing = Path.of("this-file-should-not-exist-12345.json");

    assertFalse(Files.exists(missing));

    assertThrows(IOException.class, () -> store.load(missing));
  }

  @Test
  void invalidJsonThrowsValidationException() throws IOException {
    JsonStore store = new JsonStore();
    Path file = Files.createTempFile("bad-json-", ".json");
    file.toFile().deleteOnExit();


    // Write invalid Json
    Files.writeString(file, "this is not valid json at all");

    assertThrows(ValidationException.class, () -> store.load(file));
  }
}
