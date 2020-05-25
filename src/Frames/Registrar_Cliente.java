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
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import softwarefinancieroap.Cliente;
/**
 *
 * @author lenovo
 */
public class Registrar_Cliente extends JDialog{
    
    private JLabel      jlRegistroCliente, jlNombres, jlApellidos, jlNDocumento,jlDireccion, jlTelefono, jlClientes, jlImageCliente;
    private JTextField  txNombres, txApellidos, txNDocumento, txDireccion, txTelefono;
    private JButton     jbRegistrar,jbCancelar;
    private JPanel      panel;
    
    public Registrar_Cliente(MenuPrincipal mainframe){
        super(mainframe, true);
        setTitle("Registrar Cliente");
	setSize(470, 380);
        setupWidgets();
        setupEvents();
        setVisible(true);
        
    }

    private void setupWidgets() {
        jlRegistroCliente = new JLabel("RegistroCliente");
        jlClientes = new JLabel("Clientes");
        jlDireccion = new JLabel("Direccion:");
        jlImageCliente = new JLabel();
        jlNDocumento = new JLabel("N.Documento:");
        jlNombres = new JLabel("Nombres:");
        jlTelefono = new JLabel("Telefono:");
        jlApellidos = new JLabel("Apellidos:");
        
        txNombres = new JTextField();
        txApellidos =  new JTextField();
        txNDocumento =  new JTextField();
        txTelefono =  new JTextField();
        txDireccion =  new JTextField();
        
        jbRegistrar =  new JButton("Registrar");
        jbCancelar =  new JButton("Cancelar");
        
        panel =  new JPanel(null);panel.setBackground(new Color(247,247,247));
        
        jlRegistroCliente.setBounds(20, 30, 120, 30);
        jlNombres.setBounds(20, 90, 90, 20);txNombres.setBounds(120, 90, 120, 20);
        jlApellidos.setBounds(20, 130, 90, 20);txApellidos.setBounds(120, 130, 120, 20);
        jlNDocumento.setBounds(20, 170, 90, 20);txNDocumento.setBounds(120, 170, 120, 20);
        jlDireccion.setBounds(20, 210, 90, 20);txDireccion.setBounds(120, 210, 120, 20);
        jlTelefono.setBounds(20, 250, 90, 20);txTelefono.setBounds(120, 250, 120, 20);
        jlClientes.setBounds(310, 210, 90, 20);
        jlImageCliente.setBounds(270, 50, 160, 160);jlImageCliente.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/Clientes.png")));
        
        jbRegistrar.setBounds(140, 300, 90, 20);
        jbCancelar.setBounds(240, 300, 90, 20);
        
        
        panel.add(jlClientes);
        panel.add(jlDireccion);
        panel.add(jlImageCliente);
        panel.add(jlNDocumento);
        panel.add(jlNombres);
        panel.add(jlApellidos);
        panel.add(jlRegistroCliente);
        panel.add(jlTelefono);
        
        panel.add(jbRegistrar);
        panel.add(jbCancelar);
        
        panel.add(txApellidos);
        panel.add(txDireccion);
        panel.add(txNDocumento);
        panel.add(txNombres);
        panel.add(txTelefono);
        
        add(panel,BorderLayout.CENTER);
    }

    private void setupEvents() {
        this.setResizable(false);
        
        txNDocumento.addKeyListener(new KeyListener() {//evento de solo numeros
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char caracter=e.getKeyChar();
                String textoNDocumento = txNDocumento.getText();
                if (Character.isLetter(caracter)) {
                    if (textoNDocumento.length()>0) {
                        txNDocumento.setText("");
                        txNDocumento.setText(textoNDocumento.substring(0, textoNDocumento.length()-1));
                    }
                }
            }
        });
        
        txTelefono.addKeyListener(new KeyListener() {//evento de solo numeros
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char caracter=e.getKeyChar();
                String textoTelefono = txTelefono.getText();
                if (Character.isLetter(caracter)) {
                    if (textoTelefono.length()>0) {
                        txTelefono.setText("");
                        txTelefono.setText(textoTelefono.substring(0, textoTelefono.length()-1));
                    }
                }
            }
        });
        
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        jbRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente(txNDocumento.getText(),
                        txNombres.getText(),
                        txApellidos.getText(),
                        txDireccion.getText(),
                        txTelefono.getText());
                if (cliente.registrar_cliente()){
                    JOptionPane.showMessageDialog(rootPane, "Cliente Registrado Exitosamente!", "info", JOptionPane.INFORMATION_MESSAGE);
                }
                dispose();
            }
        });
    }
}
