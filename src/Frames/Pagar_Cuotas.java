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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import softwarefinancieroap.Cuota;

/**
 *
 * @author lenovo
 */
public class Pagar_Cuotas extends JDialog {

    private     JLabel      jlTipoPago,jlFechEfectiva,jlCodComprobante,jlDatosPago;
    private     JTextField  txTipoPago,txFechEfectiva,txCodComprobante;
    private     JButton     jbRegistrarDatos,jbCancelar;
    private     JPanel      panelCenter;
    private     Cuota       cuota;
    
    public Pagar_Cuotas(Ver_CuotasInversion mainframe,Cuota cuota) {
        super(mainframe, true);
        this.cuota=cuota;
        setTitle("Datos");
	setSize(310, 240);
        setupWidgets();
        setupEvents();
        setVisible(true);
    }

    private void setupWidgets() {
        jlDatosPago = new JLabel("Datos del Pago");jlDatosPago.setFont(new Font("Comic Sans MS",1,22));
        jlCodComprobante = new JLabel("Codigo del Comprobante: ");
        jlFechEfectiva = new JLabel("Fecha Efectiva: ");
        jlTipoPago = new JLabel("Tipo de Pago: ");
        
        txCodComprobante = new JTextField();
        txFechEfectiva = new JTextField();
        txTipoPago = new JTextField();
        
        jbCancelar = new JButton("Cancelar");
        jbRegistrarDatos = new JButton("Registrar Datos");
        panelCenter = new JPanel(null);panelCenter.setBackground(new Color(247,247,247));
        
        jlCodComprobante.setBounds(20, 50, 150, 15);
        jlDatosPago.setBounds(55, 10, 200, 30);
        jlFechEfectiva.setBounds(20, 85, 120, 15);
        jlTipoPago.setBounds(20, 120, 120, 15);
        
        txCodComprobante.setBounds(170, 50, 100, 20);
        txFechEfectiva.setBounds(170, 85, 100, 20);
        txTipoPago.setBounds(170, 120, 100, 20);
        
        jbRegistrarDatos.setBounds(35, 160, 125, 20);
        jbCancelar.setBounds(170, 160, 90, 20);
        
        panelCenter.add(jlDatosPago);
        panelCenter.add(jlCodComprobante);
        panelCenter.add(jlFechEfectiva);
        panelCenter.add(jlTipoPago);
        
        panelCenter.add(jbCancelar);
        panelCenter.add(jbRegistrarDatos);
        
        //derecha
        panelCenter.add(txCodComprobante);
        panelCenter.add(txFechEfectiva);
        panelCenter.add(txTipoPago);
                
        add(panelCenter,BorderLayout.CENTER);
        
    }

    private void setupEvents() {
        jbCancelar.setBackground(new Color(36, 83, 181));
        jbRegistrarDatos.setBackground(new Color(36, 83, 181));
        setLocationRelativeTo(null);
        this.setResizable(false);
        Date fecha = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txFechEfectiva.setText(""+dateFormat.format(fecha));
        txFechEfectiva.setEditable(false);
        
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(rootPane, "Desea Cancelar El Proceso?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0)dispose();
            }
        });
        
        jbRegistrarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(rootPane, "Desea Completar el pago con los datos?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                    if(txCodComprobante.getText().length()>=3 && txTipoPago.getText().length()>4){
                        cuota.setCodcomprobante(txCodComprobante.getText());
                        cuota.setFechEfectiva(txFechEfectiva.getText());
                        cuota.setTipoPago(txTipoPago.getText().toLowerCase());
                        cuota.setEstadoCuota("Pagada");
                        if (cuota.pagarCuota()){
                            JOptionPane.showMessageDialog(rootPane, "Se ha pagado la cuota", "Info", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Llenar todos los campos", "Atención", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        
        txCodComprobante.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if( !(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txCodComprobante.getText().length()+1>10){
                    e.consume();
                }
            }
        });
        
        txTipoPago.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                
                if (txTipoPago.getText().length()+1>30){
                    e.consume();
                }
            }
        });
    }
    
    
}    