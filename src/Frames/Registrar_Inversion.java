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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import softwarefinancieroap.CuentaBancaria;
import softwarefinancieroap.Cuota;
import softwarefinancieroap.Inversion;

/**
 *
 * @author lenovo
 */
public class Registrar_Inversion extends JDialog{

    private JLabel          jlCodigo, jlFechInicio, jlFechTermino, jlMonto, jlTasaInteres, jlCuotas, jlRegistroInversion, jlImageBanco;
    private JTextField      txCodigo, txFechInicio, txFechTermino, txMonto, txTasaInteres, txCuotas;
    private JButton         jbRegistrar, jbCancelar, jbRegistrarCuentaBancaria;
    private JCheckBox       jcheckRegistroBanco;
    private JPanel          panel;
    private CuentaBancaria  cuenta;
    private final Registrar_Inversion mainInversion = this;
            
    private final String CCcliente;
    
    public Registrar_Inversion(MenuPrincipal mainframe,String CCcliente1){
        super(mainframe, true);
        this.CCcliente = CCcliente1 ;
        setTitle("Registrar inversión");
	setSize(530, 410);
        setupWidgets();
        setupEvents();
        setVisible(true);
    }

    private void setupWidgets() {
        jlCodigo = new JLabel("Codigo:");
        jlCuotas = new JLabel("Cuotas:");
        jlFechInicio = new JLabel("Fecha Inicio:");
        jlFechTermino = new JLabel("Fecha Termino:");
        jlMonto = new JLabel("Monto:");
        jlRegistroInversion = new JLabel("Registro Inversión");jlRegistroInversion.setFont(new Font("Comic Sans MS",1,22));
        jlTasaInteres = new JLabel("Tasa de Interes:");
        jlImageBanco = new JLabel();    
        
        txCodigo = new JTextField();
        txCuotas = new JTextField();
        txFechInicio = new JTextField();
        txFechTermino = new JTextField();
        txMonto = new JTextField();
        txTasaInteres = new JTextField();
        
        jbCancelar = new JButton("Cancelar");
        jbRegistrar = new JButton("Registrar");
        jbRegistrarCuentaBancaria = new JButton("Registrar Cuenta Bancaria");
        
        jcheckRegistroBanco = new JCheckBox("",false);
        
        panel = new JPanel(null);panel.setBackground(new Color(247, 247, 247));
        jlImageBanco.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/banco.jpg")));
        
        jlRegistroInversion.setBounds(20, 30, 210, 30);
        jlCodigo.setBounds(20, 90, 60, 20);txCodigo.setBounds(130, 90, 120, 20);
        jlFechInicio.setBounds(20, 130, 90, 20);txFechInicio.setBounds(130, 130, 120, 20);
        jlFechTermino.setBounds(20, 170, 90, 20);txFechTermino.setBounds(130, 170, 120, 20);
        jlMonto.setBounds(20, 210, 90, 20);txMonto.setBounds(130, 210, 120, 20);
        jlTasaInteres.setBounds(20, 250, 110, 20);txTasaInteres.setBounds(130, 250, 120, 20);
        jlCuotas.setBounds(20, 290, 90, 20);txCuotas.setBounds(130, 290, 120, 20);
        
        jlImageBanco.setBounds(330, 60, 130, 130);
        
        jbRegistrar.setBounds(165, 330, 90, 20);jbCancelar.setBounds(275, 330, 90, 20);
        jbRegistrarCuentaBancaria.setBounds(305, 200, 190, 20);
        jcheckRegistroBanco.setBounds(275, 200, 20, 20);
        
        panel.add(jlCodigo);
        panel.add(jlFechInicio);
        panel.add(jlFechTermino);
        panel.add(jlMonto);
        panel.add(jlTasaInteres);
        panel.add(jlCuotas);
        panel.add(jlImageBanco);
        panel.add(jlRegistroInversion);
        panel.add(jbRegistrar);
        panel.add(jbCancelar);
        panel.add(jbRegistrarCuentaBancaria);
        panel.add(jcheckRegistroBanco);
        panel.add(txCodigo);
        panel.add(txCuotas);
        panel.add(txFechInicio);
        panel.add(txFechTermino);
        panel.add(txMonto);
        panel.add(txTasaInteres);
        
        add(panel,BorderLayout.CENTER);
    }

    private void setupEvents() {
        jbCancelar.setBackground(new Color(36, 83, 181));
        jbRegistrar.setBackground(new Color(36, 83, 181));
        jbRegistrarCuentaBancaria.setBackground(new Color(36, 83, 181));
        this.setResizable(false);
        jcheckRegistroBanco.setEnabled(false);
        txFechInicio.setEditable(false);
        txFechTermino.setEditable(false);
        Date fecha = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txFechInicio.setText(""+dateFormat.format(fecha));
        
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(rootPane, "Desea Cancelar El Proceso de registro?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0)dispose();
            }    
        });
        
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
            
            @Override
            public void keyReleased(KeyEvent e){
                if(txCuotas.getText().length()==0){
                    txFechTermino.setText("");
                }
                if(txMonto.getText().length()>5 && txCuotas.getText().length()>0 && txTasaInteres.getText().length()>0){
                    int numerodias=30*Integer.parseInt(txCuotas.getText());
                    String dia="";
                    String mes="";
                    String anio="";
                    for (int i = 0; i < txFechInicio.getText().length(); i++) {
                        char cha =txFechInicio.getText().charAt(i);
                        if (i<2){
                            dia+=cha;
                        }
                        if(i>2 && i<5){
                            mes+=cha;
                        }
                        if(i>5 && i<=10){
                            anio+=cha;
                        }
                    }
                    Date fechai = new Date((Integer.parseInt(anio)-1900),(Integer.parseInt(mes)-1), Integer.parseInt(dia));
                    Date fechaf=sumarDiasFecha(fechai, numerodias);
                    mes=""+fechaf.getMonth();
                    if(mes.length()==1)mes="0"+fechaf.getMonth();
                    txFechTermino.setText(fechaf.getDate()+"/"+mes+"/"+(fechaf.getYear()+1900));
                }
            }
        });
        
        jbRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCampos() && verificarInteres()) {
                    Inversion inversion = new Inversion(cuenta.getNumeroCuenta(),CCcliente, txCodigo.getText(), txFechInicio.getText(), txFechTermino.getText(), Double.parseDouble(txMonto.getText()), Double.parseDouble(txTasaInteres.getText()),Integer.parseInt(txCuotas.getText()) );
                    int InversionProceso = inversion.Buscar_Inversion();
                    if(InversionProceso == 2){
                        JOptionPane.showMessageDialog(rootPane, "La Inversión ya se encuentra registrada", "Atención!", JOptionPane.WARNING_MESSAGE);
                    }else{
                        if(InversionProceso == 3){
                            JOptionPane.showMessageDialog(rootPane, "El Cliente ya tiene una Inversion en proceso.\nDebe concluir la inversion actual para registrar otra", "Atención!", JOptionPane.WARNING_MESSAGE);
                        }else{
                            cuenta.registrarCuentaBancaria();
                            if(inversion.registrarInversion()){
                                int numerodias=30*Integer.parseInt(txCuotas.getText());
                                Date fechai = getFechaInicial();
                                Date fechaf=sumarDiasFecha(fechai, numerodias);
                                double monto=Double.parseDouble(txMonto.getText());
                                double f = (1+(Double.parseDouble(txTasaInteres.getText())/100));
                                double cuota=(monto*(Math.pow(f, Integer.parseInt(txCuotas.getText()))))/Integer.parseInt(txCuotas.getText());
                                crearCuotas(fechai,fechaf,cuota,Integer.parseInt(txCuotas.getText()),cuenta);
                                JOptionPane.showMessageDialog(rootPane, "Inversión registrada exitosamente", "Información!", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            }
                        }
                    }
                }
            }
        });
        
        jbRegistrarCuentaBancaria.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (cuenta==null) {
                    CrearCuentaBancaria();
                }else{
                    if (JOptionPane.showConfirmDialog(rootPane, "-Esta intentando registrar una Cuenta Bancaria\n-Desea Borrar la Cuenta ya registrada?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                        CrearCuentaBancaria();
                    }
                }
            }
        });
    }
    public void crearCuotas(Date fechainicial,Date fechafinal, double valorcuota, int numerocuotas, CuentaBancaria cuenta){
        String [] fecha=fechasPago(fechainicial, fechafinal, numerocuotas);
        //System.out.println("lenfecha: "+fecha.length);
        //System.out.println(fecha[1]);
        for (int i = 1; i < Integer.parseInt(txCuotas.getText())+1; i++) {         
            String numero=""+i;
            if (numero.length()!=2){
                numero="0"+i;
            }
            //System.out.println("cuota: "+numero+", "+fecha[i]+", Espera, "+txCodigo.getText()+", "+cuenta.getNumeroCuenta()+", "+valorcuota);
            Cuota cuota = new Cuota(numero,fecha[i],"Espera",txCodigo.getText(),cuenta.getNumeroCuenta(),valorcuota);
            cuota.registrarCuota(true);
        }
    }
    public String[] fechasPago(Date fechai,Date fechaf, int numero){
        String [] fecha = new String[numero+1];
        Date [] dates = new Date[numero+1];
        //System.out.println("");
        dates[0]=fechai;
        for (int i = 1; i < numero+1; i++) {
            dates[i]=sumarDiasFecha(dates[i-1], 30);
//            System.out.println("fechafechasppago:"+dates[i]);
            String mes=""+(dates[i].getMonth()+1);
            if(2!=mes.length())mes="0"+(dates[i].getMonth()+1);
            fecha[i]=dates[i].getDate()+"/"+mes+"/"+(dates[i].getYear()+1900);
            //System.out.println(fecha[i]);
        }
        //fecha[numero-1]=dates[numero-1].getDate()+"/"+dates[numero-1].getMonth()+"/"+dates[numero-1].getYear();
        //System.out.println(fecha[numero]);
        return fecha;
    }
            
    public static Date sumarDiasFecha(Date fecha, int dias){
      if (dias==0) return fecha;
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha); 
      calendar.add(Calendar.DAY_OF_YEAR, dias);  
      String fechafinal=calendar.getTime().getDate()+"/"+(calendar.getTime().getMonth()+1)+"/"+(calendar.getTime().getYear()+1900);
      //System.out.println("fecharetornosumardiass: "+fechafinal);
      return calendar.getTime();
      //return calendar.getTime();
      
    }
    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
    
    public Date getFechaInicial(){
        String dia="";
        String mes="";
        String anio="";
        for (int i = 0; i < txFechInicio.getText().length(); i++) {
            char cha =txFechInicio.getText().charAt(i);
            if (i<2){
                dia+=cha;
            }
            if(i>2 && i<5){
                mes+=cha;
            }
            if(i>5 && i<=10){
                anio+=cha;
            }
        }
        return new Date((Integer.parseInt(anio)-1900),(Integer.parseInt(mes)-1), Integer.parseInt(dia));
    }
    
    private void CrearCuentaBancaria(){
        Registrar_CuentaBancaria registrocuenta = new Registrar_CuentaBancaria(mainInversion);
        cuenta=registrocuenta.getCuenta();
        if (cuenta != null) {
            jcheckRegistroBanco.setSelected(true);
        }else{
            JOptionPane.showMessageDialog(rootPane, "No se ha registrado la Cuenta Bancaria", "Atención!", JOptionPane.WARNING_MESSAGE);
            jcheckRegistroBanco.setSelected(false);
        }
    }
    
    private boolean verificarCampos(){//true si los campos estan llenos
        if(!(txCodigo.getText().length()>=3 && txMonto.getText().length()>4 && txTasaInteres.getText().length()>=1 && txCuotas.getText().length()>0 && jcheckRegistroBanco.isSelected())){
            JOptionPane.showMessageDialog(rootPane, "-Debe llenar todos los campos y Tener registrado una Cuenta Bancaria", "Atención!", JOptionPane.WARNING_MESSAGE);
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
}
