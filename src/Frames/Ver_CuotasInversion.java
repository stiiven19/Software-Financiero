/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import softwarefinancieroap.Cuota;

/**
 *
 * @author lenovo
 */
public class Ver_CuotasInversion extends JDialog{
    private final String        CCcliente,inversion;
    private JLabel              jlInversion, jlCliente,jlCuotasPagadas,jlCuotasEspera;
    private JButton             jbRegresar,jbPagarCuota;
    private JPanel              panel;
    private JTable              tableEspera,tablePagadas;
    private DefaultTableModel   tableModel1,tablemodel2;
    private String              Numerocuota;
    private final Ver_CuotasInversion    mainVerCuotas=this;
    private boolean cargo;
    
    
    public Ver_CuotasInversion(Ver_Inversiones mainframe, String CCcliente,String inversion,boolean cargo){
        super(mainframe, true);
        this.cargo = cargo;
        this.CCcliente = CCcliente;
        this.inversion = inversion;
        setTitle("Inversiones");
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
        jlInversion = new JLabel("Inversion: " + inversion);
        
        jbRegresar = new JButton("Regresar");
        jbPagarCuota = new JButton("Pagar Cuota");
        
        JScrollPane jscrollPane = new JScrollPane();
        JScrollPane jscrollPane2 = new JScrollPane();
        
        tablePagadas = new JTable();
        tableEspera = new JTable();
        
        panel = new JPanel(null);panel.setBackground(new Color(247,247,247));
                
        tableModel1 = new DefaultTableModel(
                new Object[][] {},
                new String[] {"N.", "Fecha de Pago", "Monto", "Numero Cuenta", "Tipo de Pago", "Codigo Comprobante", "Fecha Efectiva"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==7;
            }
        };
        tablemodel2= new DefaultTableModel(
                new Object[][] {},
                new String[] {"N.","Fecha de Pago", "Monto","Numero Cuenta", "Estado"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==5;
            }
        };
        
        tableEspera.setModel(tablemodel2);
        tableEspera.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableEspera.getColumnModel().getColumn(1).setPreferredWidth(60);
        tableEspera.getColumnModel().getColumn(2).setPreferredWidth(80);   
        tableEspera.getColumnModel().getColumn(3).setPreferredWidth(80);
        tableEspera.getColumnModel().getColumn(4).setPreferredWidth(60);
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
        
        jlInversion.setBounds(20, 20, 150, 20);jlCliente.setBounds(500, 20, 120, 20);
        jlCuotasPagadas.setBounds(270, 50, 160, 20);
        jscrollPane2.setBounds(20, 75, 645, 130);
        jlCuotasEspera.setBounds(260, 200, 180, 20);
        jscrollPane.setBounds(20, 225, 645, 130);
        jbRegresar.setBounds(355, 370, 100, 20);
        jbPagarCuota.setBounds(225, 370, 120, 20);
        
        panel.add(jlCliente);
        panel.add(jlCuotasEspera);
        panel.add(jlCuotasPagadas);
        panel.add(jlInversion);
        
        panel.add(jbRegresar);
        panel.add(jbPagarCuota);
        
        panel.add(jscrollPane2);
        panel.add(jscrollPane);
        add(panel,BorderLayout.CENTER);
        
    }

    private void setupEvents() {
        jbPagarCuota.setBackground(new Color(36, 83, 181));
        jbRegresar.setBackground(new Color(36, 83, 181));
        tableEspera.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        tablePagadas.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        jbPagarCuota.setEnabled(false);
        
        jbRegresar.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        jbPagarCuota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(rootPane, "Desea Pagar la cuota N."+Numerocuota+" ?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                    Cuota cuota = VerificarCuotas();
                    if (cuota == null){
                        JOptionPane.showMessageDialog(rootPane, "Debe pagar las cuotas anteriores", "Atención!", JOptionPane.WARNING_MESSAGE);
                    }else{
                        Pagar_Cuotas pago = new Pagar_Cuotas(mainVerCuotas,cuota);
                        loadConcluidos();
                    }
                }
            }
        });
        
        tableEspera.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (tableEspera.getSelectedRows().length>1){
                    tableEspera.clearSelection();
                    jbPagarCuota.setEnabled(false);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableEspera.getSelectedRow();
                try {
                    //CCclienteSelected=""+tableModel.getValueAt(row, 1);
                    Numerocuota =""+tablemodel2.getValueAt(row, 0);
                    //System.out.println(" numero cuota: "+Numerocuota);
                    if(cargo){
                        jbPagarCuota.setEnabled(true);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    jbPagarCuota.setEnabled(false);
                }
            }
        });
        tableEspera.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (tableEspera.getSelectedRows().length>1){
                    tableEspera.clearSelection();
                    jbPagarCuota.setEnabled(false);
                }
            }
        });
        
    }

    private void loadConcluidos() {
        int rowcont=tableEspera.getRowCount();
        for (int i = 0; i < rowcont; i++) {
                tablemodel2.removeRow(0);
        }
        int rowcont2=tablePagadas.getRowCount();
        for (int i = 0; i < rowcont2; i++) {
                tableModel1.removeRow(0);
        }
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql
                    = "Select * from cuotainversion where codinversion like'"+inversion+"'   order by numerocuota";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {                
                String [] values = new String[7];
                String numerocuota = result.getString("numerocuota");
                Date fechpago = result.getDate("fechpago");
                String monto = result.getString("montocuota");
                String estado = result.getString("estadocuota");
                String numerocuenta = result.getString("cuentabanco");
                String tipo = result.getString("tipopago");
                String codcomprobante = result.getString("codcomprobante");
                Date fechefectiva = result.getDate("fechefectiva");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                if("Pagada".equals(estado)){
                    values[0]= numerocuota;
                    values[1] = ""+dateFormat.format(fechpago);
                    values[2] = monto;
                    values[3] = numerocuenta;
                    values[4] = tipo;
                    values[5] = codcomprobante;
                    values[6] = ""+dateFormat.format(fechefectiva);
                    tableModel1.addRow(values);
                }else{
                    values[0]= numerocuota;
                    values[1] = ""+dateFormat.format(fechpago);
                    values[2] = monto;
                    values[3] = numerocuenta;
                    values[4] = estado;
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
    
    private Cuota VerificarCuotas(){
        Cuota cuota=null;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql
                    = "Select * from cuotainversion where codinversion like'"+inversion+"' and numerocuota<='"+Numerocuota+"' order by numerocuota desc;";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {                
                String [] values = new String[7];
                int numerocuota = Integer.parseInt(result.getString("numerocuota"));
                String estado = result.getString("estadocuota");
                System.out.println(numerocuota);
                if (numerocuota == Integer.parseInt(Numerocuota) ){
                    String fechpago = result.getString("fechpago");
                    String monto = result.getString("montocuota");
                    String numerocuenta = result.getString("cuentabanco");
                    String tipo = result.getString("tipopago");
                    String codcomprobante = result.getString("codcomprobante");
                    String fechefectiva = result.getString("fechefectiva");
                    //String.format("%.2f",monto
                    cuota = new Cuota (""+Numerocuota,fechpago,estado,inversion,numerocuenta,Double.parseDouble(monto));
                }
                if (numerocuota<Integer.parseInt(Numerocuota)) {
                    if("Espera".equals(estado)){
                        cuota = null;
                        System.out.println("numerocuota: "+numerocuota+" < "+Integer.parseInt(Numerocuota));
                        System.out.println("estado: "+estado);
                    }
                }
            }
            result.close();
            st.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
        return cuota;
    }
}