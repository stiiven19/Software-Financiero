/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import softwarefinancieroap.Garantia;

/**
 *
 * @author lenovo
 */
public class Registrar_Garantia extends JDialog {
    
    private JLabel      jlCodigo, jlTipo, jlValor, jlUbicacion, jlRegistrarGarantia;
    private JTextField  txCodigo, txTipo, txValor, txUbicacion;
    private JButton     jbRegistrar, jbCancelar;
    private JPanel      panel;
    Garantia    garantia;
    
    public Registrar_Garantia(Registrar_Prestamo mainframe){
        super(mainframe, true);
        setTitle("Registrar Prestamo");
	setSize(340, 310);
        setupWidgets();
        setupEvents();
        setVisible(true);
    }//constructor

    private void setupWidgets() {
        jlCodigo = new JLabel("Codigo:");
        jlTipo = new JLabel("Tipo:");
        jlValor = new JLabel("Valor:");
        jlUbicacion = new JLabel("Ubicacion:");
        jlRegistrarGarantia = new JLabel("Registrar Garantia");
        
        txCodigo = new JTextField();
        txTipo = new JTextField();
        txValor = new JTextField();
        txUbicacion = new JTextField();
        
        jbRegistrar = new JButton("Registrar");
        jbCancelar = new JButton("Cancelar");
        
        panel = new JPanel(null);panel.setBackground(new Color(247,247,247));
        
        jlRegistrarGarantia.setBounds(20, 25, 120, 30);
        jlCodigo.setBounds(20, 75, 90, 20);txCodigo.setBounds(120, 75, 120, 20);
        jlTipo.setBounds(20, 115, 90, 20);txTipo.setBounds(120, 115, 120, 20);
        jlValor.setBounds(20, 155, 90, 20);txValor.setBounds(120, 155, 120, 20);
        jlUbicacion.setBounds(20, 195, 90, 20);txUbicacion.setBounds(120, 195, 120, 20);
        
        jbRegistrar.setBounds(75, 235, 90, 20);
        jbCancelar.setBounds(175, 235, 90, 20);
        
        panel.add(jlCodigo);
        panel.add(jlTipo);
        panel.add(jlValor);
        panel.add(jlUbicacion);
        panel.add(jlRegistrarGarantia);
        
        panel.add(txCodigo);
        panel.add(txTipo);
        panel.add(txValor);
        panel.add(txUbicacion);
        
        panel.add(jbRegistrar);
        panel.add(jbCancelar);
                
        
        add(panel);
    }//acomodando objetos en la ventana

    public Garantia getGarantia() {
        return garantia;
    }//evento getgarantia
    
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
                if (txCodigo.getText().length()>3 && txTipo.getText().length()>3 && txValor.getText().length()>3 && txUbicacion.getText().length()>3) {
                    garantia = new Garantia(txCodigo.getText(), txTipo.getText(), Double.parseDouble(txValor.getText()), txUbicacion.getText());
                    String [] garantiaArray=garantia.Buscar_Garantia();
                    if (garantiaArray!=null){//null si no hay garantia igual
                        String msj="Ya hay una garantia con este codigo\n"
                                + "Desea registrar esta como su garantia?"
                                + "\n-Codigo: " + garantiaArray[0] 
                                + "\n-Tipo: " + garantiaArray[1]
                                + "\n-Valor: " + garantiaArray[2]
                                + "\n-Ubicacion: " + garantiaArray[3];
                        if(JOptionPane.showConfirmDialog(rootPane, msj, "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                            garantia = new Garantia(garantiaArray[0], garantiaArray[1], Double.parseDouble(garantiaArray[2]), garantiaArray[3]);
                            JOptionPane.showMessageDialog(rootPane, "Garantia Registrada", "Info", JOptionPane.INFORMATION_MESSAGE); 
                            setVisible(false);
                            dispose();
                        }else{
                            garantia=null;
                            txCodigo.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Garantia Registrada", "Info", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Rellenar Campos", "Atención!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        txValor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txValor.getText().length()+1>9){
                    e.consume();
                }
            }
        });
        
        txTipo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(c>=97 && c<=122) && !(c>=65 && c<=90) && !(c>=00 && c<=32) && c!=127 && c!='ñ' && c!='Ñ'){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txTipo.getText().length()+1>30){
                    e.consume();
                }
            }
        });
        
        txCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txCodigo.getText().length()+1>10){
                    e.consume();
                }
            }
        });
        
        txUbicacion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (txUbicacion.getText().length()+1>29){
                    e.consume();
                }
            }
        });
    }//eventos de botones
}
