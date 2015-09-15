/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_browser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import javax.rmi.CORBA.Util;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import web_browser.ResultSetTableModel;
/**
 *
 * @author Blogger
 */
public class History extends javax.swing.JFrame {
    
    
    ArrayList<File> historyFiles = new ArrayList<>();
    ArrayList<Connection> conn = new ArrayList<>();
    ArrayList<Statement> stmts = new ArrayList<>();
    ArrayList<ResultSet> rsa = new ArrayList<>();
            
    Utilities utilities = new Utilities();
    final ResultSetTableModel model = new ResultSetTableModel();
    JFrame parent = null;

    /**
     * Creates new form History
     */
    
    public History() 
    {
        initComponents();
        jTable_History.setShowGrid(true);
    }
    
    public History(JFrame frame) 
    {
        initComponents();
        jTable_History.setShowGrid(true);
        parent = frame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_History = new javax.swing.JTable();
        button_ChromeHistory = new javax.swing.JButton();
        button_MozillaHistory = new javax.swing.JButton();
        exportExcel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboSelectUser = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View History");
        setName("View History"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable_History.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable_History);

        button_ChromeHistory.setText("Load Google Chrome History");
        button_ChromeHistory.setLabel("Load Google Chrome History");
        button_ChromeHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChromeHistoryActionPerformed(evt);
            }
        });

        button_MozillaHistory.setText("Load Mozilla Firefox History");
        button_MozillaHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_MozillaHistoryActionPerformed(evt);
            }
        });

        exportExcel.setLabel("Export to Excel");
        exportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportExcelActionPerformed(evt);
            }
        });

        jLabel1.setText("Select User:");

        String userDirectory = System.getProperty("user.home");
        File root = new File("C:\\Users\\");
            File[] list = root.listFiles();

            for ( File f : list )
            {
                if ( f.isDirectory() )
                {
                    comboSelectUser.addItem(f.getName());
                }
            }

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(comboSelectUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(button_ChromeHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(button_MozillaHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(button_ChromeHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button_MozillaHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(comboSelectUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addContainerGap())
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void button_ChromeHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChromeHistoryActionPerformed
        try
        {
            historyFiles = utilities.getChromeFiles("C:\\Users\\"+comboSelectUser.getSelectedItem()+"\\AppData\\Local\\Google\\Chrome\\User Data", "History");
            
            if(historyFiles.size() >= 1)
            {
                for (int i=0; i<historyFiles.size();i++)
                {
                    conn.add(i,DbController.getConnection(historyFiles.get(i).toString()));
                    conn.get(i).setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                    stmts.add(conn.get(i).createStatement());
                    rsa.add (stmts.get(i).executeQuery("SELECT ur.url as Url, ur.title as Title,"
                            + "datetime((vi.visit_time / 1000000) + (strftime('%s', '1601-01-01')), 'unixepoch','localtime') as Date "
                            + "FROM visits vi INNER JOIN urls ur "
                            +  "on ur.id = vi.url ORDER BY Date DESC;"));
                }
                model.setResultSet(rsa);
                System.out.println ("Row Count : " + model.getRowCount());
                jTable_History.setModel(model); 
                jTable_History.setAutoCreateRowSorter(true);
            }
            else
            {
                JOptionPane.showMessageDialog(jTable_History, "No Google Chrome history found on this system");
            }         
        }
        catch (Exception ex)
        {
            if (ex.getMessage().contains("The database file is locked"))
            {
                JOptionPane.showMessageDialog(jTable_History, "Please Close google chrome and try again in 30 seconds");
            }
            else
            {
                System.out.println("Catch Error in button_ChromeHistoryActionPerformed: " + ex.getMessage());
            }
        }
        finally
        {
            try 
            {
                historyFiles.clear(); conn.clear(); stmts.clear();rsa.clear();   
            } 
            catch (Exception ex) 
            {
               System.out.println("Finally Error in button_ChromeHistoryActionPerformed: "  + ex.getMessage() );
            }
        }
    }//GEN-LAST:event_button_ChromeHistoryActionPerformed

    private void button_MozillaHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_MozillaHistoryActionPerformed
        try
        {
            historyFiles = utilities.getMozillaFiles("C:\\Users\\"+comboSelectUser.getSelectedItem()+"\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles", "places.sqlite");

            if(historyFiles.size() >= 1)
            {
                for (int i=0; i<historyFiles.size();i++)
                {
                    conn.add(i,DbController.getConnection(historyFiles.get(i).toString()));
                    stmts.add(conn.get(i).createStatement());
                    rsa.add (stmts.get(i).executeQuery("SELECT url as Url,"
                            + "datetime((moz_historyvisits.visit_date/1000000), 'unixepoch','localtime') as Date FROM "
                            + "moz_historyvisits INNER JOIN moz_places "
                            + "on moz_historyvisits.place_id = moz_places.id ORDER BY Date DESC;"));
                }
                model.setResultSet(rsa);
                System.out.println ("Row Count : " + model.getRowCount());
                jTable_History.setModel(model); 
                jTable_History.setAutoCreateRowSorter(true);
            }
            else
            {
                JOptionPane.showMessageDialog(jTable_History, "No Mozilla Firefox history found on this system");
            }         

        }
        catch (Exception ex)
        {
            System.out.println("Catch Error in button_MozillaHistoryActionPerformed: " + ex.getMessage());
        }
        finally
        {
            try 
            {
                historyFiles.clear(); conn.clear(); stmts.clear();rsa.clear();
            } 
            catch (Exception ex) 
            {
               System.out.println("Finally Error in button_MozillaHistoryActionPerformed: "  + ex.getMessage() );
            }
        }
    }//GEN-LAST:event_button_MozillaHistoryActionPerformed

    private void exportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportExcelActionPerformed
        utilities.toExcel(jTable_History, this);
    }//GEN-LAST:event_exportExcelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        parent.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                History hist = new History();
                Utilities.setFrameLocation(hist);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ChromeHistory;
    private javax.swing.JButton button_MozillaHistory;
    private javax.swing.JComboBox comboSelectUser;
    private javax.swing.JButton exportExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_History;
    // End of variables declaration//GEN-END:variables
}
