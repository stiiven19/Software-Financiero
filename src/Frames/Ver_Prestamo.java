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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import softwarefinancieroap.Cliente;
import softwarefinancieroap.Cuota;
import softwarefinancieroap.Garantia;
import softwarefinancieroap.Prestamo;

public class Ver_Prestamo extends JDialog{
    
    private         JLabel        jlPrestamo,jlCodigoPre,jlFechaSolicitud,jlFechaAprobación,jlFechaInicio,jlFechaTermino,jlMonto,jlTasaInteres,jlEstado,jlImageGarantia,jlImageFiador, jlCliente, jlGarantia, jlFiador, jlCocigoGa,jlTipo,jlValor,jlUbicacion,jlCCfiador,jlNombres,jlApellidos,jlTelefono,jlDireccion,jlcuotas,jlnotieneg,jlnotienef;
    private         JButton       jbAprobar, jbRechazar, jbRegresar;
    private         JPanel        panel;
    private final   Garantia      garantia;
    private final   Cliente       fiador;
    private         Prestamo      prestamo;
    private final   String        CCcliente;
    private boolean               cargo;
    
    public Ver_Prestamo(Ver_Prestamos mainframe,String CCcliente1,Prestamo prestamo,Garantia garantia,Cliente fiador,boolean cargo){
        super(mainframe, true);
        this.CCcliente = CCcliente1 ;
        this.garantia = garantia;
        this.fiador = fiador;
        this.prestamo = prestamo;
        this.cargo = cargo;
        setTitle("Ver Prestamo");
	setSize(500, 440);
        setupWidgets();
        setupEvents();
        setVisible(true);
    }

    private void setupWidgets() {
        //inicializamos obj
        jlPrestamo = new JLabel("Préstamo");jlPrestamo.setFont(new Font("Comic Sans MS",1,22));
        jlCodigoPre =  new JLabel("Codigo: " + prestamo.getCodtransaccion());
        jlFechaAprobación =  new JLabel("Fecha de Aprobación: " + prestamo.getFechAprobacion_pre());
        jlFechaInicio =  new JLabel("Fecha de Inicio: " + prestamo.getFechInicio());
        jlFechaSolicitud =  new JLabel("Fecha de Solicitud: " + prestamo.getFechSolicitud_pre());
        jlFechaTermino =  new JLabel("Fecha Termino: " + prestamo.getFechTermino());
        
        jlMonto =  new JLabel("Monto: " + String.format("%.2f",prestamo.getMonto()));
        jlTasaInteres =  new JLabel("Tasa de interes: " + prestamo.getInteres());
        jlEstado =  new JLabel("Estado: " + prestamo.getEstado());
        jlCliente =  new JLabel("Cliente: " + CCcliente);
        jlImageFiador = new JLabel();
        jlImageGarantia = new JLabel();
        jlcuotas = new JLabel("Numero Cuotas: " + prestamo.getNumerocuotas());
        
        panel = new JPanel(null);panel.setBackground(new Color(247, 247, 247));
        jlGarantia =  new JLabel("GARANTIA"); 
        jbRegresar = new JButton("Regresar");
        if (garantia != null){
            jlCocigoGa = new JLabel("-Codigo: " + garantia.getCodgarantia());
            jlTipo = new JLabel("-Tipo de Garantia: " + garantia.getTipo());
            jlUbicacion = new JLabel ("-Ubicacion: " + garantia.getUbicacion());
            jlValor = new JLabel("-Valor: " + String.format("%.2f",garantia.getValor()));
            jlCocigoGa.setBounds(280, 80, 150, 15);
            jlTipo.setBounds(280, 100, 180, 15);
            jlUbicacion.setBounds(280, 120, 150, 15);
            jlValor.setBounds(280, 140, 150, 15);
            panel.add(jlCocigoGa);
            panel.add(jlTipo);
            panel.add(jlUbicacion);
            panel.add(jlValor);
        }else{
            jlnotieneg = new JLabel("No tiene");
            jlnotieneg.setBounds(280, 100, 90, 15);
            panel.add(jlnotieneg);
        }
        
        jlFiador = new JLabel("FIADOR");
        if (fiador !=null){
            jlCCfiador = new JLabel("-N. Identificación: " + fiador.getCeducli());
            jlNombres = new JLabel("-Nombres: " + fiador.getNomcli());
            jlApellidos = new JLabel("-Apellidos: " + fiador.getApellicli());
            jlTelefono = new JLabel("-Telefono: " + fiador.getTelecli());
            jlDireccion = new JLabel("-Dirección: " + fiador.getDircli());
            
            jlCCfiador.setBounds(280, 200, 200, 15);
            jlNombres.setBounds(280, 220, 200, 15);
            jlApellidos.setBounds(280, 240, 200, 15);
            jlTelefono.setBounds(280, 260, 200, 15);
            jlDireccion.setBounds(280, 280, 200, 15);
            
            panel.add(jlCCfiador);
            panel.add(jlNombres);
            panel.add(jlApellidos);
            panel.add(jlTelefono);
            panel.add(jlDireccion);
            //panel.add
        }else{
            jlnotienef = new JLabel("No tiene");
            jlnotienef.setBounds(280, 250, 90, 15);
            panel.add(jlnotienef);
        }
        
        if("pendiente".equals(prestamo.getEstado())){
            jbAprobar = new JButton("Aprobar");
            jbRechazar = new JButton("Rechazar");
            jbAprobar.setBounds(205, 390, 90, 20); jbRechazar.setBounds(305, 390, 90, 20);
            panel.add(jbAprobar);
            panel.add(jbRechazar);
            jbAprobar.setBounds(105, 350, 90, 20); jbRechazar.setBounds(205, 350, 90, 20);
            jbRegresar.setBounds(305, 350, 90, 20);
            jbRechazar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.showConfirmDialog(rootPane, "-Esta Seguro que desea RECHAZAR el prestamo?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                        prestamo.setEstado("aprobado");
                        if(prestamo.aprobarPrestamo()){
                            jlEstado.setText("Estado: "+prestamo.getEstado());
                            JOptionPane.showMessageDialog(rootPane, "Se ha rechazado el prestamo", "Información!", JOptionPane.INFORMATION_MESSAGE);
                            jbAprobar.setVisible(false);
                            jbRechazar.setVisible(false);
                        }
                    }
                }    
            });//evento de boton cancelar prestamo

            jbAprobar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.showConfirmDialog(rootPane, "-Esta Seguro que desea APROBAR el prestamo?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                        Date fecha = new Date();
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        prestamo.setEstado("aprobado");
                        prestamo.setFechAprobacion_pre(dateFormat.format(fecha));
                        prestamo.setFechInicio(dateFormat.format(fecha));
                        int numerodias=30*prestamo.getNumerocuotas();
                        String dia="";
                        String mes="";
                        String anio="";
                        for (int i = 0; i < prestamo.getFechInicio().length(); i++) {
                            char cha = dateFormat.format(fecha).charAt(i);
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
                        prestamo.setFechTermino(fechaf.getDate()+"/"+mes+"/"+(fechaf.getYear()+1900));
                        if(prestamo.aprobarPrestamo()){
                            jlFechaTermino.setText("Fecha Termino: "+fechaf.getDate()+"/"+mes+"/"+(fechaf.getYear()+1900));
                            jlFechaInicio.setText("Fecha de Inicio: "+prestamo.getFechInicio());
                            jlFechaAprobación.setText("Fecha de Aprobación: "+prestamo.getFechAprobacion_pre());
                            jlEstado.setText("Estado: "+prestamo.getEstado());
                            JOptionPane.showMessageDialog(rootPane, "Se ha aprobado el prestamo", "Información!", JOptionPane.INFORMATION_MESSAGE);
                            jbAprobar.setVisible(false);
                            jbRechazar.setVisible(false);
                            double monto=prestamo.getMonto();
                            double f = (1+(prestamo.getInteres()/100));
                            double cuota=(monto*(Math.pow(f, prestamo.getNumerocuotas())))/prestamo.getNumerocuotas();
                            crearCuotas(fechai, fechaf, cuota, prestamo.getNumerocuotas(),prestamo.getCodtransaccion());
                        }
                        
                    }
                }
            });//evento boton registrar prestamo
        }else{
            jbRegresar.setBounds(205, 350, 90, 20);
        }
        
        jlImageGarantia.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/Garantias2.png")));
        jlImageFiador.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/Captura3.png")));
        
        jlGarantia.setBounds(280, 50, 60, 15);
        jlFiador.setBounds(280, 170, 60, 15);
        jlPrestamo.setBounds(150, 15, 210, 25);
        jlCliente.setBounds(20, 50, 120, 15);
        jlCodigoPre.setBounds(20, 80, 120, 15);
        jlFechaSolicitud.setBounds(20, 110, 200, 15);
        jlFechaAprobación.setBounds(20, 140, 2000, 15);
        jlFechaInicio.setBounds(20, 170, 200, 15);
        jlFechaTermino.setBounds(20, 200, 200, 15);
        jlMonto.setBounds(20, 230, 170, 15);
        jlTasaInteres.setBounds(20, 260, 120, 15);
        jlEstado.setBounds(20, 290, 120, 15);
        jlImageGarantia.setBounds(220, 45,60,30);
        jlImageFiador.setBounds(220, 165,60,30);
        jlcuotas.setBounds(20, 320, 120, 15);
        
        //agregamos al panel los obj
        panel.add(jlPrestamo);
        panel.add(jlCliente);
        panel.add(jlCodigoPre);
        panel.add(jlFechaSolicitud);
        panel.add(jlFechaAprobación);
        panel.add(jlFechaInicio);
        panel.add(jlFechaTermino);
        panel.add(jlMonto);
        panel.add(jlTasaInteres);
        panel.add(jlEstado);
        panel.add(jlImageGarantia);panel.add(jlGarantia);
        panel.add(jlImageFiador);panel.add(jlFiador);
        panel.add(jlcuotas);
        
        panel.add(jbRegresar);
        add(panel,BorderLayout.CENTER);
    }

    private void setupEvents() {
        jbAprobar.setBackground(new Color(36, 83, 181));
        jbRechazar.setBackground(new Color(36, 83, 181));
        jbRegresar.setBackground(new Color(36, 83, 181));
        if (!cargo){
            jbAprobar.setEnabled(false);
        }
        this.setResizable(false);
        Date fecha = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //estado sin editar
        
        
        
        jbRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });//evento boton registrar garantia   
    }
    
    public Date getFechaInicial(String fecha){
        String dia="";
        String mes="";
        String anio="";
        for (int i = 0; i < fecha.length(); i++) {
            char cha =fecha.charAt(i);
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
    
    public void crearCuotas(Date fechainicial,Date fechafinal, double valorcuota, int numerocuotas,String codtransaccion ){
        String [] fecha=fechasPago(fechainicial, fechafinal, numerocuotas);
        System.out.println("lenfecha: "+fecha.length);
        System.out.println(fecha[1]);
        for (int i = 1; i < numerocuotas+1; i++) {         
            String numero=""+i;
            if (numero.length()!=2){
                numero="0"+i;
            }
            System.out.println("cuota: "+numero+", "+fecha[i]+", Espera, "+codtransaccion+", "+null+", "+valorcuota);
            Cuota cuota = new Cuota(numero,fecha[i],"Espera",codtransaccion,null,valorcuota);
            cuota.registrarCuota(false);
        }
    }
    public String[] fechasPago(Date fechai,Date fechaf, int numerocuotas){
        String [] fecha = new String[numerocuotas+1];
        Date [] dates = new Date[numerocuotas+1];
        //System.out.println("");
        dates[0]=fechai;
        for (int i = 1; i < numerocuotas+1; i++) {
            dates[i]=sumarDiasFecha(dates[i-1], 30);
//            System.out.println("fechafechasppago:"+dates[i]);
            String mes=""+(dates[i].getMonth()+1);
            if(2!=mes.length())mes="0"+(dates[i].getMonth()+1);
            fecha[i]=dates[i].getDate()+"/"+mes+"/"+(dates[i].getYear()+1900);
            System.out.println(fecha[i]);
        }
        //fecha[numero-1]=dates[numero-1].getDate()+"/"+dates[numero-1].getMonth()+"/"+dates[numero-1].getYear();
        //System.out.println(fecha[numero]);
        return fecha;
    }
}
        

