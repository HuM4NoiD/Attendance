package attendance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ratho
 */
public class AttendanceDivision extends javax.swing.JFrame {

    /**
     * Creates new form AttendanceDivision
     */
    int visible = 0;
    
    JFileChooser jfcA, jfcB;
    public AttendanceDivision() {
        initComponents();
        proceedB.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        title = new javax.swing.JLabel();
        importA = new javax.swing.JButton();
        importB = new javax.swing.JButton();
        proceedB = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Import Both Divisions");

        importA.setText("Division A");
        importA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importAActionPerformed(evt);
            }
        });

        importB.setText("Division B");
        importB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importBActionPerformed(evt);
            }
        });

        proceedB.setText("Proceed");
        proceedB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(importA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(importB)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(label1)
                .addContainerGap(217, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(proceedB)
                        .addGap(160, 160, 160))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(label1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importA)
                    .addComponent(importB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(proceedB)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importBActionPerformed
       
        jfcB =new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = jfcB.showOpenDialog(null);
        ArrayList<StudentDetails> list;
        
        if(r == JFileChooser.APPROVE_OPTION){
            File file = jfcB.getSelectedFile();
            title.setText("B chosen");
            
            list = getListFromExcel(file);
            if(list != null){
                for(StudentDetails s : list){
                    System.out.println(" " + s.getSapid() + " " + s.getName());
                }
                Statement st;
                for(int i = 0; i < list.size(); ++i){
                    StudentDetails stud = list.get(i);
                    Connection con = DBConnection.getDBConnection();
                    try{
                        st = con.createStatement();
                        String command1 = "INSERT INTO `detailsb` (`sapid`, `name`) VALUES ('"+stud.getSapid()+"', '"+stud.getName()+"');";
                        String command2 = "INSERT INTO `monthb` (`sapid`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`) VALUES ('"+stud.getSapid()+"', '', '', '', '', '', '', '', '', '', '');";
                        st.executeUpdate(command1);
                        st.executeUpdate(command2);
                    } catch (SQLException e){
                        JOptionPane.showMessageDialog(null,e);
                    }
                }
                
                visible++;
                importB.setEnabled(false);
            }
        }
        
        if(visible  == 2) proceedB.setEnabled(true);
        
    }//GEN-LAST:event_importBActionPerformed

    private void importAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importAActionPerformed
        jfcA =new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = jfcA.showOpenDialog(null);
        ArrayList<StudentDetails> list;
        
        if(r == JFileChooser.APPROVE_OPTION){
            File file = jfcA.getSelectedFile();
            title.setText("A chosen");
            
            list = getListFromExcel(file);
            if(list != null){
                for(StudentDetails s : list){
                    System.out.println(" " + s.getSapid() + " " + s.getName());
                }
                Statement st;
                for(int i = 0; i < list.size(); ++i){
                    StudentDetails stud = list.get(i);
                    Connection con = DBConnection.getDBConnection();
                    try{
                        st = con.createStatement();
                        String command1 = "INSERT INTO `detailsa` (`sapid`, `name`) VALUES ('"+stud.getSapid()+"', '"+stud.getName()+"');";
                        String command2 = "INSERT INTO `montha` (`sapid`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`) VALUES ('"+stud.getSapid()+"', '', '', '', '', '', '', '', '', '', '');";
                        st.executeUpdate(command1);
                        st.executeUpdate(command2);
                    } catch (SQLException e){
                        JOptionPane.showMessageDialog(null,e);
                    }
                }
                
                visible++;
                importA.setEnabled(false);
            }
        }
        
        if(visible  == 2) proceedB.setEnabled(true);
    }//GEN-LAST:event_importAActionPerformed

    private void proceedBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedBActionPerformed
        DashBoard as = new DashBoard();
        as.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_proceedBActionPerformed

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
            java.util.logging.Logger.getLogger(AttendanceDivision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendanceDivision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendanceDivision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendanceDivision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendanceDivision().setVisible(true);
            }
        });
    }
    
    public ArrayList<StudentDetails> getListFromExcel(File file){
        ArrayList<StudentDetails> studentList = new ArrayList();
        try{
            XSSFWorkbook w = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = w.getSheetAt(0);
            for(int i = sheet.getFirstRowNum();i<=sheet.getLastRowNum();++i){
               Row row = sheet.getRow(i); 
               long sap = 0;
               String name = "";
               for(int j = row.getFirstCellNum(); j<=row.getLastCellNum(); ++j){
                   Cell cell = row.getCell(j);
                   if(j == 0)sap = (long) cell.getNumericCellValue();
                   if(j == 1)name = cell.getStringCellValue();
               }
               StudentDetails student = new StudentDetails(sap,name);
               studentList.add(student);
            }
            return studentList;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton importA;
    private javax.swing.JButton importB;
    private javax.swing.JLabel label1;
    private javax.swing.JButton proceedB;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}