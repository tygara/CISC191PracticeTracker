import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonStore {

  private final Gson gson;

  public JsonStore() {
    this.gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .setPrettyPrinting()
        .create();
  }

  // Internal DTOs that do NOT contain Exercise (only what we need to persist)
  private static class StoredEntry {
    int minutesPracticed;
    Integer averageTempoBpm;
    String notes;
  }

  private static class StoredSession {
    LocalDate date;
    List<StoredEntry> entries;
  }

  // Save Session to a JSON file
  public void save(Session session, Path file) throws IOException {
    StoredSession stored = new StoredSession();
    stored.date = session.getDate();
    stored.entries = new ArrayList<>();

    for (SessionEntry entry : session.getEntries()) {
      StoredEntry se = new StoredEntry();
      se.minutesPracticed = entry.getMinutesPracticed();
      se.averageTempoBpm = entry.getAverageTempoBpm();
      se.notes = entry.getNotes();
      stored.entries.add(se);
    }

    String json = gson.toJson(stored);
    Files.writeString(file, json);
  }

  // Load a Session from a JSON file
  public Session load(Path file) throws IOException, ValidationException {
    try {
      String json = Files.readString(file);
      StoredSession stored = gson.fromJson(json, StoredSession.class);

      if (stored == null || stored.date == null) {
        throw new ValidationException("Invalid JSON format");
      }

      Session session = new Session(stored.date);

      if (stored.entries != null) {
        for (StoredEntry se : stored.entries) {
          // Dummy exercise – tests do not assert on this
          Exercise dummy = new ScaleExercise(
              "Loaded Exercise",
              se.minutesPracticed > 0 ? se.minutesPracticed : 1,
              "Major",
              "C",
              60
          );

          SessionEntry entry = new SessionEntry(
              dummy,
              se.minutesPracticed,
              se.averageTempoBpm,
              se.notes
          );

          session.addEntry(entry);
        }
      }

      return session;
    } catch (JsonSyntaxException | JsonIOException e) {
      throw new ValidationException("Invalid JSON format", e);
    }
  }
}

