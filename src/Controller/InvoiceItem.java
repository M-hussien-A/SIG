package Controller;

public class InvoiceItem {

    private String itemName;
    private double itemPrice;
    private int count;
    private int invoiceNo;
    private double itemTotal;



    public InvoiceItem() {

    }

    public InvoiceItem(String itemName, double itemPrice, int count, int invoiceNo) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoiceNo = invoiceNo;
        setItemTotal(itemPrice*count);
    }



    public int getInvoiceNo() {
        return invoiceNo;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getCount() {
        return count;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }
}
