package View;
import javax.swing.*;

public class CreateItemMultiInput {
    private JTextField itemNameTF;
    private JTextField itemPriceTF;
    private JTextField itemCountTF;


    private String itemName;
    private String itemPrice;
    private String count;

    public CreateItemMultiInput() {
        JTextField itemNameTF = new JTextField(15);
        JTextField itemPriceTF = new JTextField(15);
        JTextField itemCountTF = new JTextField(15);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Item Name:"));
        myPanel.add(itemNameTF);
        myPanel.add(Box.createVerticalBox()); // a spacer
        myPanel.add(new JLabel("Item Price:"));
        myPanel.add(itemPriceTF);
        myPanel.add(Box.createVerticalBox()); // a spacer
        myPanel.add(new JLabel("Item Count:"));
        myPanel.add(itemCountTF);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please enter item details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
           // System.out.println("x value: " + xField.getText());
           // System.out.println("y value: " + yField.getText());
            itemName = itemNameTF.getText();
            itemPrice = itemPriceTF.getText();
            count = itemCountTF.getText();
        }
        System.out.println(itemName);
       // System.out.println("y value: " + yField.getText());

    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getCount() {
        return count;
    }
}
