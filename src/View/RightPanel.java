package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel implements ActionListener {
    private JTable invoiceItems;
    private JButton saveBtn;
    private JButton cancelBtn;
    private JButton deleteLineBtn;
    private JButton insertLineBtn;
    private JPanel invoiceDetailsPanel;
    private JPanel invoiceDataPanel;
    private JPanel rightPanelButtons;
    private JTextField invoiceDateTF;
    private JTextField customerNameTF;
    private JLabel invoiceDateLB;
    private JLabel customerNameLB;
    private String[] cols={"No.","Item Name","Item Price","Count","Item Total"};
    private String[][] data = {
            {"","","","",""},
            {"","","","",""},
            {"","","","",""},
            {"","","","",""}

    };

    public JButton getDeleteLineBtn() {
        return deleteLineBtn;
    }

    public JButton getInsertLineBtn() {
        return insertLineBtn;
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public String[][] getData() {
        return data;
    }

    public String[] getCols() {
        return cols;
    }

    public JTable getInvoiceItems() {
        return invoiceItems;
    }
    public void setInvoiceItems(DefaultTableModel updatedData) {
        this.invoiceItems.setModel(updatedData);
    }


    public RightPanel() {
        super(new GridLayout(3, 1, 10, 1));

        TitledBorder border = new TitledBorder("Invoice Items");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        setBorder(border);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        setSize(300,200);
        //invoiceDetailsPanel

        invoiceItems = new JTable(data,cols);
        add(new JScrollPane(invoiceItems));
        rightPanelButtons=new JPanel();
        rightPanelButtons.setLayout(new BoxLayout(rightPanelButtons,BoxLayout.X_AXIS));
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(this);
        saveBtn.setActionCommand("save");
        rightPanelButtons.add(saveBtn);
        rightPanelButtons.add(Box.createRigidArea(new Dimension(30, 100)));
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(this);
        cancelBtn.setActionCommand("cancel");
        rightPanelButtons.add(cancelBtn);
        rightPanelButtons.add(Box.createRigidArea(new Dimension(30, 100)));
        insertLineBtn = new JButton("Insert Line");
        insertLineBtn.addActionListener(this);
        insertLineBtn.setActionCommand("insertInvoiceItem");
        rightPanelButtons.add(insertLineBtn);
        rightPanelButtons.add(Box.createRigidArea(new Dimension(30, 100)));
        deleteLineBtn = new JButton("Delete Line");
        deleteLineBtn.addActionListener(this);
        deleteLineBtn.setActionCommand("deleteInvoiceItem");
        rightPanelButtons.add(deleteLineBtn);
        add(rightPanelButtons);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
