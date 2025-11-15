import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Session {

  private final LocalDate date;
  private final List<SessionEntry> entries = new ArrayList<>();

  public Session(LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException("date must not be null");
    }
    this.date = date;
  }

  public Session() {
    this(LocalDate.now());
  }

  public LocalDate getDate() {
    return date;
  }

  public void addEntry(SessionEntry entry) {
    if (entry == null) {
      throw new IllegalArgumentException("entry must not be null");
    }
    entries.add(entry);
  }

  public List<SessionEntry> getEntries() {
    return Collections.unmodifiableList(entries);
  }

  public int getTotalMinutes() {
    int total = 0;
    for (SessionEntry entry : entries) {
      total += entry.getMinutesPracticed();
    }
    return total;
  }

  public boolean isEmpty() {
    return entries.isEmpty();
  }
}
