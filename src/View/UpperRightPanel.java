package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import Controller.*;

public class UpperRightPanel extends JPanel {
    private JTextField invoiceDateTF;
    private JTextField CustomerNameTF;
    private JLabel invoiceDateLB;
    private JLabel CustomerNameLB;
    private JLabel invoiceNumber;
    private JLabel invoiceTotal;

    public void setInvoiceDateTF(String invoiceDateTF) {
        this.invoiceDateTF.setText(invoiceDateTF);
    }

    public JTextField getInvoiceDateTF() {
        return invoiceDateTF;
    }

    public JTextField getCustomerNameTF() {
        return CustomerNameTF;
    }

    public void setCustomerNameTF(String customerNameTF) {
        this.CustomerNameTF.setText(customerNameTF);
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber.setText(invoiceNumber);
    }

    public void setInvoiceTotal(String invoiceTotal) {
        this.invoiceTotal.setText(invoiceTotal);
    }


    public UpperRightPanel() {
        super();

        //setLayout(new GridLayout(4, 2, 1, 1));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;gbc.gridy = 0;
        invoiceDateLB =new JLabel("Invoice Number");
        add(invoiceDateLB,gbc);
        gbc.gridx = 1;gbc.gridy = 0;
        invoiceNumber=new JLabel(" ");
        add(invoiceNumber,gbc);

        gbc.gridx = 0;gbc.gridy = 1;
        CustomerNameLB =new JLabel("Invoice Date");
        add(CustomerNameLB,gbc);
        gbc.gridx = 1;gbc.gridy = 1;
        invoiceDateTF =new JTextField(30);
        add(invoiceDateTF,gbc);

        gbc.gridx = 0;gbc.gridy = 2;
        CustomerNameLB =new JLabel("Customer Name");
        add(CustomerNameLB,gbc);
        gbc.gridx = 1;gbc.gridy = 2;
        CustomerNameTF =new JTextField(30);
        add(CustomerNameTF,gbc);

        gbc.gridx = 0;gbc.gridy = 3;
        CustomerNameLB =new JLabel("Invoice Total");
        add(CustomerNameLB,gbc);
        gbc.gridx = 1;gbc.gridy = 3;
        invoiceTotal=new JLabel(" ");
        add(invoiceTotal,gbc);



    }
}
