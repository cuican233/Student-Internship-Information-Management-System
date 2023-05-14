package shixi;

import shixi.utils.jdbcutil;
import shixi.views.RLUI.LoginUI;
import shixi.views.studentviews.StudentUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

class TestJF extends JFrame {
    jdbcutil jdbcutil = null;
    public TestJF() {
//        String[] columnNames = {"Date", "String", "Integer", "Number", ""};
//        Object[][] data =
//                {
//                        {new Date(), "A", new Integer(1), new Double(5.1), "Delete0"},
//                        {new Date(), "B", new Integer(2), new Double(6.2), "Delete1"},
//                        {new Date(), "C", new Integer(3), new Double(7.3), "Delete2"},
//                        {new Date(), "D", new Integer(4), new Double(8.4), "Delete3"}
//                };
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("ID");
        columnNames.add("公司名称");
        columnNames.add("职位");
        columnNames.add("要求");
        columnNames.add("薪资待遇");
        columnNames.add("状态");
        columnNames.add("申请");
        rowDate = new Vector();
        try {
            String sql = "SELECT *FROM recruitment";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                hang.add("申请");
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
        JTable table = new JTable( model )
        {
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        table.setRowHeight(100);
        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );
        ButtonColumn buttonColumn = new ButtonColumn(table, 6);
    }

    public static void main(String[] args)
    {
        TestJF frame = new TestJF();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
    }

    class ButtonColumn extends AbstractCellEditor
            implements TableCellRenderer, TableCellEditor, ActionListener
    {
        JTable table;
        JButton renderButton;
        JButton editButton;
        String text;

        public ButtonColumn(JTable table, int column)
        {
            super();
            this.table = table;
            renderButton = new JButton();

            editButton = new JButton();
            editButton.setFocusPainted( false );
            editButton.addActionListener( this );

            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer( this );
            columnModel.getColumn(column).setCellEditor( this );
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (hasFocus)
            {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }
            else if (isSelected)
            {
                renderButton.setForeground(table.getSelectionForeground());
                renderButton.setBackground(table.getSelectionBackground());
            }
            else
            {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }

            renderButton.setText( (value == null) ? "" : value.toString() );
            return renderButton;
        }

        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column)
        {
            text = (value == null) ? "" : value.toString();
            editButton.setText( text );
            return editButton;
        }

        public Object getCellEditorValue()
        {
            return text;
        }

        public void actionPerformed(ActionEvent e)
        {
            fireEditingStopped();
            System.out.println( e.getActionCommand() + " : " + table.getSelectedRow());
            new LoginUI();
            /*再new 一个新的专门用来完善用户简历信息和修改申请状态的界面，再在那个界面里添加监听器来实现*/
        }
    }
}