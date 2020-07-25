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
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import softwarefinancieroap.Cliente;

public class Modificar_Cliente extends JDialog{
    private JLabel          jlModificarCliente, jlNombres, jlApellidos, jlNDocumento,jlDireccion, jlTelefono,  jlImageCliente, jlCuentasusadas; 
    private JTextField      txNombres, txApellidos, txNDocumento, txDireccion, txTelefono;
    private JButton         jbCancelar, jbModificar;
    private JPanel          panel;
    Cliente cliente;
    
    public Modificar_Cliente(MenuPrincipal mainframe, Cliente cliente) {
        super(mainframe, true);
        this.cliente = cliente;
        setTitle("Cliente");
        setSize(340, 470);
        setupWidgets();
        setupEvents();
        setVisible(true);
    }

    private void setupWidgets() {
        
        jlModificarCliente = new JLabel("Modificar Cliente");jlModificarCliente.setFont(new Font("Comic Sans MS",1,22));
        jlDireccion = new JLabel("Direccion:");
        jlImageCliente = new JLabel();
        jlNDocumento = new JLabel("N.Documento:");
        jlNombres = new JLabel("Nombres:");
        jlTelefono = new JLabel("Telefono:");
        jlApellidos = new JLabel("Apellidos:");
        
        txNombres = new JTextField(cliente.getNomcli());
        txApellidos =  new JTextField(cliente.getApellicli());
        txNDocumento =  new JTextField(cliente.getCeducli());
        txTelefono =  new JTextField(cliente.getTelecli());
        txDireccion =  new JTextField(cliente.getDircli());
        
        jbModificar = new JButton("Modificar");
        jbCancelar =  new JButton("Cancelar");
        
        panel =  new JPanel(null);panel.setBackground(new Color(247,247,247));
        
        jlModificarCliente.setBounds(75, 10, 190, 40);
        jlImageCliente.setBounds(90, 45, 160, 160);jlImageCliente.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/Clientes.png")));
        jlNombres.setBounds(20, 210, 90, 20);txNombres.setBounds(110, 210, 200, 20);
        jlApellidos.setBounds(20, 240, 90, 20);txApellidos.setBounds(110, 240, 200, 20);
        jlNDocumento.setBounds(20, 270, 90, 20);txNDocumento.setBounds(110, 270, 200, 20);
        jlDireccion.setBounds(20, 300, 90, 20);txDireccion.setBounds(110, 300, 200, 20);
        jlTelefono.setBounds(20, 330, 90, 20);txTelefono.setBounds(110, 330, 200, 20);
        
        jbModificar.setBounds(70, 380, 90, 20);
        jbCancelar.setBounds(180, 380, 90, 20);
        
        panel.add(jlDireccion);
        panel.add(jlImageCliente);
        panel.add(jlNDocumento);
        panel.add(jlNombres);
        panel.add(jlApellidos);
        panel.add(jlModificarCliente);
        panel.add(jlTelefono);
        
        panel.add(jbCancelar);
        panel.add(jbModificar);
        
        panel.add(txApellidos);
        panel.add(txDireccion);
        panel.add(txNDocumento);
        panel.add(txNombres);
        panel.add(txTelefono);
        
        add(panel,BorderLayout.CENTER);
    }

    private void setupEvents() {
        jbCancelar.setBackground(new Color(36, 83, 181));
        jbModificar.setBackground(new Color(36, 83, 181));
        setLocationRelativeTo(null);
        this.setResizable(false);
        txNDocumento.setEditable(false);
        
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(rootPane, "Desea Cancelar la Modificación?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0)dispose();
            }
        });
        
        jbModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCampos() && verificarEspacios()) {
                    cliente.setApellicli(txApellidos.getText().toLowerCase());
                    cliente.setDircli(txDireccion.getText().toLowerCase());
                    cliente.setNomcli(txNombres.getText().toLowerCase());
                    cliente.setTelecli(txTelefono.getText());
                    if(cliente.Modificar_Datos()){
                        JOptionPane.showMessageDialog(rootPane, "Los datos se han Modificado Exitosamente!", "info", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    
                }
            }
        });
    }
    
    private boolean verificarEspacios(){
        int espaciosNombres=0,espaciosApellidos=0;
        String cadena=txNombres.getText();
        for (int i = 0; i < cadena.length(); i++) {
        if (cadena.charAt(i) == ' ') espaciosNombres++;
        }
        cadena=txApellidos.getText();
        for (int i = 0; i < cadena.length(); i++) { 
        if (cadena.charAt(i) == ' ') espaciosApellidos++;
        }
        if(espaciosNombres>1 || espaciosApellidos>1){
            JOptionPane.showMessageDialog(rootPane, "Los Nombres o Apellidos deben tener 1 solo espacio", "Atención", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean verificarCampos(){
        if(!(txNombres.getText().length()>3 && txApellidos.getText().length()>3 && txNDocumento.getText().length()>4 && txDireccion.getText().length()>3 && txTelefono.getText().length()>4)){
            JOptionPane.showMessageDialog(rootPane, "Llenar todos los campos", "Atención", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
