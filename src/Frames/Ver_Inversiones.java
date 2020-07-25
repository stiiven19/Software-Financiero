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
import java.text.SimpleDateFormat;
/**
 *
 * @author lenovo
 */
public class Ver_Inversiones extends JDialog{

    private final String            CCcliente;
    private String                  Inversion;
    private JLabel                  jlProceso, jlInversionesConcluidas;
    private JButton                 jbVerInversion, jbVerCronogramaCuotas;
    private JPanel                  panel;
    private JTable                  tableProceso,tableConcluidos;
    private DefaultTableModel       tableModelProceso,tableModelConcluidos;
    private final Ver_Inversiones   mainVerInversiones=this;
    private boolean cargo;
    
    
    public Ver_Inversiones(MenuPrincipal mainframe,String CCcliente1,boolean cargo){
        super(mainframe, true);
        this.cargo = cargo;
        this.CCcliente = CCcliente1 ;
        setTitle("Inversiones");
	setSize(600, 420);
        setupWidgets();
        setupEvents();
        loadConcluidos();
        setVisible(true);
    }

    private void setupWidgets() {
        jlInversionesConcluidas = new JLabel("Inversiones Concluidas: ");
        jlProceso = new JLabel("En Proceso:");
        
        jbVerCronogramaCuotas = new JButton("Ver Cronograma de Cuotas");
        jbVerInversion = new JButton("Ver Inversion");
        
        JScrollPane jscrollpaneConcluido = new JScrollPane();
        JScrollPane jscrollpaneProceso = new JScrollPane();
        
        tableConcluidos = new JTable();
        tableProceso = new JTable();
        
        panel = new JPanel(null);panel.setBackground(new Color(247,247,247));
                
        tableModelProceso = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Codigo", "Fecha Inicio", "Monto","Fecha Finalización"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==4;
            }
        };
        tableModelConcluidos= new DefaultTableModel(
                new Object[][] {},
                new String[] {"Codigo","Fecha Inicio", "Monto", "Fecha Finalización"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==4;
            }
        };
        
        tableProceso.setModel(tableModelProceso);
        tableProceso.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableProceso.getColumnModel().getColumn(1).setPreferredWidth(60);
        tableProceso.getColumnModel().getColumn(2).setPreferredWidth(80);   
        tableProceso.getColumnModel().getColumn(3).setPreferredWidth(40); 
        jscrollpaneProceso.setViewportView(tableProceso);
        
        tableConcluidos.setModel(tableModelConcluidos);
        tableConcluidos.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableConcluidos.getColumnModel().getColumn(1).setPreferredWidth(60);
        tableConcluidos.getColumnModel().getColumn(2).setPreferredWidth(80);        
        tableConcluidos.getColumnModel().getColumn(3).setPreferredWidth(40); 
        jscrollpaneConcluido.setViewportView(tableConcluidos);
        
        jlProceso.setBounds(20, 20, 90, 20);
        jscrollpaneProceso.setBounds(20, 50, 545, 39);
        jlInversionesConcluidas.setBounds(20, 150, 160, 20);
        jscrollpaneConcluido.setBounds(20, 190, 545, 130);
        jbVerInversion.setBounds(130, 340, 160, 20);
        jbVerCronogramaCuotas.setBounds(310, 340, 190, 20);
        
        panel.add(jlProceso);
        panel.add(jlInversionesConcluidas);
        
        panel.add(jbVerCronogramaCuotas);
        panel.add(jbVerInversion);
        
        panel.add(jscrollpaneProceso);
        panel.add(jscrollpaneConcluido);
        add(panel,BorderLayout.CENTER);
        
    }

    private void setupEvents() {
        jbVerCronogramaCuotas.setBackground(new Color(36, 83, 181));
        jbVerInversion.setBackground(new Color(36, 83, 181));
        
        tableConcluidos.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        tableConcluidos.getTableHeader().setResizingAllowed(false);//evita redimencionar las columnas
        
        tableProceso.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        tableProceso.getTableHeader().setResizingAllowed(false);//evita redimencionar las columnas
        InversionSelected(false, false);

        
        jbVerInversion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        jbVerCronogramaCuotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ver_CuotasInversion ver_cuotas = new Ver_CuotasInversion(mainVerInversiones,CCcliente,Inversion,cargo);
            }
        });
        
        tableConcluidos.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                tableProceso.clearSelection();
                int row = tableConcluidos.getSelectedRow();
                try {
                    Inversion=""+tableModelConcluidos.getValueAt(row, 0);
                    InversionSelected(true, false);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    InversionSelected(false, true);
                }
            }
        @Override
        public void mouseReleased(MouseEvent e) {
            tableProceso.clearSelection();
            if (tableConcluidos.getSelectedRows().length>1){
                tableConcluidos.clearSelection();
                InversionSelected(false, true);
            }
        }
    });
        
        tableProceso.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                tableConcluidos.clearSelection();
                int row = tableProceso.getSelectedRow();
                try {
                    Inversion=""+tableModelProceso.getValueAt(row, 0);
                    InversionSelected(true, false);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    InversionSelected(false, true);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                tableConcluidos.clearSelection();
                if (tableProceso.getSelectedRows().length>1){
                    tableProceso.clearSelection();
                    InversionSelected(false, true);
                }
            }
        });
    }
    
    private void loadConcluidos(){
        int rowcont=tableConcluidos.getRowCount();
        for (int i = 0; i < rowcont; i++) {
                tableModelConcluidos.removeRow(i);
        }
        int rowcont2=tableProceso.getRowCount();
        for (int i = 0; i < rowcont2; i++) {
                tableModelProceso.removeRow(i);
        }
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql
                    = "Select inversiones.codinversion, fechinicioinv , montoinv,fechterminoinv from inversiones where ceduinversionista like'"+CCcliente+"' order by 2";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            String [] values;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fecha = new java.util.Date();
            while (result.next()) {                
                values = new String[4];
                String Cod = result.getString("codinversion");
                Date fechat = result.getDate("fechterminoinv");
                String monto = result.getString("montoinv");
                Date fechai = result.getDate("fechinicioinv");
                values[0] = Cod;
                values[3] = dateFormat.format(fechat);
                values[2] = monto;
                values[1] = dateFormat.format(fechai);
                if(fecha.compareTo(fechat) <= 0){
                    tableModelProceso.addRow(values);
                }else{
                    tableModelConcluidos.addRow(values);
                }
            }
            result.close();
            st.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
    }
    
    private void InversionSelected(boolean select,boolean sonido){//1bool=select 2bool=onido
        jbVerCronogramaCuotas.setEnabled(select);
        jbVerInversion.setEnabled(select);
        if (sonido)Toolkit.getDefaultToolkit().beep();
    }
}
