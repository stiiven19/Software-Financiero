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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import javax.swing.ImageIcon;

public class MenuPrincipal extends javax.swing.JFrame {

    private     JLabel              jlMostrarPagos,jlCC,jlimagePrestamos,jlimageInversiones;
    private     JButton             jbVisualizarCliente, jbRegistrarCliente, jbModificarCliente, jbRegistrarInversion, jbRegistrarPrestamo, jbVerPrestamos, jbVerInversiones;
    private     JTextField          txaBuscar;
    private     JPanel              panelCenter;
    private     JTable              table;
    private     DefaultTableModel   tableModel;
    private final     MenuPrincipal       mainframe= this;
    private     String              CCclienteSelected;
    
    public MenuPrincipal() {
        
        setTitle("                  MAVENSAKAR");
	setSize(700, 400);
        setupWidgets();
        setupEvents();
        setVisible(true);
        load();
    }

    private void setupWidgets() {
        //inicializamos objetos
        panelCenter = new JPanel();
        
        jlMostrarPagos = new JLabel("Mostrar Pagos");
        jlCC = new JLabel("CC:");
        jlimagePrestamos = new JLabel();jlimagePrestamos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/verPrestamos.jpg")));
        jlimageInversiones =  new JLabel();jlimageInversiones.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/verInversiones.jpg")));
        
        jbVisualizarCliente = new JButton("Visualizar Cliente");
        jbRegistrarCliente = new JButton("Registrar Cliente");
        jbModificarCliente = new JButton("Modificar Cliente");
        jbRegistrarPrestamo = new JButton("Registrar Prestamo");
        jbRegistrarInversion = new JButton("Registrar Inversion");
        jbVerPrestamos =  new JButton("Ver Prestamos");
        jbVerInversiones =  new JButton("Ver Inversiones");
        
        JScrollPane jscrollPane = new JScrollPane();
        
        txaBuscar = new JTextField("BUSCAR");
        
        //ponemos ubicacion de objetos
        jlMostrarPagos.setBounds(20, 23, 100, 15);
        jlimagePrestamos.setBounds(500, 10, 160, 130);
        jlimageInversiones.setBounds(500, 180, 160, 130);
        jlCC.setBounds(285, 23, 20, 16);
        
        txaBuscar.setBounds(310, 22, 118, 19);
        
        jbVisualizarCliente.setBounds(20, 263,132, 25);
        jbRegistrarCliente.setBounds(172, 263, 130, 25);
        jbModificarCliente.setBounds(322, 263, 130, 25);
        
        jbRegistrarInversion.setBounds(92, 318, 143, 25);
        jbRegistrarPrestamo.setBounds(255, 318, 150, 25);
        
        jbVerInversiones.setBounds(510, 320, 130, 20);
        jbVerPrestamos.setBounds(510, 150, 130, 20);
        
        jscrollPane.setBounds(20, 63, 433,183 );
        table = new JTable();
        
        panelCenter.setLayout(null);panelCenter.setBackground(new Color(247,247,247));
        //panelRight.setLayout(null);//panelRight.setBackground(new Color(247,247,247));
        
        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"N.","Cedula", "Nombre", "Apellido"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==4;
            }
        };
        
        table.setModel(tableModel);
                
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);        
        jscrollPane.setViewportView(table);
        
        //agregamos al panelcentro
        panelCenter.add(jlMostrarPagos);
        panelCenter.add(jlCC);
        
        panelCenter.add(jbVisualizarCliente);
        panelCenter.add(jbRegistrarCliente);
        panelCenter.add(jbModificarCliente);
        panelCenter.add(jbRegistrarInversion);
        panelCenter.add(jbRegistrarPrestamo);
        panelCenter.add(txaBuscar);
        panelCenter.add(jscrollPane);
        
        //derecha
        panelCenter.add(jlimageInversiones);
        panelCenter.add(jlimagePrestamos);
        
        panelCenter.add(jbVerInversiones);
        panelCenter.add(jbVerPrestamos);
        add(panelCenter,BorderLayout.CENTER);
        //add(panelRight,BorderLayout.EAST);
    }    
    //Eventos
    private void setupEvents(){
        setLocationRelativeTo(null);
        this.setResizable(false);
        table.getTableHeader().setReorderingAllowed(false);//evita reordenar las columnas
        table.getTableHeader().setResizingAllowed(false);//evita redimencionar las columnas
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //inabilitamos botones
        jbModificarCliente.setEnabled(false);
        jbRegistrarInversion.setEnabled(false);
        jbRegistrarPrestamo.setEnabled(false);
        jbVisualizarCliente.setEnabled(false);
        jbVerPrestamos.setEnabled(false);
        jbVerInversiones.setEnabled(false);
        
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                try {
                    CCclienteSelected=""+tableModel.getValueAt(row, 1);
                    jbModificarCliente.setEnabled(true);
                    jbRegistrarInversion.setEnabled(true);
                    jbRegistrarPrestamo.setEnabled(true);
                    jbVisualizarCliente.setEnabled(true);
                    jbVerPrestamos.setEnabled(true);
                    jbVerInversiones.setEnabled(true);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    jbModificarCliente.setEnabled(false);
                    jbRegistrarInversion.setEnabled(false);
                    jbRegistrarPrestamo.setEnabled(false);
                    jbVisualizarCliente.setEnabled(false);
                    jbVerPrestamos.setEnabled(false);
                    jbVerInversiones.setEnabled(false);
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (table.getSelectedRows().length>1){
                    table.clearSelection();
                    jbModificarCliente.setEnabled(false);
                    jbRegistrarInversion.setEnabled(false);
                    jbRegistrarPrestamo.setEnabled(false);
                    jbVisualizarCliente.setEnabled(false);
                    jbVerPrestamos.setEnabled(false);
                    jbVerInversiones.setEnabled(false);
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        //evento de editar jtable
        jbRegistrarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar_Prestamo registrar_Prestamo = new Registrar_Prestamo(mainframe,CCclienteSelected);
            }
        });
        
        jbRegistrarInversion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar_Inversion registrar_inversion = new Registrar_Inversion(mainframe,CCclienteSelected);
            }
        });
        
        jbRegistrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registrar_Cliente(mainframe);
                load();
            }
        });
        
        jbVerInversiones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        jbVerPrestamos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ver_Prestamos ver_prestamos = new Ver_Prestamos(mainframe,CCclienteSelected);
            }
        });
        
        txaBuscar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txaBuscar.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txaBuscar.getText().length()==0){
                    txaBuscar.setText("BUSCAR");
                }
            }
        });
        
        txaBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if( !(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txaBuscar.getText().length()+1>10){
                    e.consume();
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                load();
                if(txaBuscar.getText().length()>0){
                    Vector<String[]> vector = new Vector();
                    String tx=txaBuscar.getText();
                    for (int i = 0; i < table.getRowCount(); i++) {
                        String values[]=new String[4];
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            values[j]=""+tableModel.getValueAt(i, j);
                        }
                        vector.add(values);
                    }
                    int rowcont=table.getRowCount();
                    for (int i = 0; i < rowcont; i++) {
                        tableModel.removeRow(0);
                    }
                    for (int i = 0; i < vector.size(); i++) {
                        String string =(String) vector.get(i)[1];

                        if(tx.length()<=string.length()){
                            if(tx.equals(string.substring(0, tx.length()))){
                                tableModel.addRow(vector.get(i));
                            }
                        }
                    }
                }
            }
        });
    }
    
    private void load(){
        int rowcont=table.getRowCount();
        for (int i = 0; i < rowcont; i++) {
                tableModel.removeRow(0);
        }
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql
                    = "Select ceducli,nomcli,apellicli from clientes order by 2";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            int count = 0;
            while (result.next()) {                
                String [] values = new String[4];
                String CC = result.getString("ceducli");
                String nombre = result.getString("nomcli");
                String apellido = result.getString("apellicli");
                count++;
                values[0]= ""+count;
                values[1] = CC;
                values[2] = nombre;
                values[3] = apellido;
                
                //String direccion = result.getString("dircli");
                //String telefono = result.getString("telecli");
                
                //System.out.println("cedula " + CC + " NOMBRE: " + nombre + " APELLIDO: " + apellido + " DIRECCION: " + direccion + " telefono: " + telefono);
                //System.out.println("cedula " + CC + " NOMBRE: " + nombre + " APELLIDO: " + apellido );
                tableModel.addRow(values);
            }
            
            result.close();
            st.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
    TableModel dataModel = new AbstractTableModel() {
          public int getColumnCount() { return 10; }
          public int getRowCount() { return 10;}
          public Object getValueAt(int row, int col) { return new Integer(row*col); }
        };
        JTable table = new JTable(dataModel);
        JScrollPane scrollpane = new JScrollPane(table);
    */
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
