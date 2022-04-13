package Controller;

import java.util.ArrayList;
import java.util.List;

public class HandlingFunctions {
    public  List<InvoiceItem> getSelectedItems(List<InvoiceItem> allItems,int invoiceNumber) {
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        for(int i=0; i <allItems.size() ;i++){
            if (allItems.get(i).getInvoiceNo()==invoiceNumber){
                invoiceItems.add(allItems.get(i));
            }
        }
        return invoiceItems;
    }
    public void addTotalToInvoiceList(List<Invoice> allInvoices){
      for(int i=0; i <allInvoices.size() ;i++) {
          double total = 0;
          System.out.println(allInvoices.get(i).getInvoiceItems().isEmpty());
          if (!allInvoices.get(i).getInvoiceItems().isEmpty()) {

              for (int j = 0; j < allInvoices.get(i).getInvoiceItems().size(); j++) {

                  total = total + allInvoices.get(i).getInvoiceItems().get(j).getItemTotal();
              }
              allInvoices.get(i).setTotal(total);
          }
      }
  }
    public String[][] invoiceTableData(List<Invoice> allInvoices){
    String[] col1 = new String[allInvoices.size()];
    String[] col2 = new String[allInvoices.size()];
    String[] col3 = new String[allInvoices.size()];
    String[] col4 = new String[allInvoices.size()];

        for(int i=0; i <allInvoices.size() ;i++) {

        col1[i] = Integer.toString(allInvoices.get(i).getInvoiceNo());
        col2[i] = allInvoices.get(i).getInvoiceDate();
        col3[i] = allInvoices.get(i).getCustomer();
        col4[i] = Double.toString(allInvoices.get(i).getTotal());

    }
    String[][] hhh ={
            col1,
            col2,
            col3,
            col4
    }  ;

  // String[][] hhh =new String[2][allInvoices.size()] ;
    System.out.println(hhh);//System.out.println(data);

    return  transposeData(hhh);

}
    public String[][] itemTableData(List<InvoiceItem> allItems){
        String[] col1 = new String[allItems.size()];
        String[] col2 = new String[allItems.size()];
        String[] col3 = new String[allItems.size()];
        String[] col4 = new String[allItems.size()];
        String[] col5 = new String[allItems.size()];
        for(int i=0; i <allItems.size() ;i++) {

            col1[i] = Integer.toString(i+1);
            col2[i] = allItems.get(i).getItemName();
            col3[i] = Double.toString(allItems.get(i).getItemPrice());
            col4[i] = Integer.toString(allItems.get(i).getCount());
            col5[i] = Double.toString(allItems.get(i).getItemTotal());
        }
        String[][] hhh ={
                col1,
                col2,
                col3,
                col4,
                col5
        }  ;

        // String[][] hhh =new String[2][allInvoices.size()] ;
        System.out.println(hhh);//System.out.println(data);

        return  transposeData(hhh);

    }
    public static String[][] transposeData(String [][] data){
        String[][] temp = new String[data[0].length][data.length];
        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[0].length; j++)
                temp[j][i] = data[i][j];
        return temp;
    }
    public List<Invoice> assignItemsToInvoices(List<Invoice> allInvoices,List<InvoiceItem> allItems){

        for(int i=0; i <allInvoices.size() ;i++){
            List<InvoiceItem> items = new ArrayList<>();
            for(int j=0; j <allItems.size() ;j++){
            if(allInvoices.get(i).getInvoiceNo()==allItems.get(j).getInvoiceNo()){
                items.add(allItems.get(j));
                  if (items.isEmpty()){
                      System.out.println("ahpoooo");
                  }

            }
        }
            allInvoices.get(i).setInvoiceItems(items);

        }
        return allInvoices;
    }
    public int getNewInvoiceNumber(List<Invoice> allInvoices) {
      int lastInvoiceNumber= 0;
        for (int i = 0; i < allInvoices.size(); i++) {
           if( lastInvoiceNumber<allInvoices.get(i).getInvoiceNo()){
               lastInvoiceNumber=allInvoices.get(i).getInvoiceNo();
           };
        }
        return lastInvoiceNumber+1;
    }
}
