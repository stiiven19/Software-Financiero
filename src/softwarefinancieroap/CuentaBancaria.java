/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarefinancieroap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author lenovo
 */
public class CuentaBancaria {
    String numeroCuenta,banco,tipoCuenta;

    public CuentaBancaria(String numeroCuenta, String banco, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
        this.tipoCuenta = tipoCuenta;
    }
    public boolean registrarCuentaBancaria(){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "insert into cuentasbancarias values ('"+ numeroCuenta+ "','" 
                                                                                                + banco +"','" 
                                                                                                + tipoCuenta +"')";
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
    public String[] Buscar_CuentaBancaria(){
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        String [] values = new String[3];
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "select * from cuentasbancarias where numerocuenta like '"+numeroCuenta+"'";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {                
                String numeroCuenta = result.getString("numerocuenta");
                String Banco = result.getString("banco");
                String Tipo = result.getString("tipocuenta");
                values[0] = numeroCuenta;
                values[1] = Banco;
                values[2] = Tipo;
            }
            java.sql.Statement st = conexion.createStatement();
            st.close();
            conexion.close();
            
        }catch(Exception e){
            System.out.println("ERROR DE CONEXION registro " + e.getMessage());
        }
        return values;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    
    
}
