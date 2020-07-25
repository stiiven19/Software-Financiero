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
public class Inversion extends Transaccion{
    private final String numeroCuentaBancaria;

    public Inversion(String numeroCuentaBancaria, String ceducli, String codtransaccion, String fechInicio, String fechTermino, double monto, double interes,int numerocuotas) {
        super(ceducli, codtransaccion, fechInicio, fechTermino, monto, interes,numerocuotas);
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    
    }
    
    public boolean registrarInversion(){
        boolean retorno = false;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "insert into inversiones values ('"+ ceducli+ "','" 
                                                                                                + codtransaccion +"','" 
                                                                                                + fechInicio + "','" 
                                                                                                + fechTermino + "','" 
                                                                                                + interes + "','" 
                                                                                                + monto + "','" 
                                                                                                + numeroCuentaBancaria + "',"
                                                                                                + numerocuotas + ")";
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
    
    public int Buscar_Inversion(){//1 = no hay inversiones en proceso ni iguales 2=Inversion con el mismo codigo 3=ya tiene inversion en proceso
        int retorno = 1;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            String sql
                    = "select * from inversiones where codinversion like '"+codtransaccion+"'";
            java.sql.Statement st = conexion.createStatement();
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {         
                retorno = 2;
            }
            
            sql="select * from cuotainversion join inversiones on cuotainversion.codinversion=inversiones.codinversion where ceduinversionista like '" + ceducli + "' and estadocuota in ('No Pagada','Espera')";
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
}
