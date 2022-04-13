package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LeftPanel extends JPanel implements ActionListener,MouseListener {

    public static final String[] cols={"No.","Date","Customer","Total"};
 private JTable invoicesTable;
private int selectedInvoiceNumber;
 private String[][] data = {
         {"","","",""},
         {"","","",""},
         {"","","",""},
         {"","","",""}
 };

 private JButton createInvoiceBtn;
 private JButton deleteInvoiceBtn;

private JPanel leftPanelButtons;

    public int getSelectedInvoiceNumber() {
        return selectedInvoiceNumber;
    }

    public JButton getCreateInvoiceBtn() {
        return createInvoiceBtn;
    }

    public void setCreateInvoiceBtn(JButton createInvoiceBtn) {
        this.createInvoiceBtn = createInvoiceBtn;
    }

    public JButton getDeleteInvoiceBtn() {
        return deleteInvoiceBtn;
    }

    public void setDeleteInvoiceBtn(JButton deleteInvoiceBtn) {
        this.deleteInvoiceBtn = deleteInvoiceBtn;
    }

    public JTable getInvoicesTable() {
        return invoicesTable;
    }

    public void setInvoicesTable(DefaultTableModel updatedData) {
        this.invoicesTable.setModel(updatedData);
    }

    public LeftPanel() {
        super(new GridLayout(2, 1, 10, 1));
        TitledBorder border = new TitledBorder("Invoices");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        setBorder(border);
      //  setSize(300,100);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        invoicesTable = new JTable(data,cols);
        invoicesTable.addMouseListener(this);
        add(new JScrollPane(invoicesTable));
        leftPanelButtons=new JPanel();
        leftPanelButtons.setLayout(new BoxLayout(leftPanelButtons,BoxLayout.X_AXIS));
        createInvoiceBtn = new JButton("Create New Invoice");
        createInvoiceBtn.addActionListener(this);
        createInvoiceBtn.setActionCommand("create");
        leftPanelButtons.add(createInvoiceBtn);
        leftPanelButtons.add(Box.createRigidArea(new Dimension(30, 100)));
        deleteInvoiceBtn = new JButton("Delete Invoice");
        deleteInvoiceBtn.addActionListener(this);
        deleteInvoiceBtn.setActionCommand("delete");
        leftPanelButtons.add(deleteInvoiceBtn);
        add(leftPanelButtons);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
      /*  if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
            String selectedCellValue = (String) invoicesTable.getValueAt(invoicesTable.getSelectedRow(), 0);
            selectedInvoiceNumber = Integer.valueOf(selectedCellValue);
            //System.out.println(selectedCellValue);
        }*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

//To-do Insert empty row into jtable if lowest row gets edited   CellEditorListener
  /*  @Override
    public void editingStopped(ChangeEvent e) {
        //getting these values before calling super.editingStopped(e); because they get erased.
        int row = invoicesTable.getEditingRow();
        int col = invoicesTable.getEditingColumn();
        invoicesTable.editingStopped(e); //must call the super code to have a working edition
        if (row == invoicesTable.getRowCount() - 1 && col == invoicesTable.getColumnCount() - 1)
        {
           // invoicesTable;
        }
    }*/
}
