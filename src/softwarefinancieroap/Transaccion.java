/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarefinancieroap;

/**
 *
 * @author lenovo
 */
public class Transaccion {
    //atributos
    String ceducli;
    String codtransaccion;
    String fechInicio;
    String fechTermino;
    double monto;
    double interes;
    int numerocuotas;

    public Transaccion(String ceducli, String codtransaccion, String fechInicio, String fechTermino, double monto, double interes,int numerocuotas) {
        this.ceducli = ceducli;
        this.codtransaccion = codtransaccion;
        this.fechInicio = fechInicio;
        this.fechTermino = fechTermino;
        this.monto = monto;
        this.interes = interes;
        this.numerocuotas = numerocuotas;
    }

    public void setFechInicio(String fechInicio) {
        this.fechInicio = fechInicio;
    }

    public void setFechTermino(String fechTermino) {
        this.fechTermino = fechTermino;
    }

    
    public String getCeducli() {
        return ceducli;
    }

    public String getCodtransaccion() {
        return codtransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public double getInteres() {
        return interes;
    }
    public int getNumerocuotas() {
        return numerocuotas;
    }

    public String getFechInicio() {
        if(fechInicio != null)return fechInicio;
        return "";
    }

    public String getFechTermino() {
        if(fechTermino != null)return fechTermino;
        return "";
    }
    
    
}
