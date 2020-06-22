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

    public Cuota(String numeroCuota, String fechPago, String estadoCuota, String tipoPago, String codcomprobante, String fechEfectiva, String codtransaccion, String cuentaBanco, double montoCuota) {
        this.numeroCuota = numeroCuota;
        this.fechPago = fechPago;
        this.estadoCuota = estadoCuota;
        this.tipoPago = tipoPago;
        this.codcomprobante = codcomprobante;
        this.fechEfectiva = fechEfectiva;
        this.codtransaccion = codtransaccion;
        this.cuentaBanco = cuentaBanco;
        this.montoCuota = montoCuota;
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
    
    public boolean pagarCuota(boolean bandera){
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
                    = "insert into cuotainversion values ('"+ numeroCuota+ "','" 
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
}   

