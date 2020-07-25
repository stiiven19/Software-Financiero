/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import softwarefinancieroap.Cliente;
import java.util.Date;

public class Ver_Cliente extends JDialog{
    private JLabel          jlVisualizarCliente, jlNombres, jlApellidos, jlNDocumento,jlDireccion, jlTelefono,  jlImageCliente, jlCuentasusadas; 
    private JLabel          jlInverProceso, jlInverConcluidas, jlTotalInversiones, jlInversiones;
    private JLabel          jlTotalPrestamoSolicitados, jlPreRechazados, jlPreProceso, jlPreConcluidos, jlPreAprobados, jlPreEspera, jlPrestamos;
    private JButton         jbRegresar;
    private JPanel          panel;
    private JTable          table;
    private DefaultTableModel   tableModel;
    
    public Ver_Cliente(MenuPrincipal mainframe, Cliente cliente) {
        super(mainframe, true);
        setTitle("Cliente");
        setSize(540, 490);        
        setupWidgets();
        setupEvents();
        Actualizar_datos(cliente);
        setVisible(true);
    }

    private void setupWidgets() {
        
        jlVisualizarCliente = new JLabel("Visualizar Cliente");jlVisualizarCliente.setFont(new Font("Comic Sans MS",1,22));
        jlDireccion = new JLabel();
        jlImageCliente = new JLabel();
        jlNDocumento = new JLabel();
        jlNombres = new JLabel();
        jlTelefono = new JLabel();
        jlApellidos = new JLabel();
        jlCuentasusadas = new JLabel("Cuentas Bancarias Usadas");
        
        jlInverProceso = new JLabel     ("En Proceso: 0");
        jlInverConcluidas = new JLabel  ("Concluidas: 0");
        jlTotalInversiones = new JLabel ();
        jlInversiones = new JLabel("Inversiones");jlInversiones.setFont(new Font("Comic Sans MS",1,14));
        
        jlPrestamos = new JLabel("Prestamos");jlPrestamos.setFont(new Font("Comic Sans MS",1,14));
        jlPreAprobados = new JLabel();jlPreAprobados.setFont(new Font(jlApellidos.getFont().getFontName(),1,12));
        jlPreProceso = new JLabel();
        jlPreConcluidos = new JLabel();
        jlPreEspera = new JLabel();
        jlPreRechazados = new JLabel();
        jlTotalPrestamoSolicitados = new JLabel();
        
        jbRegresar =  new JButton("Regresar");
        
        JScrollPane jscrollPane = new JScrollPane();
        table = new JTable();
        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Numero", "Banco", "Tipo"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==3;
            }
        };
        table.setModel(tableModel);
                
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);        
        jscrollPane.setViewportView(table);
        
        panel =  new JPanel(null);panel.setBackground(new Color(247,247,247));
        
        jlVisualizarCliente.setBounds(30, 10, 200, 40);
        jlImageCliente.setBounds(40, 45, 160, 160);jlImageCliente.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/Clientes.png")));
        jlNombres.setBounds(20, 200, 250, 20);
        jlApellidos.setBounds(20, 240, 250, 20);
        jlNDocumento.setBounds(20, 280, 210, 20);
        jlDireccion.setBounds(20, 320, 260, 20);
        jlTelefono.setBounds(20, 360, 210, 20);
        
        jlPrestamos.setBounds(330, 20, 90, 20);
        jlPreAprobados.setBounds(270, 50, 110, 20);
        jlPreProceso.setBounds(270, 70, 100, 20);jlPreEspera.setBounds(370, 70, 90, 20);
        jlPreConcluidos.setBounds(270, 93, 100, 20);jlPreRechazados.setBounds(370, 93, 90, 20);
        jlTotalPrestamoSolicitados.setBounds(270, 116, 190, 20);
        
        jlInversiones.setBounds(330, 156, 90, 20);
        jlInverProceso.setBounds(270, 176, 100, 20);
        jlInverConcluidas.setBounds(270, 199, 100, 20);
        jlTotalInversiones.setBounds(270, 222, 130, 20);
        
        jlCuentasusadas.setBounds(300, 262, 190, 20);
        jscrollPane.setBounds(270, 284, 240, 80);
        
        jbRegresar.setBounds(225, 400, 90, 20);
        
        
        panel.add(jlDireccion);
        panel.add(jlImageCliente);
        panel.add(jlNDocumento);
        panel.add(jlNombres);
        panel.add(jlApellidos);
        panel.add(jlVisualizarCliente);
        panel.add(jlTelefono);
        panel.add(jscrollPane);
        
        panel.add(jlPreAprobados);
        panel.add(jlPreConcluidos);
        panel.add(jlPreEspera);
        panel.add(jlPreProceso);
        panel.add(jlPreRechazados);
        panel.add(jlPrestamos);
        panel.add(jlTotalPrestamoSolicitados);
        
        panel.add(jlInverConcluidas);
        panel.add(jlInverProceso);
        panel.add(jlInversiones);
        panel.add(jlTotalInversiones);
        panel.add(jlCuentasusadas);
        
        panel.add(jbRegresar);
        
        add(panel,BorderLayout.CENTER);
    }

    private void setupEvents() {
        jbRegresar.setBackground(new Color(36, 83, 181));
        setLocationRelativeTo(null);
        this.setResizable(false);
        table.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        table.getTableHeader().setResizingAllowed(false);
        
        jbRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void Actualizar_datos(Cliente cliente){
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql;
            sql = "select  * from inversiones where ceduinversionista like '" + cliente.getCeducli() + "'";
            int contadorInversiones = 0;
            ResultSet result5 = conexion.createStatement().executeQuery(sql);
            while(result5.next()){
                contadorInversiones++;
            }
            result5.close();
            sql="select * from cuotainversion join inversiones on cuotainversion.codinversion=inversiones.codinversion where ceduinversionista like '" + cliente.getCeducli() + "' and estadocuota in ('No Pagada','Espera')";
            ResultSet result2 = conexion.createStatement().executeQuery(sql);
            boolean bandProceso = false;
            while (result2.next()) {
                 bandProceso = true;
            }
            result2.close();
            sql = "Select  numerocuenta, banco, tipocuenta, count(*) from cuentasbancarias join cuotainversion on numerocuenta = cuentabanco  join inversiones on cuotainversion.codinversion = inversiones.codinversion where ceduinversionista like '" + cliente.getCeducli() + "' group by 1,2,3 order by 1;";
            ResultSet result3 = conexion.createStatement().executeQuery(sql);
            while (result3.next()) {
                String [] cuentas = new String[3];
                String numero = result3.getString("numerocuenta");
                String banco = result3.getString("banco");
                String tipocuenta = result3.getString("tipocuenta");
                cuentas [0] = numero;
                cuentas [1] = banco;
                cuentas [2] = tipocuenta;
                tableModel.addRow(cuentas);
            }
            result3.close();
            sql = "Select  * from prestamos where ceduprestatario like '" + cliente.getCeducli() + "'";
            ResultSet result4 = conexion.createStatement().executeQuery(sql);
            int contadorRechazado = 0;
            int contadorEspera = 0;
            int contadorConcluido = 0;
            int contadorProceso = 0;
            while(result4.next()){
                Date fechat = result4.getDate("fechterminopre");
                Date fecha = new Date();
                Date fecha2 = fechat;
                int fech;
                if(fecha2!=null) {
                    fech=fecha.compareTo(fecha2);
                }else{
                    fech=0;
                }
                String estado = result4.getString("estadoprestamo");
                if("rechazado".equals(estado)){
                    contadorRechazado++;
                    continue;
                }
                if("pendiente".equals(estado)){
                    contadorEspera++;
                    continue;
                }
                if("aprobado".equals(estado)){
                    String fechtermino = result4.getString("fechterminopre");
                    if(fech<=0){
                        contadorProceso++;
                    }else{
                        contadorConcluido++;
                    }
                }
            }
            jlNombres.setText   ("Nombres  :  " + cliente.getNomcli());
            jlApellidos.setText ("Apellidos  :  " + cliente.getApellicli());
            jlDireccion.setText ("Direccion  :  " + cliente.getDircli());
            jlTelefono.setText  ("Telefono  :  " + cliente.getTelecli());
            jlNDocumento.setText("Numero Documento : " + cliente.getCeducli());
            if(bandProceso){
                jlInverProceso.setText(     "En Proceso: 1");
                jlInverConcluidas.setText(  "Concluidas : "+(contadorInversiones-1));
            }else{
                jlInverConcluidas.setText("Concluidas : " + contadorInversiones);
            }
            jlTotalInversiones.setText("Total Inversiones: " + contadorInversiones);
            jlPreAprobados.setText  (" Aprobados : "+(contadorConcluido+contadorProceso));
            jlPreConcluidos.setText ("-Concluidos : "+contadorConcluido);
            jlPreProceso.setText    ("-En Proceso: "+contadorProceso);
            jlPreEspera.setText     ("-En Espera   : "+contadorEspera);
            jlPreRechazados.setText ("-Rechazados: "+contadorRechazado);
            jlTotalPrestamoSolicitados.setText("-Total Prestamos Solicitados: " + (contadorConcluido+contadorProceso+contadorRechazado+contadorEspera));
            result4.close();
            st.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
        
    }
}
