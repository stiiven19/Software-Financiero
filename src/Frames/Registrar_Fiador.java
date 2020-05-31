/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lenovo
 */
public class Registrar_Fiador extends JDialog {
    
    private JLabel      jlCCfiador,jlNombreFiador,jlApellidoFiador,jlTelefonoFiador, jlCC, jlRegistroFiador,jlCliente,jlFiador;
    private JButton     jbRegistrar,jbCancelar,jbBuscar;
    private JTextField  txBuscar;
    private JPanel      panel;
    String              fiador="";

    public Registrar_Fiador(Registrar_Prestamo mainframe) {
        super(mainframe, true);
        setTitle("Registrar Prestamo");
	setSize(400, 250);
        setupWidgets();
        setupEvents();
        setVisible(true);
    }

    private void setupWidgets() {
        jlCCfiador = new JLabel("Cedula");
        jlNombreFiador = new JLabel("Nombres");
        jlApellidoFiador = new JLabel("Apellidos");
        jlTelefonoFiador = new JLabel("Telefono");
        jlCC = new JLabel("Cedula:");
        jlRegistroFiador = new JLabel("Registro Fiador");
        //jlCliente = new JLabel("Cliente");
        jlFiador = new JLabel();
        
        jbRegistrar = new JButton("Registrar");
        jbCancelar = new JButton ("Cancelar");
        jbBuscar = new JButton();
        
        txBuscar = new JTextField();
        
        panel = new JPanel(null);panel.setBackground(new Color(247,247,247));
        //-----------------------------
        jlRegistroFiador.setBounds(20, 30, 120, 30);
        jlCC.setBounds(20, 80, 90, 20);txBuscar.setBounds(130, 80, 110, 20);jbBuscar.setBounds(250, 80, 20, 20);
        jlCCfiador.setBounds(20, 105, 100, 20);jlNombreFiador.setBounds(100, 105, 200, 20);jlApellidoFiador.setBounds(200, 105, 200, 20);jlTelefonoFiador.setBounds(280, 105, 200, 20);
        jlFiador.setBounds(20, 130, 400, 20);
        
        jbRegistrar.setBounds(105, 165, 90, 20); jbCancelar.setBounds(205, 165, 90, 20);
        
        //-----------------------------
        panel.add(jlCC);
        panel.add(jlCCfiador);
        panel.add(jlRegistroFiador);
        //panel.add(jlCliente);
        panel.add(jlNombreFiador);
        panel.add(jlApellidoFiador);
        panel.add(jlTelefonoFiador);
        panel.add(jlFiador);
        
        panel.add(jbRegistrar);
        panel.add(jbCancelar);
        panel.add(jbBuscar);
        
        panel.add(txBuscar);
        add(panel);
    }//posicionamiento de objetos

    private void setupEvents() {
        this.setResizable(false);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {                
                
            }

            @Override
            public void windowClosed(WindowEvent e) {
                fiador="";
                dispose();
            }

            @Override
            public void windowIconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                
            }
        });
        jbCancelar.addActionListener((ActionEvent e) -> {
            fiador="";
            dispose();
        });
        
        jbRegistrar.addActionListener((ActionEvent e) -> {
            if ("".equals(fiador)) {
                JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado fiador", "Atención!", JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(rootPane, "fiador: "+fiador+" registrado", "Información!", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        
        jbBuscar.addActionListener((ActionEvent e) -> {
            String sql = "select * from clientes where ceducli like'"+txBuscar.getText()+"'";
            //System.out.println(sql);
            String[] str = new String[4];
            str[0]="ceducli";
            str[1]="nomcli";
            str[2]="apellicli";
            str[3]="telecli";
            String DatosCliente=new ConexionDataBase().EjecutarSql(sql,str);
            if (!"".equals(DatosCliente)) {
                jlFiador.setText(DatosCliente);
                fiador=txBuscar.getText();
            }else{
                fiador="";
                jlFiador.setText("Cliente con CC: "+txBuscar.getText()+" No Existe");
            }
        });
        
        txBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if( !(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txBuscar.getText().length()+1>10){
                    e.consume();
                }
            }
        });
    }//eventos de objetos
    
    public String getFiador(){        
        return fiador;
    }//metodo getfiador
}
    
    
    

