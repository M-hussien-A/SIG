package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.*;
import Controller.*;



public class UIFrame extends JFrame implements ActionListener, MouseListener {
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private UpperRightPanel upperRightPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private int selectedInvoice= -1;
    private int selectedItem;

    private ReadWriteData readWriteData;
    private List<Invoice> invoicesList;
    private List<InvoiceItem> itemsList;
    private HandlingFunctions functions;
    private CreateItemMultiInput createItemMultiInput;
    private CreateInvoiceMultiInput createInvoiceMultiInput;
    private boolean cont = true;

// Constrcutor for the class
    public UIFrame() throws HeadlessException {
        //Create the main frame using super keyword to hit the parent class constructor
        super("Design Preview");

        //Create menu bar and assign it to the frame
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        functions = new HandlingFunctions();
        readWriteData = new ReadWriteData();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        //Add load file button "Item" to File drop down menu
        loadFile = new JMenuItem("Load File", 'L');
        loadFile.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        loadFile.addActionListener(this);
        loadFile.setActionCommand("loadFromFile");
        fileMenu.add(loadFile);
        fileMenu.addSeparator();

        //Add Save file button "Item" to File drop down menu
        saveFile = new JMenuItem("Save File", 'S');
        saveFile.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        saveFile.addActionListener(this);
        saveFile.setActionCommand("saveFile");
        fileMenu.add(saveFile);

        //Add Jpanels to the frame
        leftPanel = new LeftPanel();

        rightPanel = new RightPanel();
        upperRightPanel = new UpperRightPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        //Add Left Panel
        gbc.gridheight = 8;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(leftPanel, gbc);
        leftPanel.getInvoicesTable().addMouseListener(this);
        leftPanel.getCreateInvoiceBtn().addActionListener(this);
        leftPanel.getDeleteInvoiceBtn().addActionListener(this);
        //Add Upper Right Panel
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(upperRightPanel, gbc);

        //Add Right Panel
        gbc.gridheight = 2;
        gbc.gridwidth = 6;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(rightPanel, gbc);
        rightPanel.getCancelBtn().addActionListener(this);
        rightPanel.getSaveBtn().addActionListener(this);
        rightPanel.getDeleteLineBtn().addActionListener(this);
        rightPanel.getInsertLineBtn().addActionListener(this);
        // setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public List<Invoice> getInvoicesList() {
        return invoicesList;
    }

    //  Implement listeners for load and save buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "save"://save button for right panel edit
                try {
                    cont=true;
                    saveEditedDataToInvoicesList();
                    refreshInvoicesTable();refreshInvoicesItems();

                } catch (ParseException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "cancel"://cancel button for right panel edit
                refreshInvoicesItems();
                break;
            case "create"://Create new invoice button for left panel edit
                //todo
                if(readWriteData.isInitialized()) {
                    createInvoiceMultiInput = new CreateInvoiceMultiInput(functions.getNewInvoiceNumber(invoicesList));
                    validateNewInvoiceData();
                    if (cont) {
                        try {
                            invoicesList.add(new Invoice(functions.getNewInvoiceNumber(invoicesList),
                                    new SimpleDateFormat("dd-MM-yyyy").parse(createInvoiceMultiInput.getInvoiceDate()),
                                    createInvoiceMultiInput.getInvoiceCustomer()
                                    ));
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        functions.assignItemsToInvoices(invoicesList,itemsList);
                        refreshInvoicesTable();


                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Please choose an item","Warning",JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "delete"://delete invoice button for left panel edit
                int result = JOptionPane.showConfirmDialog(null,"Are you sure?","Warning",JOptionPane.OK_CANCEL_OPTION);
                if(result==JOptionPane.OK_OPTION) {
                    invoicesList.remove(selectedInvoice);
                    if (selectedInvoice > 0) {
                        selectedInvoice--;
                    }
                   if( invoicesList.isEmpty()) {
                       refreshInvoicesTable();
                       rightPanel.setInvoiceItems((new DefaultTableModel(
                               rightPanel.getData(), rightPanel.getCols())));
                       upperRightPanel.setInvoiceDateTF("");
                       upperRightPanel.setInvoiceTotal("");
                       upperRightPanel.setInvoiceNumber("");
                       upperRightPanel.setCustomerNameTF("");
                   }else{
                       refreshInvoicesTable();
                       refreshInvoicesItems();
                   }
                }
                break;
            case "deleteInvoiceItem"://delete item button for right panel edit
                selectedItem=(rightPanel.getInvoiceItems().getSelectedRow());
                if (readWriteData.isInitialized()) {
                    if (selectedItem + 1 >= 0 & selectedItem < invoicesList.get(selectedInvoice).getInvoiceItems().size()) {
                        result = JOptionPane.showConfirmDialog(null,"Are you sure?","Warning",JOptionPane.OK_CANCEL_OPTION);
                        if(result==JOptionPane.OK_OPTION) {
                            invoicesList.get(selectedInvoice).getInvoiceItems().remove(selectedItem);
                            functions.addTotalToInvoiceList(invoicesList);
                            refreshInvoicesItems();
                            refreshInvoicesTable();
                            selectedItem = 0;

                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please choose an item","Warning",JOptionPane.ERROR_MESSAGE);
                }
                break;

        case "insertInvoiceItem"://insert item button for right panel edit
           // System.out.println(readWriteData.isInitialized());
           if(readWriteData.isInitialized()) {
               createItemMultiInput = new CreateItemMultiInput();
               validateNewItemData();
               if (cont) {
                   invoicesList.get(selectedInvoice).getInvoiceItems()
                           .add(new InvoiceItem(createItemMultiInput.getItemName(),
                                   Double.parseDouble(createItemMultiInput.getItemPrice())
                                   , Integer.parseInt(createItemMultiInput.getCount())
                                   , selectedInvoice));
                   functions.addTotalToInvoiceList(invoicesList);
                   refreshInvoicesItems();
                   refreshInvoicesTable();
               }
           }
           else {
                   JOptionPane.showMessageDialog(null,"Please choose an item","Warning",JOptionPane.ERROR_MESSAGE);
               }

        break;
        case "loadFromFile": //Load data button CSV
                try {
                    //System.out.println(readWriteData.isInitialized());

                    invoicesList = readWriteData.loadInvoices();
                    itemsList = readWriteData.loadItems();
                    invoicesList = functions.assignItemsToInvoices(invoicesList, itemsList);
                    refreshInvoicesTable();

                } catch (Exception ex) {
                    ex.printStackTrace();}
                break;
            case "saveFile": //Save data button CSV
                result = JOptionPane.showConfirmDialog(null,"Are you sure?","Warning",JOptionPane.OK_CANCEL_OPTION);
                if(result==JOptionPane.OK_OPTION) {
                    try {
                        readWriteData.saveContent(invoicesList, itemsList);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                break;

    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1&& (readWriteData.isInitialized())) {
            String selectedCellValue = (String) leftPanel.getInvoicesTable().getValueAt(leftPanel.getInvoicesTable().getSelectedRow(), 0);
            selectedInvoice=(leftPanel.getInvoicesTable().getSelectedRow());  //Integer.valueOf(selectedCellValue);
            functions.getSelectedItems(itemsList, Integer.valueOf(selectedCellValue));
            refreshInvoicesItems();

        }

 /*   public JPanel test(JPanel y1 , JPanel y2){
        JPanel g = new JPanel();
        g.add(y1);
        g.add(y1);
        g.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        return g ;
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

    public void refreshInvoicesTable() {
        if (selectedInvoice <= 0) {
            functions.addTotalToInvoiceList(invoicesList);
            leftPanel.setInvoicesTable(
                    new DefaultTableModel(functions.invoiceTableData(invoicesList), leftPanel.cols));
        }
    }

    public void saveEditedDataToInvoicesList() throws Exception{
        validateSavedData();
        if(cont) {
            invoicesList.get(selectedInvoice).setInvoiceDate(new SimpleDateFormat("dd-MM-yyyy").
                    parse(upperRightPanel.getInvoiceDateTF().getText()));

            invoicesList.get(selectedInvoice).setCustomer(upperRightPanel.getCustomerNameTF().getText());
            List<InvoiceItem> newItemsList = new ArrayList<>();
            for (int i = 0; i < invoicesList.get(selectedInvoice).getInvoiceItems().size(); i++) {
                String itemName = (String) rightPanel.getInvoiceItems().getValueAt(i, 1);
                double itemPrice = Double.parseDouble(rightPanel.getInvoiceItems().getValueAt(i, 2).toString());
                int itemCount = Integer.parseInt(rightPanel.getInvoiceItems().getValueAt(i, 3).toString());
                //System.out.println(itemName);
                newItemsList.add(new InvoiceItem(itemName, itemPrice, itemCount,
                        invoicesList.get(selectedInvoice).getInvoiceNo()));
            }
            invoicesList.get(selectedInvoice).setInvoiceItems(newItemsList);
        }
    }
public void refreshInvoicesItems(){
        //System.out.println(selectedInvoice);
            rightPanel.setInvoiceItems((new DefaultTableModel(
                    functions.itemTableData(invoicesList.get(selectedInvoice).getInvoiceItems()),
                    rightPanel.getCols())));

   /* if(itemsList ==invoicesList.get(selectedInvoice).getInvoiceItems())
    {

        System.out.println("safafafefafafaf");
    }*/
    upperRightPanel.setInvoiceNumber(Integer.toString(invoicesList.get(selectedInvoice).getInvoiceNo()));
    upperRightPanel.setInvoiceDateTF(invoicesList.get(selectedInvoice).getInvoiceDate());
    upperRightPanel.setCustomerNameTF(invoicesList.get(selectedInvoice).getCustomer());
    upperRightPanel.setInvoiceTotal(Double.toString(invoicesList.get(selectedInvoice).getTotal()));


}

public void validateNewItemData(){
    if(createItemMultiInput.getItemName()==null || createItemMultiInput.getItemName().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a valid item name", "Warning", JOptionPane.ERROR_MESSAGE);
        cont = false;
        return;
    }else cont = true;
        if(!isDouble(createItemMultiInput.getItemPrice())){
            JOptionPane.showMessageDialog(null,"Please enter a valid price","Warning",JOptionPane.ERROR_MESSAGE);
            cont =false;
            return;
        }else cont = true;
    if(!isNumeric(createItemMultiInput.getCount())){
        JOptionPane.showMessageDialog(null,"Please enter a valid count","Warning",JOptionPane.ERROR_MESSAGE);
        cont =false;
        return;
    }else cont = true;


    }
    public void validateNewInvoiceData() {
        if (createInvoiceMultiInput.getInvoiceCustomer() == null || createInvoiceMultiInput.getInvoiceCustomer().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid customer name", "Warning", JOptionPane.ERROR_MESSAGE);
            cont = false;
            return;
        }else cont = true;
        if (!checkDate(createInvoiceMultiInput.getInvoiceDate() )) {
            JOptionPane.showMessageDialog(null, "Please enter a valid date", "Warning", JOptionPane.ERROR_MESSAGE);
            cont = false;
            return;
        }else cont = true;

    }

public void validateSavedData(){


    if(!isAlpha(upperRightPanel.getCustomerNameTF().getText())){
        JOptionPane.showMessageDialog(null,"Please enter a valid customer name", "Warning",JOptionPane.ERROR_MESSAGE);
        cont =false;
        return;
    }else cont = true;
    for (int i = 0; i < invoicesList.get(selectedInvoice).getInvoiceItems().size(); i++) {
        String itemName = (String) rightPanel.getInvoiceItems().getValueAt(i, 1);
        if(itemName==null){
            JOptionPane.showMessageDialog(null,"Please enter a valid item name", "Warning",JOptionPane.ERROR_MESSAGE);
            cont =false;
            return;
        }else cont = true;
        String itemPrice = rightPanel.getInvoiceItems().getValueAt(i, 2).toString();
        if(!isDouble(itemPrice)){
            JOptionPane.showMessageDialog(null,"Please enter a valid price","Warning",JOptionPane.ERROR_MESSAGE);
            cont =false;
            return;
        }else cont = true;
        String itemCount = rightPanel.getInvoiceItems().getValueAt(i, 3).toString();
        if(!isNumeric(itemCount)){
            JOptionPane.showMessageDialog(null,"Please enter a valid count","Warning",JOptionPane.ERROR_MESSAGE);
            cont =false;
            return;
        }else cont = true;
    }



}
    public static boolean checkDate(String dt1)
    {
        SimpleDateFormat df1=new SimpleDateFormat("dd-MM-yyyy");
        df1.setLenient(false);
        try {
            df1.parse(dt1);
        } catch (ParseException e) {

            return false;
        }
        return true;
    }
    public static boolean isAlpha(String s) {
        return s != null && !s.isEmpty() && s.matches("^[a-zA-Z]*$");
    }
    public static boolean isAlphaNumeric(String s) {
        return s != null && !s.isEmpty() && s.matches("^[a-zA-Z0-9]*$");
    }
    public static boolean isNumeric(String s) {
        return s != null && !s.isEmpty() && s.matches("^[0-9]*$");
    }
    public static boolean isDouble(String s) {
        return s != null&& !s.isEmpty() && s.matches("^[.0-9]*$");
    }
    public void OptionPaneExample(){

        String name=JOptionPane.showInputDialog("Enter Name");
    }
}
