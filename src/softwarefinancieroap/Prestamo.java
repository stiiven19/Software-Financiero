/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarefinancieroap;
import java.sql.*;
/**
 *
 * @author lenovo
 */
public class Prestamo extends Transaccion{
    //atributos
    String fechSolicitud_pre;
    String fechAprobacion_pre;
    String fiador;
    String garantia;
    String estado;
    public String numerocuotas;
    
    //constructor
    public Prestamo(String ceducli, String codtransaccion,String fechSolicitud_pre, String fechAprobacion_pre, String fechInicio, String fechTermino, double interes, double monto, String estado, String garantia, String fiador ) {
        super(ceducli, codtransaccion, fechInicio, fechTermino, monto, interes);
        this.fechSolicitud_pre = fechSolicitud_pre;
        this.fechAprobacion_pre = fechAprobacion_pre;
        this.fiador = fiador;
        this.garantia = garantia;
        this.estado = estado;
    }
    
    public boolean registrarPrestamo(){
        if (fechAprobacion_pre!=null) {
            fechAprobacion_pre="'"+fechAprobacion_pre+"'";
        }
        if (fiador!=null) {
            fiador="'"+fiador+"'";
        }
        if (garantia!=null) {
            garantia="'"+garantia+"'";
        }
        if (fechInicio!=null) {
            fechInicio="'"+fechInicio+"'";
        }
        if (fechTermino!=null) {
            fechTermino="'"+fechTermino+"'";
        }
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "insert into prestamos values ("+ ceducli+ ",'" 
                                                                                                + codtransaccion +"','" 
                                                                                                + fechSolicitud_pre + "'," 
                                                                                                + fechAprobacion_pre + "," 
                                                                                                + fechInicio + "," 
                                                                                                + fechTermino + ",'" 
                                                                                                + interes + "','" 
                                                                                                + monto + "','" 
                                                                                                + estado + "'," 
                                                                                                + garantia + "," 
                                                                                                + fiador + ","
                                                                                                + numerocuotas+ ")";
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
    
    public boolean Buscar_Prestamo(){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "select * from prestamos where codprestamo like '"+codtransaccion+"'";
            java.sql.Statement st = conexion.createStatement();
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {         
                retorno = true;
            }
            st.close();
            conexion.close();
            
        }catch(Exception e){
            System.out.println("ERROR DE CONEXION registro " + e.getMessage());
        }
        return retorno;
    }
}
