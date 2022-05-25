package org.example;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class View extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadFile,saveFile;
    private JPanel leftSidePanel, rightSidePanel, leftBtns, upperLeftPanel, rightUpper,rightDwnBtns, rightCenter;
    private JTable invoiceTbl , invItemsTbl;
    private String[] headerInvItemsTbl = {"No.", "Item Name", "Item Price", "Count", "Item Total"} ;
    private String[] headerInvTbl = {"No.", "Date", "Customer", "Total"};
    private String[][] dataInvItemsTbl;
    private String[][] dataInvTbl;
    private JSplitPane pane_split;
    private JButton createNewInvoiceBtn, deleteInvoiceBtn, saveBtn, cancelBtn;
    private JLabel invoiceNumValueLabel, invoiceTotalValueLabel;
    private JTextField invoiceDateTF, customerNameTF;
    //private Controller controller= null;
    public void setDataInvTbl(String[][] dataInvTbl) {
        this.dataInvTbl = dataInvTbl;
    }
    public void setDataInvItemsTbl(String[][] dataInvItemsTbl) {
        this.dataInvItemsTbl = dataInvItemsTbl;
    }
    public View() {
        super("SIG Application");
        setSize(1800,900);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1800, 900)); //Setting minimum window/JFrame Size
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();

        //Creating File Menu
        menuBar = new JMenuBar();
        loadFile = new JMenuItem("Load File");
        //loadFile.addActionListener(new Controller());
        loadFile.setActionCommand("loadFile");


        saveFile = new JMenuItem("Save File");
        saveFile.addActionListener(new Controller());
        saveFile.setActionCommand("saveFile");

        fileMenu = new JMenu("File");
        fileMenu.add(loadFile);
        fileMenu.add(saveFile);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar); // to display it in the frame

        leftSidePanel = new JPanel();
        leftSidePanel.setPreferredSize(new Dimension(800,800));
        leftSidePanel.setLayout(new BorderLayout());
        leftSidePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,0, true));
        leftSidePanel.setVisible(true);

        //Components of left side_panel
        ///////////////////////////////Table on the left panel
        upperLeftPanel = new JPanel(new GridLayout(1,2,3,2));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Invoices Table");
        titledBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
        upperLeftPanel.setBorder(titledBorder);
        try{
            //dataInvItemsTbl= controllerForDataInvTbl.getDataOfInvoiceTabel();
            invoiceTbl = new JTable(dataInvTbl ,headerInvTbl);
        } catch (NullPointerException n){
            System.out.println("NullPointerException");
        }
        upperLeftPanel.add(new JScrollPane(invoiceTbl));
        leftSidePanel.add(upperLeftPanel, BorderLayout.PAGE_START);
        ///////////////////////////////buttons on the left panel
        leftBtns = new JPanel(new GridLayout(1,2,3,2));

        createNewInvoiceBtn = new JButton("Create New Invoice");
        createNewInvoiceBtn.setActionCommand("createNewInvoiceBtn");
        leftBtns.add(createNewInvoiceBtn);
        createNewInvoiceBtn.addActionListener(new Controller()); // Controller plays role of an ActionListener

        deleteInvoiceBtn = new JButton("Delete Invoice");
        leftBtns.add(deleteInvoiceBtn);
        deleteInvoiceBtn.setActionCommand("deleteInvoice_btn"); // as if it is an id instead of -actionEvent.getSource().equals()-
        deleteInvoiceBtn.addActionListener(new Controller(invoiceTbl));
        leftSidePanel.add(leftBtns, BorderLayout.SOUTH);

        //Right side panel
        rightSidePanel = new JPanel();
        rightSidePanel.setPreferredSize(new Dimension(800,800));
        rightSidePanel.setLayout(new BorderLayout());
        rightSidePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,0, false));
        rightSidePanel.setVisible(true);

        //Components of right side_panel
        ////////////////////////////////////////////////////////////Right Top
        rightUpper = new JPanel(new GridLayout(4,2,2,3));

        rightUpper.add(new JLabel("Invoice Number"));

        invoiceNumValueLabel = new JLabel("23");
        rightUpper.add(invoiceNumValueLabel);

        rightUpper.add(new JLabel("Invoice Date"));

        invoiceDateTF = new JTextField(20);
        rightUpper.add(invoiceDateTF);

        rightUpper.add(new JLabel("Customer Name"));

        customerNameTF = new JTextField(20);
        customerNameTF.setActionCommand("customerNameTF");
        customerNameTF.addActionListener(new Controller());
        rightUpper.add(customerNameTF);

        rightUpper.add(new JLabel("Invoice Total"));

        invoiceTotalValueLabel = new JLabel("350.50");
        rightUpper.add(invoiceTotalValueLabel);

        rightSidePanel.add(rightUpper, BorderLayout.PAGE_START);
        ////////////////////////////////////////////////////////////Right center
        rightCenter = new JPanel(new GridLayout(0,1,2,3));
        rightCenter.setBorder(BorderFactory.createTitledBorder("Invoice Items"));

        try{
            //dataInvItemsTbl= controllerForDataInvTbl.getDataOfInvoiceTabel();
            invItemsTbl = new JTable(dataInvItemsTbl , headerInvItemsTbl);
        } catch (NullPointerException n){
            System.out.println("NullPointerException");
        }

        rightCenter.add(new JScrollPane(invItemsTbl));

        rightSidePanel.add(rightCenter, BorderLayout.CENTER);
        ///////////////////////////////////////////////////////////Right bottom
        rightDwnBtns = new JPanel(new GridLayout(1,2,3,2));

        saveBtn = new JButton("Save");
        rightDwnBtns.add(saveBtn);
        saveBtn.addActionListener(new Controller());
        saveBtn.setActionCommand("save_btn");

        cancelBtn = new JButton("Cancel");
        rightDwnBtns.add(cancelBtn);
        cancelBtn.addActionListener(new Controller());
        cancelBtn.setActionCommand("cancel_btn");

        rightSidePanel.add(rightDwnBtns, BorderLayout.SOUTH);

        pane_split = new JSplitPane();
        pane_split.setSize(1700,850);
        pane_split.setDividerSize(5);
        pane_split.setDividerLocation(800);
        pane_split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        pane_split.setLeftComponent(leftSidePanel);
        pane_split.setRightComponent(rightSidePanel);
        getContentPane().add(pane_split);

    }
}