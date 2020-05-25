/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;
import java.sql.*;


import softwarefinancieroap.Cliente;
import softwarefinancieroap.Garantia;
import softwarefinancieroap.Prestamo;
public class SoftwareFinancieroAP {
      
    public static void main(String[] args) {
        
        
        String cedu    ="1085347901";
     
        Cliente cliente = new Cliente(cedu, "steven andres","guerrero cruz","cra 4E #20-61","3163798800");
        //System.out.println("Registrocliente1: " + cliente.registrar_cliente());
        //cliente.registrar_cliente();
        
        Cliente fiador = new Cliente("12745947", "byron andres","guerrero martinez","cra 4E #20-61","3185554399");
        //System.out.println("Registrocliente2: " + fiador.registrar_cliente());
        
        //String codgarantia, String tipo, double valor, String ubicacion
        Garantia garantia = new Garantia("1234"," inmueble",12000," ubicacion");
        //System.out.println("RegistroGarantia: " + garantia.registrarGarantia());
        
        // String fechSolicitud_pre, String fechAprobacion_pre, int fiador, String garantia, char estado, int ceducli, String codtransaccion, String fechInicio, String fechTermino, double monto, double interes
        Prestamo presta= new Prestamo("12/12/2012","12/12/2012","12745947","1234","pendiente",cedu,"123abc","12/12/2010","12/12/2012",10000,2);
        //System.out.println("Registro Presstamo: " + presta.registrarPrestamo());
        
        
        new MenuPrincipal();
        
    }
       
    
}
