import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    private JSpinner spinnerRows;
    private JSpinner spinnerColumns;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        JLabel label = new JLabel("Grid size:");
        spinnerRows = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        spinnerColumns = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        JButton buttonCreate = new JButton("Create");

        add(label);
        add(spinnerRows);
        add(spinnerColumns);
        add(buttonCreate);

        buttonCreate.addActionListener(this::create);
    }

    private void create(ActionEvent e) {
        frame.canvas.init(getRows(), getCols());
    }

    public int getRows() {
        return (Integer) spinnerRows.getValue();
    }

    public int getCols() {
        return (Integer) spinnerColumns.getValue();
    }
}