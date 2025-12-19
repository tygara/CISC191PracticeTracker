import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application window for the Practice Tracker GUI.
 *
 * This class represents the primary Swing JFrame for the application.
 * It is responsible for initializing and displaying the user interface,
 * managing user interaction through buttons and list selection, and
 * coordinating between the UI and the underlying session data model.
 *
 * The window provides:
 * - Controls for creating, loading, deleting, and planning practice sessions
 * - A list view displaying available practice sessions
 * - A detail panel showing information for the selected session
 * - Integration with JSON-based persistence for loading sessions from disk
 *
 * Design relationships:
 * IS-A: MainWindow extends {@link JFrame}.
 * HAS-A: MainWindow contains UI controls, session storage, and persistence logic.
 */
public class MainWindow extends JFrame {

  private final JButton newSessionButton;
  private final JButton loadSessionButton;
  private final JButton saveSessionButton;
  private final JButton deleteSessionButton;
  private final JButton newPlanButton;

  // Reference to memory named "sessionListModel" unable to be reassigned to object of same class.
  private final DefaultListModel<String> sessionListModel;
  private final JList<String> sessionList;
  private final JTextArea sessionDetails;

  // Keeps Session objects in memory aligned with the list model
  // Declare a private, final field names sessions, type List<Session> (interface)
  private final List<Session> sessions;

  // Handles JSON persistence
  // declares variable whose type is JsonStore
  private final JsonStore store;



/**
 * Constructs the main application window for the Practice Tracker.
 *
 * Initializes all core application state and builds the Swing user interface.
 * This includes creating and configuring UI components, applying a dark theme,
 * initializing in-memory session storage and JSON persistence, laying out panels,
 * and wiring all event listeners for user interaction.
 *
 * Specifically, this constructor:
 * - Initializes session storage and the JSON persistence handler
 * - Configures the JFrame (size, layout, close operation, background)
 * - Builds the top button panel and styles all buttons
 * - Creates the session list and session details display
 * - Arranges components using BorderLayout and JSplitPane
 * - Registers action listeners for buttons and selection listeners for the list
 * - Sets the initial state of the session details panel
 */
  public MainWindow() {
    super("Practice Tracker");

    // Object instantiation and field initialization
    this.sessions = new ArrayList<>();
    this.store = new JsonStore();

    // Simple dark mode
    Color bg = new Color(25, 25, 25);
    Color panelBg = new Color(35, 35, 35);
    Color fg = new Color(230, 230, 230);
    Color accent = new Color(70, 130, 180);

    // Overrides UI properties for all Swing buttons in the application
    // Sets global focus highlight color for swing buttons
    UIManager.put("Button.focus", accent);


    // Basic Window Setup
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(900, 500);
    // Center the window on the screen, null here means center relative to the screen
    setLocationRelativeTo(null);
    // Divide window into regions, horizontal and vertical gaps
    setLayout(new BorderLayout(8, 8));
    getContentPane().setBackground(bg);

    // Creation of panel with buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBackground(panelBg);
    newSessionButton = new JButton("New Session");
    loadSessionButton = new JButton("Load Session");
    saveSessionButton = new JButton("Save Session");
    deleteSessionButton = new JButton("Delete Session");
    newPlanButton = new JButton("New Plan");

    // Style buttons all at once using enhanced for loop
    // Groups buttons into array and apply same visual config to each
    JButton[] buttons = {
        newSessionButton,
        loadSessionButton,
        saveSessionButton,
        deleteSessionButton,
        newPlanButton
    };

    for (JButton b : buttons) {
      b.setBackground(accent);
      b.setForeground(fg);
      b.setFocusPainted(false);
      b.setBorderPainted(false);
    }

    // Add button to panel in specific order
    // Each button now becomes a child of buttonPanel
    // Each call places the buttons inside the top panel so the layout manager can arrange/display
    buttonPanel.add(newSessionButton);
    buttonPanel.add(loadSessionButton);
    buttonPanel.add(saveSessionButton);
    buttonPanel.add(deleteSessionButton);
    buttonPanel.add(newPlanButton);

    // Places the button panel at the top of the window
    add(buttonPanel, BorderLayout.NORTH);

    // Session list UI
    // JList does not store data itself, it reads data from another object called a model
    sessionListModel = new DefaultListModel<>(); // list of strings -> stores data
    // Creates a visual list that displays whatever is inside sessionListModel
    sessionList = new JList<>(sessionListModel); // -> Displays data
    // List is now connected to the model
    sessionList.setBackground(bg);
    sessionList.setForeground(fg);
    sessionList.setSelectionBackground(accent.darker());
    sessionList.setSelectionForeground(fg);

    // Creates JScroll Pane to put sessionList inside it
    // Makes session list scrollable, visually labeled, and styled to match rest of theme
    JScrollPane listScrollPane = new JScrollPane(sessionList);
    listScrollPane.getViewport().setBackground(bg);
    listScrollPane.setBorder(BorderFactory.createTitledBorder("Sessions"));

    // Session details panel
    sessionDetails = new JTextArea();
    sessionDetails.setEditable(false);
    sessionDetails.setLineWrap(true);
    sessionDetails.setWrapStyleWord(true);
    sessionDetails.setBackground(bg);
    sessionDetails.setForeground(fg);
    
    // Wrap session details in scrollable JScrollPane
    JScrollPane detailsScrollPane = new JScrollPane(sessionDetails);
    detailsScrollPane.getViewport().setBackground(bg);
    detailsScrollPane.setBorder(BorderFactory.createTitledBorder("Session Details"));
    
    // Create the middle pane split that allows for resizing
    JSplitPane splitPane =
        new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, detailsScrollPane);
    splitPane.setResizeWeight(0.35);
    
    // Position split pane in center border layout
    add(splitPane, BorderLayout.CENTER);

    // Button behavior
    // Wire button to specific functions, call on click
    newSessionButton.addActionListener(e -> createNewSession());
    loadSessionButton.addActionListener(e -> loadSessionFromFile());
    saveSessionButton.addActionListener(e -> saveSelectedSessionToFile());
    deleteSessionButton.addActionListener(e -> deleteSelectedSession());
    newPlanButton.addActionListener(e -> showWeeklyPlan());

    // updates the session details whenever the list seleciton changes and initalizes the details 
    // panel at startup
    sessionList.addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
        updateSessionDetails();
      }
    });

    updateSessionDetails();
  }



/**
 * Creates a new practice session for the current date.
 *
 * Instantiates a new {@link Session} using today's date, adds it to the
 * in-memory session list, and inserts a formatted label into the session
 * list UI model. The newly created session is then selected so its details
 * are immediately displayed.
 */
  private void createNewSession() {
    Session s = new Session(LocalDate.now());
    sessions.add(s);
    sessionListModel.addElement(formatSessionLabel(s));
    sessionList.setSelectedIndex(sessionListModel.size() - 1);
  }



/**
 * Loads a practice session from a JSON file selected by the user.
 *
 * Displays a file chooser dialog to allow the user to select a session file.
 * If a valid file is chosen, the session is deserialized using {@link JsonStore}
 * and added to the in-memory session list and session list UI.
 *
 * If the user cancels the dialog, the method exits without making changes.
 * Validation and I/O errors encountered during loading are reported to the user
 * via an error dialog.
 */
  private void loadSessionFromFile() {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Load Session JSON");

    if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
      return;
    }

    Path file = chooser.getSelectedFile().toPath();

    try {
      Session loaded = store.load(file);
      sessions.add(loaded);
      sessionListModel.addElement(formatSessionLabel(loaded));
      sessionList.setSelectedIndex(sessionListModel.size() - 1);
    } catch (ValidationException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(),
          "Load Error", JOptionPane.ERROR_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(),
          "File Error", JOptionPane.ERROR_MESSAGE);
    }
  }


  private void saveSelectedSessionToFile() {
    int index = sessionList.getSelectedIndex();
    if (index < 0 || index >= sessions.size()) {
      JOptionPane.showMessageDialog(this,
          "Select a session to save.",
          "Save Session",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Save Session JSON");

    if(chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
      return;
    }

    Path file = chooser.getSelectedFile().toPath();

    // Auto add .json if user forgets to save
    // if(!file.toString().toLowerCase().endsWith(".json")) {
    //   file = Path.of(file.toString() + ".json");
    // }

    try {
    store.save(sessions.get(index), file);
    JOptionPane.showMessageDialog(this,
        "Saved to:\n" + file,
        "Save Session",
        JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(this,
          "File error: " + ex.getMessage(),
          "Save Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }



/**
 * Deletes the currently selected practice session.
 *
 * If no session is selected, an informational dialog is displayed and the
 * method returns without making changes. When a valid selection exists,
 * the corresponding session is removed from both the in-memory session
 * collection and the session list UI model.
 *
 * After deletion, the selection is updated to a remaining session if one
 * exists. If no sessions remain, the session details panel is refreshed
 * to indicate that no session is selected.
 */
  private void deleteSelectedSession() {
    int index = sessionList.getSelectedIndex();
    if (index < 0) {
      JOptionPane.showMessageDialog(this,
          "Select a session to delete.",
          "Delete Session",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    sessions.remove(index);
    sessionListModel.remove(index);

    if (!sessionListModel.isEmpty()) {
      sessionList.setSelectedIndex(Math.min(index, sessionListModel.size() - 1));
    } else {
      updateSessionDetails();
    }
  }



/**
 * Updates the session details display based on the currently selected session.
 *
 * If no valid session is selected, the details panel is cleared and a
 * placeholder message is shown. When a session is selected, this method
 * retrieves the corresponding {@link Session}, formats its summary and
 * entries into readable text, and displays the result in the details panel.
 */
  private void updateSessionDetails() {
    int index = sessionList.getSelectedIndex();
    if (index < 0 || index >= sessions.size()) {
      sessionDetails.setText("No session selected.");
      return;
    }

    Session s = sessions.get(index);
    StringBuilder sb = new StringBuilder();
    sb.append("Date: ").append(s.getDate()).append("\n");
    sb.append("Total minutes: ").append(s.getTotalMinutes()).append("\n\n");

    for (SessionEntry entry : s.getEntries()) {
      sb.append("Minutes: ").append(entry.getMinutesPracticed()).append("\n");
      if (entry.getAverageTempoBpm() != null) {
        sb.append("Tempo: ").append(entry.getAverageTempoBpm()).append("\n");
      }
      if (entry.getNotes() != null && !entry.getNotes().isBlank()) {
        sb.append("Notes: ").append(entry.getNotes()).append("\n");
      }
      sb.append("\n");
    }

    sessionDetails.setText(sb.toString());
  }



/**
 * Generates and displays a weekly practice plan.
 *
 * Creates a predefined set of {@link Exercise} objects, uses a
 * {@link PlanGenerator} to produce a {@link WeeklyPlan}, and displays
 * the resulting plan in a modal dialog. This method is intended to
 * demonstrate weekly plan visualization rather than persist data.
 */
  private void showWeeklyPlan() {
    List<Exercise> exercises = List.of(
        new ScaleExercise("Major Scales", 15, "Major", "C", 90),
        new ArpeggioExercise("Arpeggios", 10, "Cmaj7", 100),
        new SongExercise("Repertoire", 20, "Fade To Black", "Metallica")
    );

    PlanGenerator generator = new PlanGenerator();
    WeeklyPlan plan = generator.generate(exercises);

    PlanDialog dialog = new PlanDialog(this, plan);
    dialog.setVisible(true);
  }



/**
 * Formats a practice session for display in the session list.
 *
 * Creates a concise, human-readable label containing the session date
 * and total practice time in minutes. This string is used as the visual
 * representation of a {@link Session} in the session list UI.
 *
 * @param s the session to format
 * @return a formatted session label suitable for list display
 */
  private String formatSessionLabel(Session s) {
    return s.getDate() + " (" + s.getTotalMinutes() + " min)";
  }



  /**
   * Entry point for launching the Practice Tracker window.
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainWindow window = new MainWindow();
      window.setVisible(true);
    });
  }
}
