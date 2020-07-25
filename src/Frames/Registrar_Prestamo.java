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
    
    private     JLabel              jlRegistarPrestamo,jlCodigo,jlFechaSolicitud,jlFechaInicio,jlFechaTermino,jlMonto,jlTasaInteres,jlEstado,jlImageGarantia,jlImageFiador,jlCuotas;
    private     JButton             jbRegistrar, jbCancelar, jbRegistrarGarantia, jbRegistrarFiador;
    private     JTextField          txCodigo,txFechaSolicitud,txFechaInicio,txFechaTermino,txMonto,txTasaInteres, txCuotas;
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
        setTitle("Registrar Prestamo");
	setSize(600, 520);
        setupWidgets();
        setupEvents();
        setVisible(true);
    }

    private void setupWidgets() {
        //inicializamos obj
        jlRegistarPrestamo = new JLabel("Registro Préstamo");jlRegistarPrestamo.setFont(new Font("Comic Sans MS",1,22));
        jlCodigo = new JLabel("Codigo:");
        jlFechaSolicitud = new JLabel("Fecha:");
        jlFechaInicio = new JLabel("Fecha Inicio:");
        jlFechaTermino = new JLabel("Fecha Termino:");
        jlMonto = new JLabel("Monto:");
        jlTasaInteres = new JLabel("Tasa Interes:");
        jlEstado = new JLabel("Estado:");
        jlImageGarantia = new JLabel();
        jlImageFiador = new JLabel();
        jlCuotas = new JLabel("Numero Cuotas: ");
        
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
        txCuotas = new JTextField();
        
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
        jlCuotas.setBounds(20, 330, 100, 20);txCuotas.setBounds(130,330,120,20);
        jlEstado.setBounds(20, 370, 60, 20);jcbEstado.setBounds(130, 370, 120, 20);
        jlImageGarantia.setBounds(300, 30,201,121);
        jlImageFiador.setBounds(300, 200,201,121);
        
        jbRegistrar.setBounds(205, 430, 90, 20); jbCancelar.setBounds(305, 430, 90, 20);
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
        panel.add(jlCuotas);
        
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
        panel.add(txCuotas);
        
        panel.add(jcbEstado);
        panel.add(jcheckRegistrarGarantia);
        panel.add(jcheckRegistrarFiador);
        add(panel,BorderLayout.CENTER);
    }

    private void setupEvents() {
        jbCancelar.setBackground(new Color(36, 83, 181));
        jbRegistrar.setBackground(new Color(36, 83, 181));
        jbRegistrarFiador.setBackground(new Color(36, 83, 181));
        jbRegistrarGarantia.setBackground(new Color(36, 83, 181));
        this.setResizable(false);
        jcheckRegistrarGarantia.setEnabled(false);jcheckRegistrarGarantia.setSelected(false);
        jcheckRegistrarFiador.setEnabled(false);
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
                if(JOptionPane.showConfirmDialog(rootPane, "Desea Cancelar El Proceso de registro?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0)dispose();
            }    
        });//evento de boton cancelar prestamo
        
        jbRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCampos() && verificarInteres()) {
                    if (garantia != null && (CCfiador!=null && !"".equals(CCfiador))) {
                        Prestamo prestamo = new Prestamo(CCcliente, txCodigo.getText(), txFechaSolicitud.getText(),null, null, null, Double.parseDouble(txTasaInteres.getText()), Double.parseDouble(txMonto.getText()), (String) jcbEstado.getSelectedItem(), garantia.getCodgarantia(), CCfiador,Integer.parseInt(txCuotas.getText()));
                        RegistrarPrestamo(prestamo);
                    }else{
                        if (garantia != null) {
                            Prestamo prestamo = new Prestamo(CCcliente, txCodigo.getText(), txFechaSolicitud.getText(),null, null, null, Double.parseDouble(txTasaInteres.getText()), Double.parseDouble(txMonto.getText()), (String) jcbEstado.getSelectedItem(), garantia.getCodgarantia(), null, Integer.parseInt(txCuotas.getText()));
                            RegistrarPrestamo(prestamo);
                        }else{
                            Prestamo prestamo = new Prestamo(CCcliente, txCodigo.getText(), txFechaSolicitud.getText(),null, null, null, Double.parseDouble(txTasaInteres.getText()), Double.parseDouble(txMonto.getText()), (String) jcbEstado.getSelectedItem(), null, CCfiador, Integer.parseInt(txCuotas.getText()));
                            RegistrarPrestamo(prestamo);
                        }
                    }
                }
            }
        });//evento boton registrar prestamo
        
        jbRegistrarGarantia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (garantia==null) {
                    CrearGarantia();
                }else{
                    if (JOptionPane.showConfirmDialog(rootPane, "-Esta intentando registrar una Garantia\n-Desea Borrar la Garantia ya registrada?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                        CrearGarantia();
                    }
                }
            }
        });//evento boton registrar garantia
        
        jbRegistrarFiador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CCfiador == null || "".equals(CCfiador)) {
                    CrearFiador();
                }else{
                    if (JOptionPane.showConfirmDialog(rootPane, "-Esta intentando registrar un Fiador\n-Desea BORRAR el Fiador ya registrado?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                        CrearFiador();
                    }
                }
            }
        });//evento boton registrar fiador
        
        txMonto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if( !(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txMonto.getText().length()+1>10){
                    e.consume();
                }
            }
        });
        
        txTasaInteres.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if( !(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127 && c!=46){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txTasaInteres.getText().length()+1>6){
                    e.consume();
                }
            }
        });
        
        txCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if( !(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txCodigo.getText().length()+1>10){
                    e.consume();
                }
            }
        });
        
        txCuotas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if( !(c>=48 && c<=57  ) && !(c>=00 && c<=31) && c!=127){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (txCuotas.getText().length()+1>2){
                    e.consume();
                }
            }
        });
    }//eventos de objetos de la ventana
    
    private boolean verificarCampos(){//true si los campos estan llenos
        if(!((garantia != null|| (CCfiador!=null && !"".equals(CCfiador))) && txCodigo.getText().length()>=3 && txMonto.getText().length()>4 && txTasaInteres.getText().length()>=1 && txCuotas.getText().length()>=1 )){
            JOptionPane.showMessageDialog(rootPane, "Llenar todos los campos", "Atención", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean verificarInteres(){//true si el interes esta bien
        int puntosInteres=0;
        String cadena=txTasaInteres.getText();
        for (int i = 0; i < cadena.length(); i++) {
        if (cadena.charAt(i) == '.') puntosInteres++;
        }
        if(puntosInteres>1){
            JOptionPane.showMessageDialog(rootPane, "El interes solo puede tener un punto decimal", "Atención", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void RegistrarPrestamo(Prestamo prestamo){//busca y registra el prestamo en caso de que se pueda
        int Buscar_prestamo=prestamo.Buscar_Prestamo();
        if (Buscar_prestamo==2) {
            JOptionPane.showMessageDialog(rootPane, "El Codigo del prestamo ya se encuentra registrado", "Atención!", JOptionPane.WARNING_MESSAGE);
        }else{
            if (Buscar_prestamo==3) {
                JOptionPane.showMessageDialog(rootPane, "El Cliente ya tiene un prestamo en proceso.\nDebe concluir el prestamo actual para registrar otro", "Atención!", JOptionPane.WARNING_MESSAGE);
            }else{
                garantia.registrarGarantia();
                if (prestamo.registrarPrestamo() && Buscar_prestamo==1) {
                JOptionPane.showMessageDialog(rootPane, "Prestamo registrado exitosamente", "Información!", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                }
            }
        }
    }
    
    private void CrearGarantia(){
        Registrar_Garantia Reggarantia = new Registrar_Garantia(mainPrestamo);
        garantia=Reggarantia.getGarantia();
        if (garantia != null) {
            jcheckRegistrarGarantia.setSelected(true);
        }else{
            JOptionPane.showMessageDialog(rootPane, "No se ha registrado la garantia", "Atención!", JOptionPane.WARNING_MESSAGE);
            jcheckRegistrarGarantia.setSelected(false);
        }
    }//verifica que se ha registrado la garantia 
    
    private void CrearFiador(){
        Registrar_Fiador registro = new Registrar_Fiador(mainPrestamo);
        CCfiador = registro.getFiador();
        if (!"".equals(CCfiador)) {
            jcheckRegistrarFiador.setSelected(true);
        }else{
            JOptionPane.showMessageDialog(rootPane, "No se ha registrado el fiador", "Atención!", JOptionPane.WARNING_MESSAGE);
            jcheckRegistrarFiador.setSelected(false);
        }
    }//verifica que se ha registrado la garantia 
}
