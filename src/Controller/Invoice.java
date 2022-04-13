package Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private static int invoiceId;
    private int invoiceNo;
    private Date invoiceDate;
    private String customer;
    private double total;
    private List<InvoiceItem> invoiceItems;



    public Invoice() {
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceId++;
    }

    public Invoice(int invoiceNo, Date invoiceDate, String customer) {
        this();
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.customer = customer;
    }



    public List<InvoiceItem> getInvoiceItems() {
        return this.invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems=invoiceItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public String getInvoiceDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(this.invoiceDate);
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
