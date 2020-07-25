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
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import softwarefinancieroap.Cliente;
import softwarefinancieroap.Garantia;
import softwarefinancieroap.Prestamo;
/**
 *
 * @author lenovo
 */
public class Ver_Prestamos extends JDialog{

    private final String CCcliente;
    private String              codprestamo;
    private JLabel              jlEspera, jlPrestamosConcluidos;
    private JButton             jbVerPrestamo, jbVerCronogramaCuotas;
    private JPanel              panel;
    private JTable              tableEspera,tableConcluidos;
    private DefaultTableModel   tableModel1,tablemodel2;
    private final Ver_Prestamos       mainVerPrestamos=this;
    private Prestamo            prestamo;
    private Garantia            garantia;
    private Cliente             fiador;
    private boolean             cargo;
    
    public Ver_Prestamos(MenuPrincipal mainframe,String CCcliente1,boolean cargo){
        super(mainframe, true);
        this.cargo = cargo;
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
        jlEspera = new JLabel("En Proceso:");
        
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
        jscrollPane2.setBounds(20, 50, 545, 39);
        jlPrestamosConcluidos.setBounds(20, 110, 160, 20);
        jscrollPane.setBounds(20, 150, 545, 170);
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
        jbVerCronogramaCuotas.setBackground(new Color(36, 83, 181));
        jbVerPrestamo.setBackground(new Color(36, 83, 181));
        
        tableConcluidos.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        tableConcluidos.getTableHeader().setResizingAllowed(false);//evita redimencionar las columnas
        
        tableEspera.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        tableEspera.getTableHeader().setResizingAllowed(false);//evita redimencionar las columnas
        jbVerCronogramaCuotas.setEnabled(false);
        jbVerPrestamo.setEnabled(false);

        
        jbVerPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizarPrestamo(codprestamo);
                Ver_Prestamo ver_prestamos = new Ver_Prestamo(mainVerPrestamos,CCcliente,prestamo,garantia,fiador,cargo);
            }
        });
        
        jbVerCronogramaCuotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ver_CuotasPrestamo ver_cuotas = new Ver_CuotasPrestamo(mainVerPrestamos,CCcliente,codprestamo);
            }
        });
        
        tableConcluidos.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                tableEspera.clearSelection();
                int row = tableConcluidos.getSelectedRow();
                try {
                    codprestamo=""+tablemodel2.getValueAt(row, 0);
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
                    codprestamo=""+tableModel1.getValueAt(row, 0);
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
    
    private void ActualizarPrestamo(String codigoprestamo){
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql
                    = "Select TO_CHAR(fechterminopre :: DATE, 'dd/mm/yyyy') as fechterminopre,montoprestamo,estadoprestamo,TO_CHAR(fechsolicitudpre :: DATE, 'dd/mm/yyyy') as fechsolicitudpre,TO_CHAR(fechiniciopre :: DATE, 'dd/mm/yyyy') as fechiniciopre,TO_CHAR(fechaprobacionpre :: DATE, 'dd/mm/yyyy') as fechaprobacionpre,interesprestamo,garantia,fiador,numerocuotas from prestamos  where codprestamo like'"+codigoprestamo+"';";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {             
                //System.out.println("sql: "+sql);
                String fechas = result.getString("fechsolicitudpre");
                String fechaa = result.getString("fechaprobacionpre");
                String fechai = result.getString("fechiniciopre");
                String fechat = result.getString("fechterminopre");
                Double monto = result.getDouble("montoprestamo");
                String estado = result.getString("estadoprestamo");
                String interes = result.getString("interesprestamo");
                String codgarantia = result.getString("garantia");
                String codfiador = result.getString("fiador");
                String numerocuotas = result.getString("numerocuotas");
                //System.out.println("Prestamo: "+CCcliente+", "+codprestamo +", "+fechas+", " +fechaa+", "+fechai+", "+fechat+", "+Double.parseDouble(interes)+", "+Double.valueOf(twoDForm.format(monto))+", "+estado+", "+codgarantia+", "+codfiador);
                prestamo = new Prestamo(CCcliente,codprestamo,fechas,fechaa,fechai,fechat,Double.parseDouble(interes),monto,estado,codgarantia,codfiador,Integer.parseInt(numerocuotas));
                if(!"null".equals(codgarantia)){
                    sql = "Select * from garantias where codgarantia like'"+codgarantia+"';";
                    ResultSet result2 = conexion.createStatement().executeQuery(sql);
                    while(result2.next()){
                        String valor = result2.getString("valor");
                        String tipogarantia = result2.getString("tipogarantia");
                        String ubicacion = result2.getString("ubicacion");
                        garantia = new Garantia(codgarantia,tipogarantia,Double.parseDouble(valor),ubicacion);
                        //System.out.println("Garantia: "+codgarantia+", "+tipogarantia+", "+Double.parseDouble(valor)+", "+ubicacion);
                    }
                    result2.close();
                }
                if (!"null".equals(codfiador)){
                    sql = "Select * from clientes where ceducli like'"+codfiador+"';";
                    ResultSet result3 = conexion.createStatement().executeQuery(sql);
                    while(result3.next()){
                        String nomcli = result3.getString("nomcli");
                        String apellicli = result3.getString("apellicli");
                        String dircli = result3.getString("dircli");
                        String telecli = result3.getString("telecli");
                        fiador = new Cliente(codfiador,nomcli,apellicli,dircli,telecli);
                        //System.out.println("Cliente: "+codfiador+", "+nomcli+", "+apellicli+", "+dircli+", "+telecli);
                    }
                    result3.close();
                }
            }
            result.close();
            st.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
        
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
                    = "Select codprestamo,fechterminopre,montoprestamo,estadoprestamo,TO_CHAR(fechsolicitudpre :: DATE, 'dd/mm/yyyy') as fechsolicitudpre from prestamos where ceduprestatario like'"+CCcliente+"'   order by 5";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {                
                String [] values = new String[4];
                String Cod = result.getString("codprestamo");
                Date fechat = result.getDate("fechterminopre");
                String monto = result.getString("montoprestamo");
                String estado = result.getString("estadoprestamo");
                String fechas = result.getString("fechsolicitudpre");
                
                Date fecha = new Date();
                Date fecha2 = fechat;
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                int fech;
                if(fecha2!=null) {
                    fech=fecha.compareTo(fecha2);
                }else{
                    fech=0;
                }
                if("pendiente".equals(estado) || fech<=0){
                    
                    values[0] = Cod;
                    values[1] = fechas;
                    values[2] = monto;
                    tableModel1.addRow(values);
                    if ("pendiente".equals(estado))jlEspera.setText("Por Aprobar");
                }else{
                    values[0] = Cod;
                    values[1] = ""+dateFormat.format(fechat);
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
