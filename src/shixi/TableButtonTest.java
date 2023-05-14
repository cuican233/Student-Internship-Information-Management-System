package shixi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author CC
 * @date 2021/12/17
 */
/**
 * @author FeianLing
 * @date 2019/9/10
 */
public class TableButtonTest implements TableCellRenderer {
    private JPanel jPanel;
    private JButton jButton;

    public TableButtonTest() {
        initJPanel();
        initButton();
        jPanel.add(jButton);
    }

    private void initButton() {
        jButton = new JButton();
        jButton.setBounds(2, 3, 80, 30);
        jButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("" + e.getActionCommand());
                        System.out.println(jButton.getText());
                    }
                });
    }

    private void initJPanel() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (table.getModel().isCellEditable(row, column)) {
            jButton.setText(value == null ? "" : value.toString());
            return jPanel;
        } else {
            return getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }


    private class JButtonRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;
        private JButton button;

        public JButtonRenderer() {
            button = new JButton();
        }
    }

    private class JButtonEditor extends DefaultCellEditor {

        private static final long serialVersionUID = 1L;

        private JButton button;
        private JTable table;
        private int row, column;

        public JButtonEditor() {
            super(new JTextField());
            button = new JButton();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(table.getParent(), "Hello, you selected row " + row + " Column " + column + ", you can replace these lines of codes and " + "do any thing you want here");
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            this.column = column;
            if (table.isCellEditable(row, column)) {
                button.setText(value == null ? "" : value.toString());
                return button;
            } else {
                return super.getTableCellEditorComponent(table, value,
                        isSelected, row, column);
            }

        }
    }

}


