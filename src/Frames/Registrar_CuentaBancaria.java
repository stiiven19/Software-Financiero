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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import softwarefinancieroap.CuentaBancaria;

/**
 *
 * @author lenovo
 */
public class Registrar_CuentaBancaria extends JDialog{
    
    private JLabel      jlRegistroCuentaBancaria,jlNumeroCuenta, jlBanco, jlTipo;
    private JTextField  txNumeroCuenta, txBanco;
    private JButton     jbRegistrar, jbCancelar;
    private JCheckBox   jcheckAhorros, jcheckCorriente;
    private JPanel      panel;
    CuentaBancaria      cuenta;
    
    public Registrar_CuentaBancaria(Registrar_Inversion mainframe){
        super(mainframe, true);
        setTitle("Registrar inversión");
	setSize(340, 300);
        setupWidgets();
        setupEvents();
        setVisible(true);
        //load();
    }

    private void setupWidgets() {
        jlBanco = new JLabel("Banco:");
        jlNumeroCuenta = new JLabel("Numero de Cuenta:");
        jlRegistroCuentaBancaria = new JLabel("Registro de Cuenta Bancaria");
        jlTipo = new JLabel("Tipo de Cuenta:");
        
        txBanco =  new JTextField();
        txNumeroCuenta = new JTextField();
        
        jbCancelar = new JButton("Cancelar");
        jbRegistrar = new JButton("Registrar");
        
        jcheckAhorros = new JCheckBox("Ahorros");
        jcheckCorriente = new JCheckBox("Corriente");
        
        panel = new JPanel(null);
        
        jlRegistroCuentaBancaria.setBounds(20, 25,180, 30);
        jlNumeroCuenta.setBounds(20, 75, 120, 20);txNumeroCuenta.setBounds(150, 75, 120, 20);
        jlBanco.setBounds(20, 115, 120, 20);txBanco.setBounds(150, 115, 120, 20);
        jlTipo.setBounds(20, 155, 120, 20);jcheckAhorros.setBounds(150, 155, 80, 20);jcheckCorriente.setBounds(230, 155, 120, 20);
        
        jbRegistrar.setBounds(70, 200, 90, 20);
        jbCancelar.setBounds(180, 200, 90, 20);
        
        panel.add(jlBanco);
        panel.add(jlNumeroCuenta);
        panel.add(jlRegistroCuentaBancaria);
        panel.add(jlTipo);
        panel.add(jbCancelar);
        panel.add(jbRegistrar);
        panel.add(jcheckAhorros);
        panel.add(jcheckCorriente);
        panel.add(txBanco);
        panel.add(txNumeroCuenta);
        
        add(panel,BorderLayout.CENTER);
    }

    private void setupEvents() {
        jbCancelar.setBackground(new Color(36, 83, 181));
        jbRegistrar.setBackground(new Color(36, 83, 181));
        this.setResizable(false);
        
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        jbRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txBanco.getText().length()>3 && txNumeroCuenta.getText().length()>3 && (jcheckAhorros.isSelected() || jcheckCorriente.isSelected())) {
                    if (jcheckAhorros.isSelected()){
                        cuenta = new CuentaBancaria(txNumeroCuenta.getText(), txBanco.getText(), "ahorros");
                    }else{
                        cuenta = new CuentaBancaria(txNumeroCuenta.getText(), txBanco.getText(), "corriente");
                    }
                    String [] Cuentadatos=cuenta.Buscar_CuentaBancaria();
                    if(Cuentadatos[0]!= null){
                        if(JOptionPane.showConfirmDialog(rootPane, "El numero de cuenta Ya se encuentra registrado\nDesea usar la cuenta: \n-"+Cuentadatos[0]+"\n-"+Cuentadatos[1]+"\n-"+Cuentadatos[2], "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                            JOptionPane.showMessageDialog(rootPane, "Cuenta Bancaria Registrada", "Info", JOptionPane.INFORMATION_MESSAGE);
                            cuenta = new CuentaBancaria(Cuentadatos[0], Cuentadatos[1], Cuentadatos[2]);
                            setVisible(false);
                            dispose();
                        }else{
                            cuenta=null;
                            txNumeroCuenta.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Cuenta Bancaria Registrada", "Info", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Rellenar Campos", "Atención!", JOptionPane.WARNING_MESSAGE);
                } 
            }
        });
        
        txBanco.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(c>=97 && c<=122) && !(c>=65 && c<=90) && !(c>=00 && c<=32) && c!=127 && c!='ñ' && c!='Ñ'){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txBanco.getText().length()+1>29){
                    e.consume();
                }
            }
        });
        
        txNumeroCuenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if( !(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txNumeroCuenta.getText().length()+1>10){
                    e.consume();
                }
            }
        });
        
        jcheckAhorros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcheckCorriente.isSelected()){
                    jcheckCorriente.setSelected(false);
                }
                jcheckAhorros.setSelected(true);
            }
        });
        
        jcheckCorriente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcheckAhorros.isSelected()){
                    jcheckAhorros.setSelected(false);
                }
                jcheckCorriente.setSelected(true);
            }
        });
    }
    
    public CuentaBancaria getCuenta(){
        return cuenta;
    }
}
