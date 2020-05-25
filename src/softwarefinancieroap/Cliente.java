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
public class Cliente {
    //atributos de clientes
    String ceducli;
    String nomcli;
    String apellicli;
    String dircli;
    String telecli;
    
    //constructor
    public Cliente(String ceducli, String nomcli, String apellicli, String dircli, String telecli) {
        this.ceducli = ceducli;
        this.nomcli = nomcli;
        this.apellicli = apellicli;
        this.dircli = dircli;
        this.telecli = telecli;
    }
    
    //------------------------------------------------------------------------
    //metodos
    public boolean registrar_cliente(){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "insert into clientes(ceducli,nomcli,apellicli,dircli,telecli)values ("+ ceducli+ ",'" + nomcli +"','" + apellicli + "','" + dircli + "'," + telecli + ")";
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
    public boolean Buscar_Cliente(){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "select * from clientes where ceducli like '"+ceducli+"'";
            java.sql.Statement st = conexion.createStatement();
            ResultSet result = conexion.createStatement().executeQuery(sql);
            String id=null;
            while (result.next()) {                
                id = result.getString("ceducli");
                String nombre = result.getString("nomcli");
                String apellido = result.getString("apellicli");
                String direccion = result.getString("dircli");
                String telefono = result.getString("telecli");
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
