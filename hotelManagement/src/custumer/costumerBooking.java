/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package custumer;

import admin.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class costumerBooking extends javax.swing.JInternalFrame {

    String tumerName;
    
    public costumerBooking(String cName) {
        this.tumerName = cName;
        initComponents();
        Connect();
        showTable();
        background();
    }
    
    Connection con; 
    PreparedStatement pst;
    ResultSet rs; 
    
    public final void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel_management", "root", "");
            System.out.println("Database connection successful.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(manageRoom.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
    }
    
  
    public final void showTable() {
        try {
            String customerName = this.tumerName;
            pst = con.prepareStatement("SELECT * FROM booking WHERE costumerName = ?");
            pst.setString(1, customerName);
            rs = pst.executeQuery();

            DefaultTableModel bookingModel = (DefaultTableModel) tblbooking.getModel();
            bookingModel.setRowCount(0);

            while (rs.next()) {
                int bookingID = rs.getInt("bookingID");
            
                int roomID = rs.getInt("roomID");
                Date checkIn = rs.getDate("checkIn");
                Date checkOut = rs.getDate("checkOut");
                String status = rs.getString("status");
                double total = rs.getDouble("total");

                bookingModel.addRow(new Object[]{bookingID, roomID, checkIn, checkOut, total, status});
            }
        } catch (SQLException ex) {
            Logger.getLogger(manageRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    
    public final void background(){
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UI = (BasicInternalFrameUI) this.getUI();
        UI.setNorthPane(null);
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        s1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbooking = new rojerusan.RSTableMetro();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1019, 600));
        setSize(new java.awt.Dimension(1019, 600));

        jPanel1.setBackground(new java.awt.Color(52, 48, 62));
        jPanel1.setPreferredSize(new java.awt.Dimension(1019, 600));

        s1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s1ActionPerformed(evt);
            }
        });
        s1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                s1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s1KeyReleased(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(90, 84, 117));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SEARCH");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BOOKING RECORDS");

        tblbooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Room", "Check-in", "Check-out", "Total", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblbooking.setColorBackgoundHead(new java.awt.Color(255, 255, 255));
        tblbooking.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tblbooking.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tblbooking.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblbooking.setColorFilasForeground1(new java.awt.Color(61, 58, 87));
        tblbooking.setColorFilasForeground2(new java.awt.Color(61, 58, 87));
        tblbooking.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tblbooking.setColorSelBackgound(new java.awt.Color(61, 58, 87));
        tblbooking.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        tblbooking.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblbooking.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblbooking.setGridColor(new java.awt.Color(204, 204, 204));
        tblbooking.setRowHeight(30);
        tblbooking.setSelectionBackground(new java.awt.Color(61, 58, 87));
        tblbooking.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblbooking.setShowGrid(false);
        jScrollPane1.setViewportView(tblbooking);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void s1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s1ActionPerformed

    private void s1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s1KeyPressed
       
    }//GEN-LAST:event_s1KeyPressed

    private void s1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s1KeyReleased
        DefaultTableModel obj =(DefaultTableModel) tblbooking.getModel();
        TableRowSorter<DefaultTableModel> obj1=new TableRowSorter<>(obj);
        tblbooking.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(s1.getText()));
    }//GEN-LAST:event_s1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField s1;
    private rojerusan.RSTableMetro tblbooking;
    // End of variables declaration//GEN-END:variables
}
