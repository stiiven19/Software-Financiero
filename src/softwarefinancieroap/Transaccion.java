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

    public Transaccion(String ceducli, String codtransaccion, String fechInicio, String fechTermino, double monto, double interes) {
        this.ceducli = ceducli;
        this.codtransaccion = codtransaccion;
        this.fechInicio = fechInicio;
        this.fechTermino = fechTermino;
        this.monto = monto;
        this.interes = interes;
    }
}
