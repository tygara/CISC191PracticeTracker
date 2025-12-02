import javax.swing.*;
import java.awt.*;

/**
 * Main application window for the Practice Tracker GUI.
 * 
 * This class sets up a simple dark-themed interface with buttons for
 * session management and a list displaying existing practice sessions.
 * 
 * IS-A: MainWindow is a JFrame.
 * HAS-A: MainWindow has buttons (new,load,delete,newplan) and a session list UI component.
 */
public class MainWindow extends JFrame {

  private final JButton newSessionButton;
  private final JButton loadSessionButton;
  private final JButton deleteSessionButton;
  private final JButton newPlanButton;
  private final JList<String> sessionList;

  /**
   * Constructs the main window and initializes all UI components,
   * layout, colors, and button styling. Also provides temporary
   * mock loader for populating the session list for test cases to pass
   */
  public MainWindow() {
    super("Practice Tracker");

    // Simple dark mode
    Color bg = new Color(25, 25, 25);
    Color panelBg = new Color(35, 35, 35);
    Color fg = new Color(230, 230, 230);
    Color accent = new Color(70, 130, 180);

    UIManager.put("Button.focus", accent);

    // Basic Window Setup
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(5, 5));
    getContentPane().setBackground(bg);

    // Top panel with buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    buttonPanel.setBackground(panelBg);

    newSessionButton = new JButton("New Session");
    loadSessionButton = new JButton("Load Session");
    deleteSessionButton = new JButton("Delete Session");
    newPlanButton = new JButton("New Plan");

    JButton[] buttons = {
        newSessionButton, loadSessionButton, deleteSessionButton, newPlanButton
    };

    for (JButton b : buttons) {
      b.setBackground(accent);
      b.setForeground(fg);
      b.setFocusPainted(false);
      b.setBorderPainted(false);
    }

    buttonPanel.add(newSessionButton);
    buttonPanel.add(loadSessionButton);
    buttonPanel.add(deleteSessionButton);
    buttonPanel.add(newPlanButton);

    // Center area
    sessionList = new JList<>(new DefaultListModel<>());
    sessionList.setBackground(bg);
    sessionList.setForeground(fg);
    sessionList.setSelectionBackground(accent.darker());
    sessionList.setSelectionForeground(fg);

    JScrollPane listScrollPane = new JScrollPane(sessionList);
    listScrollPane.getViewport().setBackground(bg);
    listScrollPane.setBorder(BorderFactory.createEmptyBorder());

    // Layout
    add(buttonPanel, BorderLayout.NORTH);
    add(listScrollPane, BorderLayout.CENTER);

    // Mock loader for screenshot
    loadSessionButton.addActionListener(e -> loadMockSessions());
  }

  /**
   * Populates the session list with mock example sessions.
   * Used only for early UI testing and screenshots.
   */
  private void loadMockSessions() {
    DefaultListModel<String> model = (DefaultListModel<String>) sessionList.getModel();
    model.clear();
    model.addElement("2025-01-01 - 20 min - Major Scales");
    model.addElement("2025-01-02 - 35 min - Arpeggios + Songs");
    model.addElement("2025-01-03 - 15 min - Ear Training");
    model.addElement("2025-01-04 - 40 min - Repertoire Run-through");
  }

  /**
   * Entry point for launching the Practice Tracker window.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainWindow window = new MainWindow();
      window.setVisible(true);
    });
  }
}

