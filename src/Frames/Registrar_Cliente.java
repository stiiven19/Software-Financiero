package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import softwarefinancieroap.Cliente;

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
        
        jlRegistroCliente = new JLabel("Registro Cliente");jlRegistroCliente.setFont(new Font("Comic Sans MS",1,22));
        jlClientes = new JLabel("Clientes");jlClientes.setFont(new Font("Comic Sans MS",0,15));
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
        
        jlRegistroCliente.setBounds(20, 30, 200, 30);
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
        jbCancelar.setBackground(new Color(36, 83, 181));
        jbRegistrar.setBackground(new Color(36, 83, 181));
        this.setResizable(false);
        
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(rootPane, "Desea Cancelar El Proceso de registro?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0)dispose();
            }
        });
        
        jbRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCampos() && verificarEspacios()) {
                    Cliente cliente = new Cliente(txNDocumento.getText(),
                        txNombres.getText().toLowerCase(),
                        txApellidos.getText().toLowerCase(),
                        txDireccion.getText().toLowerCase(),
                        txTelefono.getText());
                    if (cliente.Buscar_Cliente()) {
                        JOptionPane.showMessageDialog(rootPane, "El cliente ya ha sido registrado", "Atención", JOptionPane.WARNING_MESSAGE);
                    }else{
                        if (cliente.registrar_cliente()){
                        JOptionPane.showMessageDialog(rootPane, "Cliente Registrado Exitosamente!", "info", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        }
                    }
                }
            }
        });
        
        txNombres.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(c>=97 && c<=122) && !(c>=65 && c<=90) && !(c>=00 && c<=32) && c!=127 && c!='ñ' && c!='Ñ'){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txNombres.getText().length()+1>30){
                    e.consume();
                }
            }
        });
        
        txApellidos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(c>=97 && c<=122) && !(c>=65 && c<=90) && !(c>=00 && c<=32) && c!=127 && c!='ñ' && c!='Ñ'){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txApellidos.getText().length()+1>30){
                    e.consume();
                }
            }
        });
        
        txNDocumento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txNDocumento.getText().length()+1>10){
                    e.consume();
                }
            }
        });
        
        txTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txTelefono.getText().length()+1>10){
                    e.consume();
                }
            }
        });
        
        txDireccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (txDireccion.getText().length()+1>50){
                    e.consume();
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
