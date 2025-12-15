import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PlanDialog extends JDialog {
  
  public PlanDialog(JFrame owner, WeeklyPlan plan) {
    super(owner, "WeeklyPlan", true);
    setSize(800, 350);
    setLocationRelativeTo(owner);

    String[] columns = buildColumns(plan.getExercises());
    Object[][] data = buildData(plan);

    DefaultTableModel model = new DefaultTableModel(data, columns) {
      public boolean isCellEditable(int row, int col) {
        return false;
      }
    };

    JTable table = new JTable(model);
    table.setRowHeight(28);

    JScrollPane scroll = new JScrollPane(table);
    add(scroll, BorderLayout.CENTER);

    JButton close = new JButton("Close");
    close.addActionListener(e -> dispose());

    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    bottom.add(close);
    add(bottom, BorderLayout.SOUTH);
  }

  private String[] buildColumns(List<Exercise> exercises) {
    String[] cols = new String[exercises.size() + 1];
    cols[0] = "Day";
    for(int i = 0; i < exercises.size(); i++) {
      cols[i + 1] = exercises.get(i).getName();
    }
    return cols;
  }

  private Object[][] buildData(WeeklyPlan plan) {
    Object[][] data = new Object[plan.getDays()][plan.getExerciseCount() + 1];
    for(int day = 0; day < plan.getDays(); day++) {
      data[day][0] = PlanGenerator.DAYS[day];
      for(int ex = 0; ex < plan.getExerciseCount(); ex++) {
        data[day][ex + 1] = plan.getMinutes(day, ex);
      }
    }
    return data;
  }
}
