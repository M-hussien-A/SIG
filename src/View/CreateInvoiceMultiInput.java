package View;

import javax.swing.*;

public class CreateInvoiceMultiInput {

        private JLabel invoiceNumberLB;
        private JTextField invoiceDateTF;
        private JTextField invoiceCustomerTF;


        private String invoiceDate;
        private String invoiceCustomer;

    public CreateInvoiceMultiInput(int invoiceNumber) {
            JTextField invoiceDateTF = new JTextField(15);
            JTextField invoiceCustomerTF = new JTextField(15);
            JLabel invoiceNumberLB =new JLabel(Integer.toString(invoiceNumber));

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Invoice Number:"));
            myPanel.add(invoiceNumberLB);
            myPanel.add(Box.createVerticalBox()); // a spacer
            myPanel.add(new JLabel("Invoice Date:"));
            myPanel.add(invoiceDateTF);
            myPanel.add(Box.createVerticalBox()); // a spacer
            myPanel.add(new JLabel("Customer:"));
            myPanel.add(invoiceCustomerTF);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please enter item details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                invoiceDate = invoiceDateTF.getText();
                invoiceCustomer = invoiceCustomerTF.getText();
            }
        }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getInvoiceCustomer() {
        return invoiceCustomer;
    }
}


