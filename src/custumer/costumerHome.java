/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package custumer;


import admin.manageRoom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import login.signIn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.HorizontalAlignment;



/**
 *
 * @author yeojvaldez
 */
public class costumerHome extends javax.swing.JFrame {

   String tumerName;
    
    public costumerHome(String userName) {
        initComponents();
        this.tumerName = userName;
        txtcostumer.setText(userName);
        Connect();
        showPieChart();
        showBarChart();
        showRoom();
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
    
    public final void showRoom(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel_management", "root", "");
            pst = con.prepareStatement("SELECT * FROM room");
            rs = pst.executeQuery();
            
            DefaultTableModel roomModel = (DefaultTableModel) tblroom.getModel();
            roomModel.setRowCount(0);

                while (rs.next()) {
                    int roomID = rs.getInt("roomID");
                    String roomType = rs.getString("roomType");
                    double price = rs.getDouble("price");
                    String status = rs.getString("availability");
                  

                    roomModel.addRow(new Object[]{roomID, roomType, price, status});
                }
        } catch (SQLException ex) {
            Logger.getLogger(manageRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void showPieChart() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "");
            DefaultPieDataset pieDataset = new DefaultPieDataset();

            String query = "SELECT availability, COUNT(*) AS room_count FROM room GROUP BY availability";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            int totalRooms = 0;
            int availableRooms = 0;

         
            while (rs.next()) {
                String availability = rs.getString("availability");
                int roomCount = rs.getInt("room_count");

                if (availability.equalsIgnoreCase("Available")) {
                    availableRooms = roomCount;
                }
                totalRooms += roomCount;  
            }

            if (totalRooms > 0) {
                double availablePercentage = (double) availableRooms / totalRooms * 100;
                double unavailablePercentage = 100 - availablePercentage;

                
                pieDataset.setValue("Available (" + String.format("%.2f", availablePercentage) + "%)", availablePercentage);
                pieDataset.setValue("Unavailable (" + String.format("%.2f", unavailablePercentage) + "%)", unavailablePercentage);
            } else {
                pieDataset.setValue("No rooms available", 100);
            }

            JFreeChart pieChartObject = ChartFactory.createPieChart("Room Availability", pieDataset, true, true, false);
            PiePlot piePlot = (PiePlot) pieChartObject.getPlot();
            piePlot.setBackgroundPaint(Color.WHITE);
            piePlot.setOutlineVisible(false);

            pieChartObject.getLegend().setFrame(BlockBorder.NONE);
            pieChartObject.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);

            ChartPanel chartPanel = new ChartPanel(pieChartObject);
            chartPanel.setPreferredSize(new java.awt.Dimension(290, 251));

            chartAvailable.removeAll();
            chartAvailable.setLayout(new BorderLayout());
            chartAvailable.add(chartPanel, BorderLayout.CENTER);
            chartAvailable.validate();
            chartAvailable.repaint();

            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(manageRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void showBarChart() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "");
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            String query = "SELECT roomType, " +
                           "COUNT(*) AS available_rooms " +
                           "FROM room " +
                           "WHERE availability = 'Available' " + 
                           "GROUP BY roomType"; 

            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                String roomType = rs.getString("roomType");
                int availableRooms = rs.getInt("available_rooms");


                dataset.addValue(availableRooms, "Available", roomType);
            }


            JFreeChart barChart = ChartFactory.createBarChart(
                    "Room Type", 
                    "Room Type", 
                      "",
                    dataset,  
                    org.jfree.chart.plot.PlotOrientation.VERTICAL, 
                    true,  
                    true, 
                    false 
            );

            CategoryPlot plot = barChart.getCategoryPlot();
            plot.setBackgroundPaint(Color.white);


            plot.getRenderer().setSeriesPaint(0, new Color(90,84,117));


            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(290, 251));  

            barGraph.removeAll();  
            barGraph.setLayout(new BorderLayout());
            barGraph.add(chartPanel, BorderLayout.CENTER);
            barGraph.validate();


            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(manageRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }






    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlhm = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlbk = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlbkng = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnlcncl = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtcostumer = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlhome = new javax.swing.JPanel();
        chartAvailable = new javax.swing.JPanel();
        barGraph = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblroom = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(61, 58, 87));
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));
        jPanel1.setSize(new java.awt.Dimension(190, 800));

        pnlhm.setBackground(new java.awt.Color(61, 58, 87));
        pnlhm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlhmMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(31, 28, 44));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-home-30.png"))); // NOI18N
        jLabel1.setText("HOME");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlhmLayout = new javax.swing.GroupLayout(pnlhm);
        pnlhm.setLayout(pnlhmLayout);
        pnlhmLayout.setHorizontalGroup(
            pnlhmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlhmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlhmLayout.setVerticalGroup(
            pnlhmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlhmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlbk.setBackground(new java.awt.Color(61, 58, 87));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-booking-30.png"))); // NOI18N
        jLabel2.setText("BOOK");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlbkLayout = new javax.swing.GroupLayout(pnlbk);
        pnlbk.setLayout(pnlbkLayout);
        pnlbkLayout.setHorizontalGroup(
            pnlbkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlbkLayout.setVerticalGroup(
            pnlbkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlbkng.setBackground(new java.awt.Color(61, 58, 87));

        jLabel3.setBackground(new java.awt.Color(62, 74, 89));
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-booking-30 (2).png"))); // NOI18N
        jLabel3.setText("BOOKINGS");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlbkngLayout = new javax.swing.GroupLayout(pnlbkng);
        pnlbkng.setLayout(pnlbkngLayout);
        pnlbkngLayout.setHorizontalGroup(
            pnlbkngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbkngLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlbkngLayout.setVerticalGroup(
            pnlbkngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbkngLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlcncl.setBackground(new java.awt.Color(61, 58, 87));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-cancel-30.png"))); // NOI18N
        jLabel4.setText("CANCEL");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlcnclLayout = new javax.swing.GroupLayout(pnlcncl);
        pnlcncl.setLayout(pnlcnclLayout);
        pnlcnclLayout.setHorizontalGroup(
            pnlcnclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlcnclLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlcnclLayout.setVerticalGroup(
            pnlcnclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlcnclLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-logout-32.png"))); // NOI18N
        jLabel6.setText("LOG OUT");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlhm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlbk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlbkng, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlcncl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(pnlhm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlbk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlcncl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlbkng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(44, 42, 61));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1210, 100));

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-hotel-64 2.png"))); // NOI18N
        jLabel8.setText("  HOTEL BOOKING SYSTEM");

        txtcostumer.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtcostumer.setForeground(new java.awt.Color(255, 255, 255));
        txtcostumer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-user-32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
                .addComponent(txtcostumer, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcostumer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlhome.setBackground(new java.awt.Color(52, 48, 62));
        pnlhome.setPreferredSize(new java.awt.Dimension(1019, 600));

        chartAvailable.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(90, 84, 117)));
        chartAvailable.setPreferredSize(new java.awt.Dimension(290, 251));

        javax.swing.GroupLayout chartAvailableLayout = new javax.swing.GroupLayout(chartAvailable);
        chartAvailable.setLayout(chartAvailableLayout);
        chartAvailableLayout.setHorizontalGroup(
            chartAvailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        chartAvailableLayout.setVerticalGroup(
            chartAvailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        barGraph.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(90, 84, 117)));

        javax.swing.GroupLayout barGraphLayout = new javax.swing.GroupLayout(barGraph);
        barGraph.setLayout(barGraphLayout);
        barGraphLayout.setHorizontalGroup(
            barGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        barGraphLayout.setVerticalGroup(
            barGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 10, new java.awt.Color(90, 84, 117)));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 48, 62));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Hotel Room List");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(90, 84, 117)));

        tblroom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room Number", "Room Type", "Room Fee", "Availability"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblroom.setColorBackgoundHead(new java.awt.Color(255, 255, 255));
        tblroom.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tblroom.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tblroom.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblroom.setColorFilasForeground1(new java.awt.Color(61, 58, 87));
        tblroom.setColorFilasForeground2(new java.awt.Color(61, 58, 87));
        tblroom.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tblroom.setColorSelBackgound(new java.awt.Color(61, 58, 87));
        tblroom.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        tblroom.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblroom.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblroom.setGridColor(new java.awt.Color(204, 204, 204));
        tblroom.setRowHeight(40);
        tblroom.setSelectionBackground(new java.awt.Color(61, 58, 87));
        tblroom.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblroom.setShowGrid(false);
        jScrollPane2.setViewportView(tblroom);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlhomeLayout = new javax.swing.GroupLayout(pnlhome);
        pnlhome.setLayout(pnlhomeLayout);
        pnlhomeLayout.setHorizontalGroup(
            pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlhomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chartAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        pnlhomeLayout.setVerticalGroup(
            pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlhomeLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addComponent(chartAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(barGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1218, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlhome, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlhome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
  
    
        pnlhm.setBackground(new Color(90,84,117));
        pnlbk.setBackground(new Color(61,58,87));
        pnlcncl.setBackground(new Color(61,58,87));
        pnlbkng.setBackground(new Color(61,58,87));
        
        
        pnlhome.removeAll(); 
        pnlhome.revalidate();
        pnlhome.repaint();


        showPieChart(); 
        showBarChart(); 


        pnlhome.add(chartAvailable); 
        pnlhome.add(barGraph); 
        pnlhome.add(jPanel4); 
        pnlhome.add(jPanel3); 

        pnlhome.revalidate();
        pnlhome.repaint();
    
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        pnlbk.setBackground(new Color(90,84,117));
        pnlhm.setBackground(new Color(61,58,87));
        pnlcncl.setBackground(new Color(61,58,87));
        pnlbkng.setBackground(new Color(61,58,87));
        
        String cName = this.tumerName;
        costumerBook costumerBookFrame = new costumerBook(cName);
        pnlhome.removeAll();
        pnlhome.add(costumerBookFrame);
        costumerBookFrame.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        pnlbkng.setBackground(new Color(90,84,117));
        pnlhm.setBackground(new Color(61,58,87));
        pnlcncl.setBackground(new Color(61,58,87));
        pnlbk.setBackground(new Color(61,58,87));
        
        String cName = this.tumerName;
        costumerBooking costumerBookingFrame = new costumerBooking(cName);
        pnlhome.removeAll();
        pnlhome.add(costumerBookingFrame);
        costumerBookingFrame.setVisible(true);
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        pnlcncl.setBackground(new Color(90,84,117));
        pnlhm.setBackground(new Color(61,58,87));
        pnlbkng.setBackground(new Color(61,58,87));
        pnlbk.setBackground(new Color(61,58,87));
        
        String cName = this.tumerName;
        costumerCancel costumerCancelFrame = new costumerCancel(cName);
        pnlhome.removeAll();
        pnlhome.add(costumerCancelFrame);
        costumerCancelFrame.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            window.dispose();
        }
        new signIn().setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void pnlhmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlhmMouseClicked
       
    }//GEN-LAST:event_pnlhmMouseClicked

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
            java.util.logging.Logger.getLogger(costumerHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(costumerHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(costumerHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(costumerHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String userName = "Default Name";
            new costumerHome(userName).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barGraph;
    private javax.swing.JPanel chartAvailable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlbk;
    private javax.swing.JPanel pnlbkng;
    private javax.swing.JPanel pnlcncl;
    private javax.swing.JPanel pnlhm;
    private javax.swing.JPanel pnlhome;
    private rojerusan.RSTableMetro tblroom;
    private javax.swing.JLabel txtcostumer;
    // End of variables declaration//GEN-END:variables
}
