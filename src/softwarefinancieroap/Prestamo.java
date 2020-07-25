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
    
    //constructor
    public Prestamo(String ceducli, String codtransaccion,String fechSolicitud_pre, String fechAprobacion_pre, String fechInicio, String fechTermino, double interes, double monto, String estado, String garantia, String fiador,int numerocuotas ) {
        super(ceducli, codtransaccion, fechInicio, fechTermino, monto, interes,numerocuotas);
        this.fechSolicitud_pre = fechSolicitud_pre;
        this.fechAprobacion_pre = fechAprobacion_pre;
        this.fiador = fiador;
        this.garantia = garantia;
        this.estado = estado;
    }

    public void setFechAprobacion_pre(String fechAprobacion_pre) {
        this.fechAprobacion_pre = fechAprobacion_pre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechAprobacion_pre() {
        if(fechAprobacion_pre != null)return fechAprobacion_pre;
        return "";
    }
    
    public String getFechSolicitud_pre() {
        return fechSolicitud_pre;
    }

    public String getEstado() {
        return estado;
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
    
    public int Buscar_Prestamo(){//1=no hay prestamos iguales ni en proceso 2= prestamos igual 2=prestamos en proceso
        int retorno = 1;
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
                retorno = 2;
            }
            
            sql="select * from prestamos where fechterminopre is null and estadoprestamo <> 'rechazado' and ceduprestatario like '"+ceducli+"'";
            ResultSet result2 = conexion.createStatement().executeQuery(sql);
            while (result2.next()) {         
                retorno = 3;
            }
            st.close();
            conexion.close();
            
        }catch(Exception e){
            System.out.println("ERROR DE CONEXION registro " + e.getMessage());
        }
        return retorno;
    }
    public boolean aprobarPrestamo(){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql;
            if ("rechazado".equals(estado)){
                sql = "update prestamos set estadoprestamo = '" + estado + "' where codprestamo like '"+codtransaccion+"';";  
            }else{
                sql = "update prestamos set estadoprestamo = '" + estado + "' where codprestamo like '"+codtransaccion+"';"
                    + "\nupdate prestamos set  fechaprobacionpre = '" + fechAprobacion_pre + "' where codprestamo like '"+codtransaccion+"';"
                    + "\nupdate prestamos set fechiniciopre = '" + fechInicio + "' where codprestamo like '"+codtransaccion+"';"
                    + "\nupdate prestamos set fechterminopre = '" + fechTermino+ "' where codprestamo like '"+codtransaccion+"';";  
            }
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
