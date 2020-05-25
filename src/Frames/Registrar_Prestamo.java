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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import softwarefinancieroap.Garantia;
import softwarefinancieroap.Prestamo;

/**
 *
 * @author lenovo
 */
public class Registrar_Prestamo extends JDialog{
    
    private     JLabel              jlRegistarPrestamo,jlCodigo,jlFechaSolicitud,jlFechaInicio,jlFechaTermino,jlMonto,jlTasaInteres,jlEstado,jlImageGarantia,jlImageFiador;
    private     JButton             jbRegistrar, jbCancelar, jbRegistrarGarantia, jbRegistrarFiador;
    private     JTextField          txCodigo,txFechaSolicitud,txFechaInicio,txFechaTermino,txMonto,txTasaInteres;
    private     JPanel              panel;
    private     JComboBox<String>   jcbEstado;
    private     JCheckBox           jcheckRegistrarGarantia,jcheckRegistrarFiador;
    private final     Registrar_Prestamo  mainPrestamo = this;
    private     Garantia            garantia;
    private     String              CCfiador;
    private final     String              CCcliente;
    
    public Registrar_Prestamo(MenuPrincipal mainframe,String CCcliente1){
        super(mainframe, true);
        this.CCcliente = CCcliente1 ;
        setTitle("            Registrar Prestamo");
	setSize(600, 480);
        setupWidgets();
        setupEvents();
        setVisible(true);
        //load();
    }

    private void setupWidgets() {
        //inicializamos obj
        jlRegistarPrestamo = new JLabel("Registro Préstamo");
        jlCodigo = new JLabel("Codigo:");
        jlFechaSolicitud = new JLabel("Fecha:");
        jlFechaInicio = new JLabel("Fecha Inicio:");
        jlFechaTermino = new JLabel("Fecha Termino:");
        jlMonto = new JLabel("Monto:");
        jlTasaInteres = new JLabel("Tasa Interes:");
        jlEstado = new JLabel("Estado:");
        jlImageGarantia = new JLabel();
        jlImageFiador = new JLabel();
        
        jbRegistrar = new JButton("Registrar");
        jbCancelar = new JButton("Cancelar");
        jbRegistrarGarantia = new JButton("Registrar Garantia");
        jbRegistrarFiador = new JButton("Registrar Fiador");
        
        txCodigo = new JTextField("");
        txFechaSolicitud = new JTextField("");
        txFechaInicio = new JTextField("");
        txFechaTermino = new JTextField("");
        txMonto = new JTextField("");
        txTasaInteres = new JTextField("");
        
        jcbEstado = new JComboBox<>(new String[]{"aprobado","pendiente","rechazado"});
        jcheckRegistrarGarantia = new JCheckBox("", true);
        jcheckRegistrarFiador = new JCheckBox("", false);
        
        panel = new JPanel(null);panel.setBackground(new Color(247, 247, 247));
        jlImageGarantia.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/Garantias.png")));
        jlImageFiador.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/Captura2.png")));
        
        jlRegistarPrestamo.setBounds(20, 30, 210, 30);
        jlCodigo.setBounds(20, 90, 60, 20);txCodigo.setBounds(130, 90, 120, 20);
        jlFechaSolicitud.setBounds(20, 130, 90, 20);txFechaSolicitud.setBounds(130, 130, 120, 20);
        jlFechaInicio.setBounds(20, 170, 90, 20);txFechaInicio.setBounds(130, 170, 120, 20);
        jlFechaTermino.setBounds(20, 210, 90, 20);txFechaTermino.setBounds(130, 210, 120, 20);
        jlMonto.setBounds(20, 250, 60, 20);txMonto.setBounds(130, 250, 120, 20);
        jlTasaInteres.setBounds(20, 290, 90, 20);txTasaInteres.setBounds(130, 290, 120, 20);
        jlEstado.setBounds(20, 330, 60, 20);jcbEstado.setBounds(130, 330, 120, 20);
        jlImageGarantia.setBounds(300, 30,201,121);
        jlImageFiador.setBounds(300, 200,201,121);
        
        jbRegistrar.setBounds(205, 390, 90, 20); jbCancelar.setBounds(305, 390, 90, 20);
        jbRegistrarGarantia.setBounds(320, 160, 150, 20);jcheckRegistrarGarantia.setBounds(300, 160, 20, 20);
        jbRegistrarFiador.setBounds(320, 330, 150, 20);jcheckRegistrarFiador.setBounds(300, 330, 20, 20);
        
        //agregamos al panel los obj
        panel.add(jlRegistarPrestamo);
        panel.add(jlCodigo);
        panel.add(jlFechaSolicitud);
        panel.add(jlFechaInicio);
        panel.add(jlFechaTermino);
        panel.add(jlMonto);
        panel.add(jlTasaInteres);
        panel.add(jlEstado);
        panel.add(jlImageGarantia);
        panel.add(jlImageFiador);
        
        panel.add(jbRegistrar);
        panel.add(jbCancelar);
        panel.add(jbRegistrarGarantia);
        panel.add(jbRegistrarFiador);
        
        panel.add(txCodigo);
        panel.add(txFechaSolicitud);
        panel.add(txFechaInicio);
        panel.add(txFechaTermino);
        panel.add(txMonto);
        panel.add(txTasaInteres);
        
        panel.add(jcbEstado);
        panel.add(jcheckRegistrarGarantia);
        panel.add(jcheckRegistrarFiador);
        add(panel,BorderLayout.CENTER);
    }

    private void setupEvents() {
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setResizable(false);
        jcheckRegistrarGarantia.setEnabled(false);jcheckRegistrarGarantia.setSelected(false);
        jcheckRegistrarFiador.setEnabled(false);
        //txfechasolicitud
        txFechaSolicitud.setEditable(false);
        txFechaInicio.setEditable(false);
        txFechaTermino.setEditable(false);
        jcbEstado.setSelectedIndex(1);jcbEstado.setEnabled(false);
        Date fecha = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txFechaSolicitud.setText(""+dateFormat.format(fecha));
        //estado sin editar
        
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }    
        });//evento de boton cancelar prestamo
        
        jbRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //txFechaInicio.getText().length()>3 && txFechaTermino.getText().length()>3 &&
                if ((garantia != null|| (CCfiador!=null && CCfiador!="")) && txCodigo.getText().length()>=3 && txMonto.getText().length()>4 && txTasaInteres.getText().length()>=1) {
                    if (garantia != null && (CCfiador!=null && CCfiador!="")) {
                        Prestamo prestamo = new Prestamo(txFechaSolicitud.getText(), null, CCfiador, garantia.getCodgarantia(), (String) jcbEstado.getSelectedItem(), CCcliente, txCodigo.getText(), null, null, Double.parseDouble(txMonto.getText()), Double.parseDouble(txTasaInteres.getText()));
                        garantia.registrarGarantia();
                        if (prestamo.Buscar_Prestamo()) {
                            JOptionPane.showMessageDialog(rootPane, "El prestamo ya se encuentra registrado", "Atención!", JOptionPane.WARNING_MESSAGE);
                        }else{
                            if (prestamo.registrarPrestamo()) {
                            JOptionPane.showMessageDialog(rootPane, "-Prestamo registrado exitosamente", "Información!", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            }
                        }
                    }else{
                        if (garantia != null) {
                            Prestamo prestamo = new Prestamo(txFechaSolicitud.getText(), null, null, garantia.getCodgarantia(), (String) jcbEstado.getSelectedItem(), CCcliente, txCodigo.getText(), null, null, Double.parseDouble(txMonto.getText()), Double.parseDouble(txTasaInteres.getText()));
                            garantia.registrarGarantia();
                            if (prestamo.Buscar_Prestamo()) {
                                JOptionPane.showMessageDialog(rootPane, "El prestamo ya se encuentra registrado", "Atención!", JOptionPane.WARNING_MESSAGE);
                            }else{
                                if (prestamo.registrarPrestamo()) {
                                JOptionPane.showMessageDialog(rootPane, "-Prestamo registrado exitosamente", "Información!", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                }
                            }
                            
                        }else{
                            Prestamo prestamo = new Prestamo(txFechaSolicitud.getText(), null, CCfiador, null, (String) jcbEstado.getSelectedItem(), CCcliente, txCodigo.getText(), null, null, Double.parseDouble(txMonto.getText()), Double.parseDouble(txTasaInteres.getText()));
                            if (prestamo.Buscar_Prestamo()) {
                                JOptionPane.showMessageDialog(rootPane, "El prestamo ya se encuentra registrado", "Atención!", JOptionPane.WARNING_MESSAGE);
                            }else{
                                if (prestamo.registrarPrestamo()) {
                                JOptionPane.showMessageDialog(rootPane, "-Prestamo registrado exitosamente", "Información!", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                }
                            }
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "-Debe llenar todos los campos y \n-Tener registrado una Garantia y/o Fiador", "Atención!", JOptionPane.WARNING_MESSAGE);
                }
                //Prestamo prestamo = new Prestamo(txFechaSolicitud.getText(), "N", WIDTH, garantia, 0, WIDTH, txCodigo.getText(), fechInicio, fechTermino, WIDTH, WIDTH)
            }
        });//evento boton registrar prestamo
        
        jbRegistrarGarantia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (garantia==null) {
                    Registrar_Garantia Reggarantia = new Registrar_Garantia(mainPrestamo);
                    garantia=Reggarantia.getGarantia();
                    if (garantia != null) {
                        jcheckRegistrarGarantia.setSelected(true);
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "No se ha registrado la garantia", "Atención!", JOptionPane.WARNING_MESSAGE);
                        jcheckRegistrarGarantia.setSelected(false);
                    }
                }else{
                    if (JOptionPane.showConfirmDialog(rootPane, "-Esta intentando registrar una Garantia\n-Desea Borrar la Garantia ya registrada?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                        Registrar_Garantia Reggarantia = new Registrar_Garantia(mainPrestamo);
                        garantia=Reggarantia.getGarantia();
                        if (garantia != null) {
                            jcheckRegistrarGarantia.setSelected(true);
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "No se ha registrado la garantia", "Atención!", JOptionPane.WARNING_MESSAGE);
                            jcheckRegistrarGarantia.setSelected(false);
                        }
                    }
                }
            }
        });//evento boton registrar garantia
        
        jbRegistrarFiador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CCfiador == null || "".equals(CCfiador)) {
                    Registrar_Fiador registro = new Registrar_Fiador(mainPrestamo);
                    CCfiador = registro.getFiador();
                    if (!"".equals(CCfiador)) {
                        jcheckRegistrarFiador.setSelected(true);
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "No se ha registrado el fiador", "Atención!", JOptionPane.WARNING_MESSAGE);
                        jcheckRegistrarFiador.setSelected(false);
                    }
                }else{
                    if (JOptionPane.showConfirmDialog(rootPane, "-Esta intentando registrar un Fiador\n-Desea BORRAR el Fiador ya registrado?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                       Registrar_Fiador registro = new Registrar_Fiador(mainPrestamo);
                        CCfiador = registro.getFiador();
                        if ( !"".equals(CCfiador)) {
                            jcheckRegistrarFiador.setSelected(true);
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "No se ha registrado el fiador", "Atención!", JOptionPane.WARNING_MESSAGE);
                            jcheckRegistrarFiador.setSelected(false);
                        } 
                    }
                }
            }
        });//evento boton registrar fiador
        
        
    }//eventos de objetos de la ventana
}
