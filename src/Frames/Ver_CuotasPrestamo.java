/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lenovo
 */
public class Ver_CuotasPrestamo extends JDialog{
    private final String        CCcliente,Prestamo;
    private JLabel              jlPrestamo, jlCliente,jlCuotasPagadas,jlCuotasEspera;
    private JButton             jbRegresar;
    private JPanel              panel;
    private JTable              tableEspera,tablePagadas;
    private DefaultTableModel   tableModel1,tablemodel2;
    //private final Ver_Cuotas    mainVerCuotas=this;
    
    
    public Ver_CuotasPrestamo(Ver_Prestamos mainframe, String CCcliente,String Prestamo){
        super(mainframe, true);
        this.CCcliente = CCcliente;
        this.Prestamo = Prestamo;
        setTitle("Prestamos");
	setSize(700, 450);
        setupWidgets();
        setupEvents();
        loadConcluidos();
        setVisible(true);
    }

    private void setupWidgets() {
        jlCliente = new JLabel("Cliente: " + CCcliente);
        jlCuotasEspera = new JLabel("Cuotas a espera de su pago");
        jlCuotasPagadas = new JLabel("Cuotas pagadas");
        jlPrestamo = new JLabel("Prestamo: " + Prestamo);
        
        jbRegresar = new JButton("Regresar");
        
        JScrollPane jscrollPane = new JScrollPane();
        JScrollPane jscrollPane2 = new JScrollPane();
        
        tablePagadas = new JTable();
        tableEspera = new JTable();
        
        panel = new JPanel(null);panel.setBackground(new Color(247,247,247));
                
        tableModel1 = new DefaultTableModel(
                new Object[][] {},
                new String[] {"N.", "Fecha de Pago", "Monto", "Estado", "Tipo de Pago", "Codigo Comprobante", "Fecha Efectiva"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==7;
            }
        };
        tablemodel2= new DefaultTableModel(
                new Object[][] {},
                new String[] {"N.","Fecha de Pago", "Monto", "Estado"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==4;
            }
        };
        
        tableEspera.setModel(tablemodel2);
        tableEspera.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableEspera.getColumnModel().getColumn(1).setPreferredWidth(60);
        tableEspera.getColumnModel().getColumn(2).setPreferredWidth(80);   
        tableEspera.getColumnModel().getColumn(3).setPreferredWidth(80);
        jscrollPane.setViewportView(tableEspera);
        
        tablePagadas.setModel(tableModel1);
        tablePagadas.getColumnModel().getColumn(0).setPreferredWidth(8);
        tablePagadas.getColumnModel().getColumn(1).setPreferredWidth(70);
        tablePagadas.getColumnModel().getColumn(2).setPreferredWidth(60);        
        tablePagadas.getColumnModel().getColumn(3).setPreferredWidth(45); 
        tablePagadas.getColumnModel().getColumn(4).setPreferredWidth(70); 
        tablePagadas.getColumnModel().getColumn(5).setPreferredWidth(80); 
        tablePagadas.getColumnModel().getColumn(6).setPreferredWidth(60); 
        jscrollPane2.setViewportView(tablePagadas);
        
        jlPrestamo.setBounds(20, 20, 150, 20);jlCliente.setBounds(500, 20, 120, 20);
        jlCuotasPagadas.setBounds(270, 50, 160, 20);
        jscrollPane2.setBounds(20, 75, 645, 130);
        jlCuotasEspera.setBounds(260, 200, 180, 20);
        jscrollPane.setBounds(20, 225, 645, 130);
        jbRegresar.setBounds(300, 370, 100, 20);
        
        panel.add(jlCliente);
        panel.add(jlCuotasEspera);
        panel.add(jlCuotasPagadas);
        panel.add(jlPrestamo);
        
        panel.add(jbRegresar);
        
        panel.add(jscrollPane2);
        panel.add(jscrollPane);
        add(panel,BorderLayout.CENTER);
        
    }

    private void setupEvents() {
        jbRegresar.setBackground(new Color(36, 83, 181));
        tableEspera.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        tablePagadas.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        jbRegresar.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
    }

    private void loadConcluidos() {
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql
                    = "Select * from cuotaprestamo where codprestamo like'"+Prestamo+"'   order by numerocuota";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {                
                String [] values = new String[7];
                String numerocuota = result.getString("numerocuota");
                Date fechpago = result.getDate("fechpago");
                String monto = result.getString("montocuota");
                String estado = result.getString("estadocuota");
                String tipo = result.getString("tipopago");
                String codcomprobante = result.getString("codcomprobante");
                Date fechefectiva = result.getDate("fechefectiva");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                if("Pagada".equals(estado)){
                    
                    values[0]= numerocuota;
                    values[1] = ""+dateFormat.format(fechpago);
                    values[2] = monto;
                    values[3] = estado;
                    values[4] = tipo;
                    values[5] = codcomprobante;
                    values[6] = ""+dateFormat.format(fechefectiva);
                    tableModel1.addRow(values);
                }else{
                    values[0]= numerocuota;
                    values[1] = ""+dateFormat.format(fechpago);
                    values[2] = monto;
                    values[3] = estado;
                    tablemodel2.addRow(values);
                }
            }
            result.close();
            st.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
    }
    
}
