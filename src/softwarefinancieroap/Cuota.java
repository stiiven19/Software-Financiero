/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarefinancieroap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author lenovo
 */
public class Cuota {
    String numeroCuota,fechPago,estadoCuota,tipoPago,codcomprobante,fechEfectiva,codtransaccion,cuentaBanco;
    double montoCuota;

    public Cuota(String numeroCuota, String fechPago, String estadoCuota, String codtransaccion, String cuentaBanco, double montoCuota) {
        this.numeroCuota = numeroCuota;
        this.fechPago = fechPago;
        this.estadoCuota = estadoCuota;
        this.codtransaccion = codtransaccion;
        this.cuentaBanco = cuentaBanco;
        this.montoCuota = montoCuota;
    }

    public void setEstadoCuota(String estadoCuota) {
        this.estadoCuota = estadoCuota;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public void setCodcomprobante(String codcomprobante) {
        this.codcomprobante = codcomprobante;
    }

    public void setFechEfectiva(String fechEfectiva) {
        this.fechEfectiva = fechEfectiva;
    }
    
    
    
    public boolean registrarCuota(boolean bandera){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql; 
            if(bandera){
                sql
                    = "insert into cuotainversion values ('"+ numeroCuota+ "','" 
                        + fechPago +"','" 
                        + montoCuota +"','"
                        + estadoCuota +"',"
                        + tipoPago +","
                        + codcomprobante +","
                        + fechEfectiva +",'"
                        + codtransaccion +"','"
                        + cuentaBanco +"')";
            }else{
                sql
                    = "insert into cuotaprestamo values ('"+ numeroCuota+ "','" 
                        + fechPago +"','" 
                        + montoCuota +"','"
                        + estadoCuota +"',"
                        + tipoPago +","
                        + codcomprobante +","
                        + fechEfectiva +",'"
                        + codtransaccion +"')";
            }
            
            //System.out.println(sql);
            PreparedStatement sentenciasql = conexion.prepareStatement(sql);
            sentenciasql.executeUpdate();
            sentenciasql.close();
            conexion.close();
            retorno = true;
        }catch(Exception e){
            System.out.println("ERROR DE CONEXION registro " + e.getMessage());
        }
        return retorno;
        
    }
    
    public boolean pagarCuota(){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql;
            sql
                = "update cuotainversion set estadocuota = '"+estadoCuota+"' where codinversion like '"+codtransaccion+"' and numerocuota like '"+numeroCuota+"';"
                    + "\nupdate cuotainversion set tipopago = '"+tipoPago+"' where codinversion like '"+codtransaccion+"' and numerocuota like '"+numeroCuota+"';"
                    + "\nupdate cuotainversion set codcomprobante = '"+codcomprobante+"' where codinversion like '"+codtransaccion+"' and numerocuota like '"+numeroCuota+"';"
                    + "\nupdate cuotainversion set fechefectiva = '"+fechEfectiva+"' where codinversion like '"+codtransaccion+"' and numerocuota like '"+numeroCuota+"';";
            PreparedStatement sentenciasql = conexion.prepareStatement(sql);
            sentenciasql.executeUpdate();
            sentenciasql.close();
            conexion.close();
            retorno = true;
        }catch(Exception e){
            System.out.println("ERROR DE CONEXION registro " + e.getMessage());
        }
        return retorno;
        
    }
}   

