package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;
import org.example.View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller implements ActionListener {

    public Controller() {}

    private ActionEvent actionEvent;
    private View view;
    private InvoiceHeader invoiceHeader;
    private InvoiceLine invoiceLine;
    private static String status;
    private JTable headerJTable;

    public Controller(View view, InvoiceHeader invoiceHeader) {
        this.view=view;
        this.invoiceHeader=invoiceHeader;
    }

    public Controller(ActionEvent actionEvent) throws ClassNotFoundException {
        this.actionEvent = actionEvent;
    }

    public Controller(JTable headerJTable) {
        this.headerJTable = headerJTable;
    }

    protected String[][] getDataOfInvoiceTabel(){
        ArrayList<InvoiceHeader> arrayList = new ArrayList<>();
        try {
            arrayList = invoiceHeader.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int numberOfColums = 4;
        int numberOfRows = arrayList.size();
        String[][] invoiceHData = new String[numberOfRows][numberOfColums];

        for(int x=0; x<arrayList.size(); x++) {
            InvoiceHeader data = arrayList.get(x);
            invoiceHData[x][0] = String.valueOf(data.getInvoiceNum());
            invoiceHData[x][1] = data.getInvoiceDate().toString();
            invoiceHData[x][2] = data.getCustomerName();
            invoiceHData[x][3] = String.valueOf(data.getTotal());
        }
        return invoiceHData;
    }

    protected String[][] getDataOfLineTabel(){
        ArrayList<InvoiceLine> arrayList = new ArrayList<>();
        arrayList = invoiceLine.readFile();
        int numberOfColums = 5;
        int numberOfRows = arrayList.size();
        String[][] invoiceIData = new String[numberOfRows][numberOfColums];

        for(int x=0; x<arrayList.size(); x++) {
            InvoiceLine  data = arrayList.get(x);
            invoiceIData[x][0] = String.valueOf(data.getInvoiceNum());
            invoiceIData[x][1] = data.getItemName();
            invoiceIData[x][2] = String.valueOf(data.getItemPrice());
            invoiceIData[x][3] = String.valueOf(data.getCount());
            invoiceIData[x][4] = String.valueOf(data.getItemTotal());
        }
        return invoiceIData;
    }


    protected void deleteHeaderSelectedRow(){
        DefaultTableModel tableModel = (DefaultTableModel) this.headerJTable.getModel();
        int[] selectedRows = headerJTable.getSelectedRows();
        if(selectedRows.length > 0){
            for (int i=0; i< selectedRows.length; i++){
                tableModel.removeRow(selectedRows[i]);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       switch (actionEvent.getActionCommand()){
           case "loadFile" :
               view.setDataInvTbl(getDataOfInvoiceTabel());
               view.setDataInvTbl(getDataOfLineTabel());
               status = "load";
               break;

           case "saveFile" :
               status = "Saved";
               break;

           case "createNewInvoiceBtn" :
               status = "Created";
               break;

           case "customerNameTF" :
               break;

           case "deleteInvoice_btn" :
               deleteHeaderSelectedRow();
               status = "Deleted";
               break;

           case "save_btn" :
               status = "Saved";
               break;

           case "cancel_btn" :
               status = "Canceled";
               break;

        }
    }
}
