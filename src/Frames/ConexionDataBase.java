/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;
import java.sql.*;
/**
 *
 * @author lenovo
 */
public class ConexionDataBase {
    
    public String EjecutarSql(String sql,String[] str){
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            
                    
            ResultSet result = conexion.createStatement().executeQuery(sql);
            System.out.println(result);
            String[] retorno=str;
            String ne="";
            while (result.next()) {      
                System.out.println("entro");
                for (int j = 0; j < str.length; j++) {
                    System.out.println(str[j]);
                    retorno[j]=result.getString(str[j]);
                    System.out.println(retorno[j]);
                    ne+=retorno[j]+"   ";
                }
            }
            result.close();
            st.close();
            conexion.close();
            return ne;
        }catch(Exception e){
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
        return "";
        
    }
}
