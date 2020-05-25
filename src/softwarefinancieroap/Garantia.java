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
public class Garantia {
    //atributos
    String codgarantia;
    String tipo;
    double valor;
    String ubicacion;

    //constructor
    public Garantia(String codgarantia, String tipo, double valor, String ubicacion) {
        this.codgarantia = codgarantia;
        this.tipo = tipo;
        this.valor = valor;
        this.ubicacion = ubicacion;
    }

    public String getCodgarantia() {
        return codgarantia;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    
    //metodos
    
    public boolean registrarGarantia(){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "insert into garantias values ('"+ codgarantia+ "','" 
                                                                                                + tipo +"'," 
                                                                                                + valor + ",'" 
                                                                                                + ubicacion + "')";
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
