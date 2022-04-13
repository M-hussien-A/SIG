package Model;

import Controller.Invoice;
import Controller.InvoiceItem;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadWriteData extends JFrame {

    private boolean initialized = false;


    public List<Invoice> loadInvoices() throws Exception {

        List<Invoice> invoices = new ArrayList<>();
        String fileIn;
        String line = null;
        fileIn=openPathDialog();
        FileReader fileReader = new FileReader(fileIn);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        if(!(fileIn == "")) {

            try {
                // Read all lines in from CSV file and add to studentList


                while ((line = bufferedReader.readLine()) != null) {
                    String[] temp = line.split(",");
                    int invoiceNo = Integer.parseInt(temp[0]);
                    Date invoiceDate = new SimpleDateFormat("dd-MM-yyyy").parse(temp[1]);
                    String customer = temp[2];
                      System.out.println(temp);
                    invoices.add(new Invoice(invoiceNo, invoiceDate, customer));
                }

            } catch (ParseException e) {
                //e.printStackTrace();
            }
            finally {
                bufferedReader.close();
            }
            //System.out.println(invoices.get(1).getInvoiceDate());

        }
        else{
            JOptionPane.showMessageDialog(null,"Please choose Path","warinig",JOptionPane.INFORMATION_MESSAGE);
        }
        printInitialInvoices(invoices);
        return invoices;

    }

    private void printInitialInvoices(List<Invoice> allInvoices) {
        for(int i=0;i<allInvoices.size();i++){
            System.out.print(allInvoices.get(i).getInvoiceNo()+",");
            System.out.print(allInvoices.get(i).getInvoiceDate()+",");
            System.out.print(allInvoices.get(i).getCustomer());
            System.out.println();
        }
    }

    public List<InvoiceItem> loadItems() throws Exception {

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        String fileIn = openPathDialog();
        String line = null;
        FileReader fileReader = new FileReader(fileIn);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        if (!(fileIn == "")) {
            try {
                // Read all lines in from CSV file and add to studentList



                while ((line = bufferedReader.readLine()) != null) {
                    String[] temp = line.split(",");
                    int invoiceNo = Integer.parseInt(temp[0]);
                    String itemName = temp[1];
                    Double itemPrice = Double.parseDouble(temp[2]);
                    int count = Integer.parseInt(temp[3]);
                    invoiceItems.add(new InvoiceItem(itemName, itemPrice, count, invoiceNo));
                }

            } /*catch (ParseException e) {
            e.printStackTrace();
        }*/

            finally {
                bufferedReader.close();
            }

            System.out.println(invoiceItems.get(1).getItemTotal());
            initialized = true;

        }
        else{
            JOptionPane.showMessageDialog(null,"Please choose Path","warinig",JOptionPane.INFORMATION_MESSAGE);
        }
        return invoiceItems;
    }

    public void saveContent(List<Invoice> allInvoices,List<InvoiceItem>allItems) throws Exception{
    String invoiceFileOut = openSaveDialog();//"src/DataFiles/InvoiceHeader.csv";//openSaveDialog();
    String itemFileOut = openSaveDialog();//"src/DataFiles/InvoiceLine.csv";//openSaveDialog();
    String line = null;
    FileWriter invoiceFileWriter  = new FileWriter (invoiceFileOut);
    BufferedWriter invoiceBufferedWriter = new BufferedWriter(invoiceFileWriter);
    FileWriter itemFileWriter  = new FileWriter (itemFileOut);
    BufferedWriter itemBufferedWriter = new BufferedWriter(itemFileWriter);
    if (!(invoiceFileOut == ""&itemFileOut=="")) {
        try {
            // Read all lines in from CSV file and add to studentList

            for(int i=0; i<allInvoices.size();i++){
                invoiceBufferedWriter.write(allInvoices.get(i).getInvoiceNo()+ ",");
                invoiceBufferedWriter.write((String) allInvoices.get(i).getInvoiceDate()+ ",");
                invoiceBufferedWriter.write(allInvoices.get(i).getCustomer()+ System.lineSeparator());
               // invoiceBufferedWriter.write(System.lineSeparator());
                System.out.print(allInvoices.get(i).getCustomer()+ ",");

            }
            invoiceBufferedWriter.flush();


            for(int i=0; i<allInvoices.size();i++){
                for(int j=0;j<allInvoices.get(i).getInvoiceItems().size();j++) {
                    itemBufferedWriter.write(allInvoices.get(i).getInvoiceItems().get(j).getInvoiceNo() + ",");
                    itemBufferedWriter.write(allInvoices.get(i).getInvoiceItems().get(j).getItemName() + ",");
                    itemBufferedWriter.write(allInvoices.get(i).getInvoiceItems().get(j).getItemPrice() + ",");
                    itemBufferedWriter.write(allInvoices.get(i).getInvoiceItems().get(j).getCount() + System.lineSeparator());
                    //itemBufferedWriter.write(System.lineSeparator());
                }

            }
            itemBufferedWriter.flush();


            JOptionPane.showMessageDialog(null,"Data saved successfully","Saved",JOptionPane.INFORMATION_MESSAGE);
                   } /*catch (ParseException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        } finally {
            invoiceBufferedWriter.close();
            invoiceFileWriter.close();
            itemBufferedWriter.close();
            itemFileWriter.close();

        }

    }
    else{
        JOptionPane.showMessageDialog(null,"Please choose Path","warinig",JOptionPane.INFORMATION_MESSAGE);
    }

}

    public String openPathDialog(){
        String fileIn = "";// = "src/DataFiles/InvoiceHeader.csv";
        JFileChooser filesave = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
        filesave.setFileFilter(filter);


        int i=filesave.showOpenDialog(this);
        if(i==JFileChooser.APPROVE_OPTION) {
            File f = filesave.getSelectedFile();
            fileIn = f.getPath();
        }
        return fileIn;
    }

    public String openSaveDialog(){
        String fileOut = "";// = "src/DataFiles/InvoiceHeader.csv";
        JFileChooser filesave = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
        filesave.setFileFilter(filter);


        int i=filesave.showSaveDialog(this);
        if(i==JFileChooser.APPROVE_OPTION) {
            File f = filesave.getSelectedFile();
            fileOut = f.getPath();
        }
        return fileOut;
    }

    public boolean isInitialized() {
        return initialized;
    }


}


