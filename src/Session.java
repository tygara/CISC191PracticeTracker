import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single practice session on a specific date.
 * 
 * A session contains one or more {@link SessionEntry} objects, each
 * describing time spent on a particular exercise.
 * 
 * IS-A: Session is a domain model object
 * HAS-A: Session has a date and a list of SessionEntry items
 */
public class Session {

  private final LocalDate date;
  private final List<SessionEntry> entries = new ArrayList<>();



  /**
   * Creates a Session for the given date.
   *
   * @param date the date of the session
   * @throws IllegalArgumentException if date is null
   */
  public Session(LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException("date must not be null");
    }
    this.date = date;
  }



  /**
   * Creates a Session using today's date.
   */
  public Session() {
    this(LocalDate.now());
  }



  /**
   * Returns the date of this session.
   *
   * @return the session date
   */
  public LocalDate getDate() {
    return date;
  }



  /**
   * Adds a new entry to this session.
   *
   * @param entry the practice entry to add
   * @throws IllegalArgumentException if entry is null
   */
  public void addEntry(SessionEntry entry) {
    if (entry == null) {
      throw new IllegalArgumentException("entry must not be null");
    }
    entries.add(entry);
  }



  /**
   * Returns an unmodifiable list of all session entries.
   *
   * @return list of entries
   */
  public List<SessionEntry> getEntries() {
    return Collections.unmodifiableList(entries);
  }



  /**
   * Calculates the total minutes practiced across all entries.
   *
   * @return total minutes practiced
   */
  public int getTotalMinutes() {
    int total = 0;
    for (SessionEntry entry : entries) {
      total += entry.getMinutesPracticed();
    }
    return total;
  }



  /**
   * Returns true if the session contains no entries.
   *
   * @return whether the session has no entries
   */
  public boolean isEmpty() {
    return entries.isEmpty();
  }
}

