/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 *
 * @author lenovo
 */
public class Ver_Prestamos extends JDialog{

    private final String CCcliente;
    private String prestamo;
    private JLabel              jlEspera, jlPrestamosConcluidos;
    private JButton             jbVerPrestamo, jbVerCronogramaCuotas;
    private JPanel              panel;
    private JTable              tableEspera,tableConcluidos;
    private DefaultTableModel   tableModel1,tablemodel2;
    private final Ver_Prestamos       mainVerPrestamos=this;
    
    
    public Ver_Prestamos(MenuPrincipal mainframe,String CCcliente1){
        super(mainframe, true);
        this.CCcliente = CCcliente1 ;
        setTitle("Prestamos");
	setSize(600, 420);
        setupWidgets();
        setupEvents();
        loadConcluidos();
        setVisible(true);
    }

    private void setupWidgets() {
        jlPrestamosConcluidos = new JLabel("Prestamos Concluidos:");
        jlEspera = new JLabel("En Espera:");
        
        jbVerCronogramaCuotas = new JButton("Ver Cronograma de Cuotas");
        jbVerPrestamo = new JButton("Ver Prestamo");
        
        JScrollPane jscrollPane = new JScrollPane();
        JScrollPane jscrollPane2 = new JScrollPane();
        
        tableConcluidos = new JTable();
        tableEspera = new JTable();
        
        panel = new JPanel(null);panel.setBackground(new Color(247,247,247));
                
        tableModel1 = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Codigo", "Fecha Solicitud", "Monto"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==3;
            }
        };
        tablemodel2= new DefaultTableModel(
                new Object[][] {},
                new String[] {"Codigo","Fecha Finalizacion", "Monto", "Estado"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==4;
            }
        };
        
        tableEspera.setModel(tableModel1);
        tableEspera.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableEspera.getColumnModel().getColumn(1).setPreferredWidth(60);
        tableEspera.getColumnModel().getColumn(2).setPreferredWidth(80);   
        jscrollPane2.setViewportView(tableEspera);
        
        tableConcluidos.setModel(tablemodel2);
        tableConcluidos.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableConcluidos.getColumnModel().getColumn(1).setPreferredWidth(60);
        tableConcluidos.getColumnModel().getColumn(2).setPreferredWidth(80);        
        tableConcluidos.getColumnModel().getColumn(3).setPreferredWidth(40); 
        jscrollPane.setViewportView(tableConcluidos);
        
        jlEspera.setBounds(20, 20, 90, 20);
        jscrollPane2.setBounds(20, 50, 545, 70);
        jlPrestamosConcluidos.setBounds(20, 150, 160, 20);
        jscrollPane.setBounds(20, 190, 545, 130);
        jbVerPrestamo.setBounds(130, 340, 160, 20);
        jbVerCronogramaCuotas.setBounds(310, 340, 190, 20);
        
        panel.add(jlEspera);
        panel.add(jlPrestamosConcluidos);
        
        panel.add(jbVerCronogramaCuotas);
        panel.add(jbVerPrestamo);
        
        panel.add(jscrollPane2);
        panel.add(jscrollPane);
        add(panel,BorderLayout.CENTER);
        
    }

    private void setupEvents() {
        
        tableConcluidos.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        tableConcluidos.getTableHeader().setResizingAllowed(false);//evita redimencionar las columnas
        
        tableEspera.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        tableEspera.getTableHeader().setResizingAllowed(false);//evita redimencionar las columnas
        jbVerCronogramaCuotas.setEnabled(false);
        jbVerPrestamo.setEnabled(false);

        
        jbVerPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        jbVerCronogramaCuotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ver_Cuotas ver_cuotas = new Ver_Cuotas(mainVerPrestamos,CCcliente,prestamo);
            }
        });
        
        tableConcluidos.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                tableEspera.clearSelection();
                int row = tableConcluidos.getSelectedRow();
                try {
                    prestamo=""+tablemodel2.getValueAt(row, 0);
                    jbVerCronogramaCuotas.setEnabled(true);
                    jbVerPrestamo.setEnabled(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    jbVerCronogramaCuotas.setEnabled(false);
                    jbVerPrestamo.setEnabled(false);
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        @Override
        public void mouseReleased(MouseEvent e) {
            tableEspera.clearSelection();
            if (tableConcluidos.getSelectedRows().length>1){
                tableConcluidos.clearSelection();
                jbVerCronogramaCuotas.setEnabled(false);
                jbVerPrestamo.setEnabled(false);
                Toolkit.getDefaultToolkit().beep();
            }
        }
    });
        
        tableEspera.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                tableConcluidos.clearSelection();
                int row = tableEspera.getSelectedRow();
                try {
                    prestamo=""+tableModel1.getValueAt(row, 0);
                    jbVerCronogramaCuotas.setEnabled(true);
                    jbVerPrestamo.setEnabled(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    jbVerCronogramaCuotas.setEnabled(false);
                    jbVerPrestamo.setEnabled(false);
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                tableConcluidos.clearSelection();
                if (tableEspera.getSelectedRows().length>1){
                    tableEspera.clearSelection();
                    jbVerCronogramaCuotas.setEnabled(false);
                    jbVerPrestamo.setEnabled(false);
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }
    
    private void loadConcluidos(){
        int rowcont=tableConcluidos.getRowCount();
        for (int i = 0; i < rowcont; i++) {
                tablemodel2.removeRow(i);
        }
        int rowcont2=tableEspera.getRowCount();
        for (int i = 0; i < rowcont2; i++) {
                tableModel1.removeRow(i);
        }
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql
                    = "Select codprestamo,fechterminopre,montoprestamo,estadoprestamo,fechsolicitudpre from prestamos where ceduprestatario like'"+CCcliente+"'   order by 5";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {                
                String [] values = new String[4];
                String Cod = result.getString("codprestamo");
                String fechat = result.getString("fechterminopre");
                String monto = result.getString("montoprestamo");
                String estado = result.getString("estadoprestamo");
                String fechas = result.getString("fechsolicitudpre");
                if("pendiente".equals(estado)){
                    
                    values[0]= Cod;
                    values[1] = fechas;
                    values[2] = monto;
                    tableModel1.addRow(values);
                }else{
                    values[0]= Cod;
                    values[1] = fechat;
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
